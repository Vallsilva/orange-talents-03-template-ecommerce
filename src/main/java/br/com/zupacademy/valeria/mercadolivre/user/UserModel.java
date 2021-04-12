package br.com.zupacademy.valeria.mercadolivre.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class UserModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String encryptPassword;
    private LocalDate moment = LocalDate.now();

    public UserModel() {
    }

    public UserModel(String login, String plainPassword) {
        this.login = login;
        this.encryptPassword = new BCryptPasswordEncoder().encode(plainPassword);
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getEncryptPassword() {
        return encryptPassword;
    }

    public LocalDate getMoment() {
        return moment;
    }
}
