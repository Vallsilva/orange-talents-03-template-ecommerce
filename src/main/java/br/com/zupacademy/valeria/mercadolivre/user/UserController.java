package br.com.zupacademy.valeria.mercadolivre.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping
    public UserModel crateUser(@RequestBody @Valid UserRequest request){
        UserModel model = request.toModel();
        return model;
    }
}
