package com.springfield.cidade_springfield.controller;

import com.springfield.cidade_springfield.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarUsuario(@RequestParam Integer idCidadao, @RequestParam String username, @RequestParam String senha) {
        try {
            boolean sucesso = authService.cadastrarUsuario(idCidadao, username, senha);
            if (sucesso) {
                return ResponseEntity.ok("Usuário cadastrado com sucesso!");
            } else {
                return ResponseEntity.badRequest().body("Erro ao cadastrar usuário.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro interno no servidor: " + e.getMessage());
        }
    }

    @PostMapping("/trocarSenha")
    public ResponseEntity<?> trocarSenha(@RequestBody TrocarSenhaRequest request) {
        boolean sucesso = authService.trocarSenha(request.getId(), request.getNovaSenha());
        if (sucesso) {
            return ResponseEntity.ok("{\"message\": \"Senha alterada com sucesso!\"}");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("{\"message\": \"Falha ao alterar a senha!\"}");
        }
    }
}
