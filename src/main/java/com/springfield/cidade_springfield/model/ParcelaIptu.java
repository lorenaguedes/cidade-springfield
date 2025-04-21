package com.springfield.cidade_springfield.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "IPTU_PARCELA")
public class ParcelaIptu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer idCidadao;
    private int mes; 
    private BigDecimal valor;
    private boolean pago;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdCidadao() {
        return idCidadao;
    }

    public void setIdCidadao(Integer idCidadao) {
        this.idCidadao = idCidadao;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }
}
