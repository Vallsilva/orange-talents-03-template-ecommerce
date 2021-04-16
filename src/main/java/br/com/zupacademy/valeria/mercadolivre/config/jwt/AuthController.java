package br.com.zupacademy.valeria.mercadolivre.config.jwt;


import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenManager tokenManager;

    @PostMapping
    public ResponseEntity<AuthTokenOutput> authenticate(@RequestBody UserLoginRequest loginRequest){
        UsernamePasswordAuthenticationToken authenticationToken = loginRequest.build();

        System.out.println("Até aqui funciona - antes authenticate");
        Authentication auth =authenticationManager.authenticate(authenticationToken);
        System.out.println("Até aqui funciona - authenticate");
        String jwt = tokenManager.generateToken(auth);
        var tokenResponse = new AuthTokenOutput("Bearer ", jwt);

        return ResponseEntity.ok(tokenResponse);
    }

    @GetMapping
    public String password(String plainPassword) {
       return new BCryptPasswordEncoder().encode(plainPassword);
    }
}
