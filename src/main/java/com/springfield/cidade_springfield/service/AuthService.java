package com.springfield.cidade_springfield.service;

import com.springfield.cidade_springfield.model.Usuario;
import com.springfield.cidade_springfield.repository.CidadaoRepository;
import com.springfield.cidade_springfield.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Collections;
import java.util.List;

import java.util.Date;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CidadaoRepository cidadaoRepository; // Repositório de cidadãos para verificar se o cidadão existe

    @Transactional
    public boolean cadastrarUsuario(Integer idCidadao, String username, String senha) {

        if (!cidadaoRepository.existsById(idCidadao)) {
            throw new RuntimeException("Erro: Cidadão com ID " + idCidadao + " não encontrado!");
        }

        if (usuarioRepository.existsById(idCidadao)) {
            throw new RuntimeException("Erro: Usuário já cadastrado para este ID de cidadão!");
        }

        if (usuarioRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Erro: Username " + username + " já está em uso!");
        }

        Usuario usuario = new Usuario();
        usuario.setId(idCidadao);
        usuario.setUsername(username);
        usuario.setSenha(senha);
        usuario.setTentativasLogin(0);
        usuario.setBloqueado(false);
        usuario.setUltimoLogin(LocalDateTime.now());

        usuarioRepository.saveAndFlush(usuario);
        return true;
    }

    public ResponseEntity<?> login(String username, String senha) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(username);

        if (!usuarioOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("message", "Usuário não encontrado!"));
        }

        Usuario usuario = usuarioOpt.get();

        if (usuario.isBloqueado()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Collections.singletonMap("message", "Usuário bloqueado devido a múltiplas tentativas de login."));
        }

        if (!usuario.getSenha().equals(senha)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("message", "Senha incorreta!"));
        }

        usuario.setUltimoLogin(LocalDateTime.now());
        usuarioRepository.save(usuario);

        return ResponseEntity.ok(Collections.singletonMap("message", "Login realizado com sucesso!"));
    }

    public boolean trocarSenha(Integer id, String novaSenha) {
        if (id == null || novaSenha == null || novaSenha.isEmpty()) {
            return false;
        }
    
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (!usuarioOpt.isPresent()) {
            return false;
        }
    
        Usuario usuario = usuarioOpt.get();
        usuario.setSenha(novaSenha);
        usuarioRepository.save(usuario);
    
        return true;
    }

    public void bloquearUsuario(Integer id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        usuarioOpt.ifPresent(usuario -> {
            usuario.setBloqueado(true);
            usuarioRepository.save(usuario);
        });
    }

    public void desbloquearUsuario(Integer id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        usuarioOpt.ifPresent(usuario -> {
            usuario.setBloqueado(false);
            usuarioRepository.save(usuario);
        });
    }
}
