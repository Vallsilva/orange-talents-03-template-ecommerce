package br.com.zupacademy.valeria.mercadolivre.product.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductQuestionRepository extends JpaRepository<ProductQuestionModel, Long> {
}
