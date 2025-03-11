package com.springfield.cidade_springfield.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    private Integer id; 

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    private Cidadao cidadao; 
    private String username;

    @Column(nullable = false)  // Torna a senha obrigatória
    private String senha;

    private boolean bloqueado;
    private int tentativasLogin;

    @Column(name = "ultimo_login")  // Nome do campo no banco
    private LocalDateTime ultimoLogin;

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cidadao getCidadao() {
        return cidadao;
    }

    public void setCidadao(Cidadao cidadao) {
        this.cidadao = cidadao;
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

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public int getTentativasLogin() {
        return tentativasLogin;
    }

    public void setTentativasLogin(int tentativasLogin) {
        this.tentativasLogin = tentativasLogin;
    }

    public LocalDateTime getUltimoLogin() {
        return ultimoLogin;
    }

    public void setUltimoLogin(LocalDateTime ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }
}
