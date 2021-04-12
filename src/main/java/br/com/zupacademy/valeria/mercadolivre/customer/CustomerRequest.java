package br.com.zupacademy.valeria.mercadolivre.customer;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class CustomerRequest {

    @NotBlank @Email
    private String login;
    @NotBlank @Size(min = 6)
    private String password;


    public CustomerRequest() {

    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }


    public CustomerModel toModel() {
        return new CustomerModel(this.login, this.password);
    }
}
