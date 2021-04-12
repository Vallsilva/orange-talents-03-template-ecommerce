package br.com.zupacademy.valeria.mercadolivre.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    @PostMapping
    public CustomerModel crateUser(@RequestBody @Valid CustomerRequest request){
        CustomerModel model = request.toModel();
        return model;
    }
}
