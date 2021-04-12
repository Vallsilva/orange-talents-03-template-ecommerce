package br.com.zupacademy.valeria.mercadolivre.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {
}
