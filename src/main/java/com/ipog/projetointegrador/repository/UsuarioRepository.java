package com.ipog.projetointegrador.repository;

import com.ipog.projetointegrador.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>  {
    Optional<Usuario> findByToken(String token);
    Optional<Usuario> findByLogin(String login);
    Optional<Usuario> findByEmailOrLogin(String email, String login);
    Optional<Usuario> findByEmailOrLoginAndIdIsNot(String email, String login, Long id);
}
