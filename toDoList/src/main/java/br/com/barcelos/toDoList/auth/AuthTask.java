package br.com.barcelos.toDoList.auth;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.barcelos.toDoList.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthTask extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var servletPath = request.getServletPath();

        if (servletPath.startsWith("/tasks")) {
            var authorization = request.getHeader("Authorization");
            var userAuthEncoded = authorization.substring("Basic".length()).trim();
            byte[] userAuthDecoded = Base64.getDecoder().decode(userAuthEncoded);
            var authStringDecoded = new String(userAuthDecoded);

            String[] userCredentials = authStringDecoded.split(":");
            String usernameCredentials = userCredentials[0];
            String passwordCredentials = userCredentials[1];

            var user = userRepository.findByUsername(usernameCredentials);

            if (user == null) {
                response.sendError(401, "Usuário não existe!");
            } else {
                var correctPassword = BCrypt.verifyer().verify(passwordCredentials.toCharArray(), user.getPassword());
                if (!correctPassword.verified) {
                    response.sendError(401, "Senha incorreta!");
                }
                request.setAttribute("userId", user.getId());
                filterChain.doFilter(request, response);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
