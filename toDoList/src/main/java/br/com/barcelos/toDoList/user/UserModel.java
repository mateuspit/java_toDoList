package br.com.barcelos.toDoList.user;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

//Podemos usar lib como o lombok para criar getters e setters automaticamente e mais limpo
@Entity(name = "tb_users")
public class UserModel {
    @Id
    @GeneratedValue(generator = "UUDI")
    private String UUID;
    @Column(name = "username_column") // Apenas mostrando a possibilidade de mudar o nome da coluna
    private String username;
    private String name;
    private String password;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getUUID() {
        return this.UUID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }
}
