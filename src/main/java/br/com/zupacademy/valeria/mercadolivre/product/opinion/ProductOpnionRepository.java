package br.com.zupacademy.valeria.mercadolivre.product.opinion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOpnionRepository extends JpaRepository<ProductOpinionModel, Long> {
}
