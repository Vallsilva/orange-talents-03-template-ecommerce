package br.com.zupacademy.valeria.mercadolivre.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findByLogin(String email);

    UserModel getByLogin(String email);
}
