package com.ipog.projetointegrador.model;
import com.ipog.projetointegrador.util.PasswordProvider;
import jakarta.persistence.*;

import java.security.SecureRandom;
import java.util.Arrays;

@Entity
@Table(name = "usuarios")

public class Usuario {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String senha;
    private String email;
    @Column(columnDefinition = "CHAR", length = 1)
    private String status;
    @Column(columnDefinition = "CHAR", length = 1)
    private String tipo;

    public String getToken() {
        return token;
    }

    public void setToken() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        this.token = Arrays.toString(bytes);
    }

    public void cleanToken() {
        this.token = null;
    }

    private String token;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = PasswordProvider.encryptPassphrase(senha);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
