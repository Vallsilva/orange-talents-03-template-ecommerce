package br.com.zupacademy.valeria.mercadolivre.config.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

//Classe criada para receber o usuário e o login para realizar a autenticação na aplicação

public class UserLoginRequest {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String plainPassword;

    public UsernamePasswordAuthenticationToken build(){
        return new UsernamePasswordAuthenticationToken(this.email, this.plainPassword);
    }

    public String getEmail() {
        return email;
    }

    public String getPlainPassword() {
        return plainPassword;
    }
}
