package com.springfield.cidade_springfield.repository;

import com.springfield.cidade_springfield.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsername(String username);
    boolean existsById(Integer id);
    // Outros métodos necessários
}

