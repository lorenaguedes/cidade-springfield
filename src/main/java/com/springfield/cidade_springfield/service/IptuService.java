package com.springfield.cidade_springfield.service;

import com.springfield.cidade_springfield.model.ParcelaIptu;
import com.springfield.cidade_springfield.repository.ParcelaIptuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IptuService {

    @Autowired
    private ParcelaIptuRepository parcelaRepo;

    public void gerarParcelas(Integer idCidadao, boolean pagamentoUnico) {
        List<ParcelaIptu> parcelas = new ArrayList<>();

        for (int mes = 1; mes <= 12; mes++) {
            ParcelaIptu p = new ParcelaIptu();
            p.setIdCidadao(idCidadao);
            p.setMes(mes);
            p.setPago(false);

            if (pagamentoUnico) {
                p.setValor(mes == 1 ? new BigDecimal("1000.00") : BigDecimal.ZERO);
            } else {
                p.setValor(new BigDecimal("1000.00"));
            }

            parcelas.add(p);
        }

        parcelaRepo.saveAll(parcelas);
    }

    public BigDecimal calcularTotalDevido(Integer idCidadao) {
        return parcelaRepo.findByIdCidadao(idCidadao).stream()
                .filter(p -> !p.isPago())
                .map(ParcelaIptu::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calcularTotalPago(Integer idCidadao) {
        return parcelaRepo.findByIdCidadaoAndPagoTrue(idCidadao).stream()
                .map(ParcelaIptu::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public boolean efetuarPagamento(Long idParcela) {
        Optional<ParcelaIptu> parcela = parcelaRepo.findById(idParcela);
        if (parcela.isPresent() && !parcela.get().isPago()) {
            ParcelaIptu p = parcela.get();
            p.setPago(true);
            parcelaRepo.save(p);
            return true;
        }
        return false;
    }
}
