package com.springfield.cidade_springfield.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "CAD_CIDADAO")
public class Cidadao {

    private static final Logger logger = LoggerFactory.getLogger(Cidadao.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;
    
    private String nome;
    private String endereco;
    private String bairro;

    public Cidadao() {
        logger.info("Criando entidade Cidadao");
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return this.bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
}
