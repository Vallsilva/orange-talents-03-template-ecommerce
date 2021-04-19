package br.com.zupacademy.valeria.mercadolivre.config.authentication;

import br.com.zupacademy.valeria.mercadolivre.user.UserModel;
import br.com.zupacademy.valeria.mercadolivre.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;



    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


//Busca o usuário no banco e retorna exceção se não encontrar
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserModel> user = userRepository.findByLogin(email);
        return user.orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
    }

    //busca o id do usuario no banco e retorna exceção se não encontrar
    public UserDetails loadUserById(Long userId) {
        Optional<UserModel> user = userRepository.findById(userId);
        return user.orElseThrow(() -> new UsernameNotFoundException("ID not found!"));

    }
}
