package com.springfield.cidade_springfield.controller;

import com.springfield.cidade_springfield.dto.ProcessarSolicitacaoRequest;
import com.springfield.cidade_springfield.enums.EstadoSolicitacao;
import com.springfield.cidade_springfield.model.HistoricoSolicitacao;
import com.springfield.cidade_springfield.service.SolicitacaoService;
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
    public ResponseEntity<String> processarEvento(@RequestBody ProcessarSolicitacaoRequest req) {
        EstadoSolicitacao novoEstado = solicitacaoService.processarEvento(req.getIdCidadao(), req.getEvento(), req.getDescricao());
        return ResponseEntity.ok("Novo estado: " + novoEstado);
    }

    @GetMapping("/historico/{idCidadao}")
    public ResponseEntity<List<HistoricoSolicitacao>> consultarHistorico(@PathVariable Integer idCidadao) {
        return ResponseEntity.ok(solicitacaoService.buscarHistorico(idCidadao));
    }
}
