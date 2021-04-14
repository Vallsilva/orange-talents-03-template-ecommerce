package br.com.zupacademy.valeria.mercadolivre.config.jwt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        Authentication auth =authenticationManager.authenticate(authenticationToken);
        String jwt = tokenManager.generateToken(auth);
        var tokenResponse = new AuthTokenOutput("Bearer ", jwt);

        return ResponseEntity.ok(tokenResponse);
    }
}
