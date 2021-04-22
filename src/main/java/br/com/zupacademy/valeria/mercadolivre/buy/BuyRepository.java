package br.com.zupacademy.valeria.mercadolivre.buy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyRepository extends JpaRepository<BuyModel, Long> {
}
