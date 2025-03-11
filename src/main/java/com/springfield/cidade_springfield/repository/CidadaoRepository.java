package com.springfield.cidade_springfield.repository;

import com.springfield.cidade_springfield.model.Cidadao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadaoRepository extends JpaRepository<Cidadao, Integer> {
}