package br.com.barcelos.toDoList.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/create")
    public UserModel createUser(@RequestBody UserModel userModel) {
        // var newUser = this.userRepository.save(userModel);
        var newUser = this.userRepository.save(userModel);
        return newUser;
    }

}
