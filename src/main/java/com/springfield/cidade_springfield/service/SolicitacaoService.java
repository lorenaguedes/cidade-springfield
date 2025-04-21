package com.springfield.cidade_springfield.service;

import com.springfield.cidade_springfield.enums.EstadoSolicitacao;
import com.springfield.cidade_springfield.enums.EventoSolicitacao;
import com.springfield.cidade_springfield.model.HistoricoSolicitacao;
import com.springfield.cidade_springfield.repository.HistoricoSolicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SolicitacaoService {

    @Autowired
    private HistoricoSolicitacaoRepository historicoRepo;

    @Autowired
    private StateMachineFactory<EstadoSolicitacao, EventoSolicitacao> factory;

    public EstadoSolicitacao processarEvento(Integer idCidadao, EventoSolicitacao evento, String descricao) {
        StateMachine<EstadoSolicitacao, EventoSolicitacao> stateMachine = factory.getStateMachine(UUID.randomUUID().toString());
        stateMachine.start();

        stateMachine.sendEvent(evento);
        EstadoSolicitacao estadoAtual = stateMachine.getState().getId();

        HistoricoSolicitacao h = new HistoricoSolicitacao();
        h.setIdCidadao(idCidadao);
        h.setDataEstado(LocalDateTime.now());
        h.setEstado(estadoAtual);
        h.setDescricao(descricao);
        historicoRepo.save(h);

        return estadoAtual;
    }

    public List<HistoricoSolicitacao> buscarHistorico(Integer idCidadao) {
        return historicoRepo.findByIdCidadao(idCidadao);
    }
}
