package br.com.zupacademy.valeria.mercadolivre.user;

import br.com.zupacademy.valeria.mercadolivre.config.role.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
public class UserModel implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String encryptPassword;
    private LocalDate moment = LocalDate.now();
    @ManyToOne
    @JoinColumn(name = "authority")
    private Role role;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.encryptPassword;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
