package br.com.zupacademy.valeria.mercadolivre.config.jwt;

public class AuthTokenOutput {

    private String tokenType;
    private String token;

    public AuthTokenOutput(String tokenType, String token) {
        this.tokenType = tokenType;
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getToken() {
        return token;
    }
}
