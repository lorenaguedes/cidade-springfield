package com.springfield.cidade_springfield.controller;

import com.springfield.cidade_springfield.enums.EventoSolicitacao;
import com.springfield.cidade_springfield.model.HistoricoSolicitacao;
import com.springfield.cidade_springfield.service.SolicitacaoService;
import com.springfield.cidade_springfield.enums.EstadoSolicitacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solicitacoes")
public class SolicitacaoController {

    @Autowired
    private SolicitacaoService solicitacaoService;

    @PostMapping("/processar")
    public ResponseEntity<String> processarEvento(
        @RequestParam Integer idCidadao,
        @RequestParam EventoSolicitacao evento,
        @RequestParam String descricao
    ) {
        EstadoSolicitacao novoEstado = solicitacaoService.processarEvento(idCidadao, evento, descricao);
        return ResponseEntity.ok("Novo estado: " + novoEstado);
    }

    @GetMapping("/historico/{idCidadao}")
    public ResponseEntity<List<HistoricoSolicitacao>> consultarHistorico(@PathVariable Integer idCidadao) {
        return ResponseEntity.ok(solicitacaoService.buscarHistorico(idCidadao));
    }
}
