package com.springfield.cidade_springfield.controller;

import com.springfield.cidade_springfield.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest.getUsername(), loginRequest.getSenha());
    }

    @PostMapping("/trocar-senha")
    public ResponseEntity<String> trocarSenha(@RequestBody TrocarSenhaRequest request) {
        try {
            authService.trocarSenha(request.getId(), request.getNovaSenha());
            return ResponseEntity.ok("Senha alterada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao alterar a senha.");
        }
    }

    @PostMapping("/bloquear/{id}")
    public void bloquearUsuario(@PathVariable Integer id) {
        authService.bloquearUsuario(id);
    }

    @PostMapping("/desbloquear/{id}")
    public void desbloquearUsuario(@PathVariable Integer id) {
        authService.desbloquearUsuario(id);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarUsuario(@RequestBody CadastroRequest cadastroRequest) {
        try {
            boolean sucesso = authService.cadastrarUsuario(
                    cadastroRequest.getId(),
                    cadastroRequest.getUsername(),
                    cadastroRequest.getSenha());

            if (sucesso) {
                return ResponseEntity.ok("Usuário cadastrado com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao cadastrar usuário");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
        }
    }
}

// Classe auxiliar para representar o corpo da requisição de login
class LoginRequest {
    private String username;
    private String senha;

    // Getters e setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

// Classe auxiliar para representar o corpo da requisição de cadastro
class CadastroRequest {
    private Integer id;
    private String username;
    private String senha;

    // Getters e setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

class TrocarSenhaRequest {
    private Integer id;
    private String novaSenha;

    // Getters e setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }
}
