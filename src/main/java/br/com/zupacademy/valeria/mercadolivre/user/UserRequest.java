package br.com.zupacademy.valeria.mercadolivre.user;

import br.com.zupacademy.valeria.mercadolivre.config.validator.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class UserRequest {

    @NotBlank @Email @UniqueValue(domainClass = UserModel.class, fieldName = "login")
    private String login;
    @NotBlank @Size(min = 6)
    private String password;


    public UserRequest() {

    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }


    public UserModel toModel() {
        return new UserModel(this.login, this.password);
    }
}
