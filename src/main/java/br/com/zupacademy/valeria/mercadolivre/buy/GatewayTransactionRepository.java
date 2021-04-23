package br.com.zupacademy.valeria.mercadolivre.buy;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GatewayTransactionRepository extends JpaRepository<GatewayTransaction, Long> {

    Optional<GatewayTransaction> findTopByPaymentIdOrderByCreatedAtDesc(Long id);
    List<GatewayTransaction> findAllByPaymentId(Long id);
}
