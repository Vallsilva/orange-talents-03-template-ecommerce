package br.com.zupacademy.valeria.mercadolivre.product.details;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsProductRepository extends JpaRepository<DetailsProduct, Long> {
}
