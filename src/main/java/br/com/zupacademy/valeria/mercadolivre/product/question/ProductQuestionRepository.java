package br.com.zupacademy.valeria.mercadolivre.product.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductQuestionRepository extends JpaRepository<ProductQuestionModel, Long> {

    @Query("SELECT title FROM ProductQuestionModel WHERE productModel.id = ?1")
    List<String> getQuestions(Long idProduct);
}
