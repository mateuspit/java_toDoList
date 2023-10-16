package br.com.barcelos.toDoList.user;

import java.time.LocalDateTime;
import java.util.UUID;

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
    private UUID id;
    @Column(name = "username_column", unique = true) // Apenas mostrando a possibilidade de mudar o nome da coluna
    private String username;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setId(UUID UUID) {
        this.id = UUID;
    }

    public UUID getId() {
        return this.id;
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

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }
}
