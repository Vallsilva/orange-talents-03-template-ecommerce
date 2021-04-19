package br.com.zupacademy.valeria.mercadolivre.config.role;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;

//Classe para definir tipos de perfis que acessem a aplicação
@Entity
public class Role implements GrantedAuthority {

    public static final Role ROLE_ADMIN = new Role("ROLE_ADMIN");
    public static final Role ROLE_CUSTOMER = new Role("ROLE_CUSTOMER");
    @Id
    private String authority;

    public Role() {
    }

    public Role(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
