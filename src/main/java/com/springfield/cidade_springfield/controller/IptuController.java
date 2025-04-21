package com.springfield.cidade_springfield.controller;

import com.springfield.cidade_springfield.service.IptuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/iptu")
public class IptuController {

    @Autowired
    private IptuService iptuService;

    @PostMapping("/gerar")
    public ResponseEntity<String> gerarParcelas(
        @RequestParam Integer idCidadao,
        @RequestParam boolean pagamentoUnico
    ) {
        iptuService.gerarParcelas(idCidadao, pagamentoUnico);
        return ResponseEntity.ok("Parcelas geradas com sucesso.");
    }

    @GetMapping("/total-pago/{idCidadao}")
    public ResponseEntity<BigDecimal> totalPago(@PathVariable Integer idCidadao) {
        return ResponseEntity.ok(iptuService.calcularTotalPago(idCidadao));
    }

    @GetMapping("/total-devido/{idCidadao}")
    public ResponseEntity<BigDecimal> totalDevido(@PathVariable Integer idCidadao) {
        return ResponseEntity.ok(iptuService.calcularTotalDevido(idCidadao));
    }

    @PostMapping("/pagar/{idParcela}")
    public ResponseEntity<String> pagarParcela(@PathVariable Long idParcela) {
        boolean pago = iptuService.efetuarPagamento(idParcela);
        return pago ? ResponseEntity.ok("Parcela paga.") : ResponseEntity.badRequest().body("Parcela inválida ou já paga.");
    }
}
