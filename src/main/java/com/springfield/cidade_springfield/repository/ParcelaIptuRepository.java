package com.springfield.cidade_springfield.repository;

import com.springfield.cidade_springfield.model.ParcelaIptu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParcelaIptuRepository extends JpaRepository<ParcelaIptu, Long> {
    List<ParcelaIptu> findByIdCidadao(Integer idCidadao);
    List<ParcelaIptu> findByIdCidadaoAndPagoTrue(Integer idCidadao);
}
