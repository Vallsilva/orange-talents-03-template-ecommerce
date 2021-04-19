package br.com.zupacademy.valeria.mercadolivre.config.jwt;


import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

//endpoint para gerar token

@Api
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenManager tokenManager;

    @PostMapping //Recebe como parametro um objeto
    public ResponseEntity<AuthTokenOutput> authenticate(@RequestBody UserLoginRequest loginRequest){

        UsernamePasswordAuthenticationToken authenticationToken = loginRequest.build();


        Authentication auth =authenticationManager.authenticate(authenticationToken);

        String jwt = tokenManager.generateToken(auth);
        var tokenResponse = new AuthTokenOutput("Bearer ", jwt);

        return ResponseEntity.ok(tokenResponse);
    }

    @GetMapping
    public String password(String plainPassword) {
       return new BCryptPasswordEncoder().encode(plainPassword);
    }
}
