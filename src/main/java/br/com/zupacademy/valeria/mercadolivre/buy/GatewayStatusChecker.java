package br.com.zupacademy.valeria.mercadolivre.buy;

public interface GatewayStatusChecker {

    boolean isGatewaySuccess(String gatewayStatus);
    boolean isGatewayError(String gatewayStatus);
}
