package br.com.zupacademy.valeria.mercadolivre.buy;

public enum PaymentFormat {
    PAYPAL("PayPal", "https://paypal.com", "paypal"),
    PAGSEGURO("PagSeguro", "https://pagseguro.com", "pagseguro");

    private String name;
    private String url;
    private String code;

    PaymentFormat(String name, String url, String code){
        this.name = name;
        this.url = url;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getCode() {
        return code;
    }
}
