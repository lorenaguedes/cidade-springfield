package com.springfield.cidade_springfield.model;

import com.springfield.cidade_springfield.enums.EstadoSolicitacao;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "HISTORICO_SOLICITACAO")
public class HistoricoSolicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer idCidadao;
    private LocalDateTime dataEstado;

    @Enumerated(EnumType.STRING)
    private EstadoSolicitacao estado;

    private String descricao;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getIdCidadao() { return idCidadao; }
    public void setIdCidadao(Integer idCidadao) { this.idCidadao = idCidadao; }

    public LocalDateTime getDataEstado() { return dataEstado; }
    public void setDataEstado(LocalDateTime dataEstado) { this.dataEstado = dataEstado; }

    public EstadoSolicitacao getEstado() { return estado; }
    public void setEstado(EstadoSolicitacao estado) { this.estado = estado; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
