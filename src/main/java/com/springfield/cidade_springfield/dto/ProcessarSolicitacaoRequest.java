package com.springfield.cidade_springfield.dto;

import com.springfield.cidade_springfield.enums.EventoSolicitacao;

public class ProcessarSolicitacaoRequest {
    private Integer idCidadao;
    private EventoSolicitacao evento;
    private String descricao;

    public Integer getIdCidadao() { return idCidadao; }
    public void setIdCidadao(Integer idCidadao) { this.idCidadao = idCidadao; }

    public EventoSolicitacao getEvento() { return evento; }
    public void setEvento(EventoSolicitacao evento) { this.evento = evento; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
