package br.com.zupacademy.valeria.mercadolivre.config.jwt;

import io.jsonwebtoken.*;
import br.com.zupacademy.valeria.mercadolivre.user.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenManager {

    //endereço da senha definida no application.properties
    @Value("${mercadolivre.jwt.secret}")
    private String secret;

    //endereço do tempo que será definido a expiration da sessão
    @Value("${mercadolivre.jwt.expiration}")
    private Long expirationMillis;


    public static final String AUTHORITIES_KEY = "scopes";

    //Metodo para gerar o token
    public String generateToken(Authentication auth){
        //Pega o usuário logado
        UserModel user = (UserModel) auth.getPrincipal();

        //set data de expiração do token
        final Date now = new Date();
        final Date expiration = new Date(now.getTime() + this.expirationMillis);

        //
        final String authorities = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        //a chamada desses metodos gera o token com base no algoritmo definido
        return Jwts.builder().setIssuer("MERCADOLIVRE")
                .setSubject(Long.toString(user.getId())) //Pega o usuario pelo id
                .claim("AUTHORITIES_KEY", authorities)
                .setIssuedAt(now).setExpiration(expiration) //Define o tempo de quando o metodo foi chamado e seta a expiração
                .signWith(SignatureAlgorithm.HS256, this.secret) //define o algoritmo que vai croptiografar a senha a ser criptografada
                .compact();
    }

    //Metodo que valida se a senha é valida ou não
    public boolean isValid(String jwt){
        try{
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(jwt);
            return true;
        } catch (JwtException | IllegalArgumentException exception){
            return false;
        }
    }


    public Long getUserIdRequestToken(String jwt){
        Claims claims = Jwts.parser().setSigningKey(this.secret)
                .parseClaimsJws(jwt).getBody();
        return Long.parseLong(claims.getSubject());
    }
}
