package com.springfield.cidade_springfield.controller;

import com.springfield.cidade_springfield.service.KafkaComunicadoConsumer;
import com.springfield.cidade_springfield.service.KafkaComunicadoProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comunicados")
public class ComunicadoController {

    @Autowired
    private KafkaComunicadoProducer producer;

    @Autowired
    private KafkaComunicadoConsumer consumer;

    @PostMapping("/publicar")
    public ResponseEntity<String> publicar(@RequestBody String mensagem) {
        producer.publicarComunicado(mensagem);
        return ResponseEntity.ok("Mensagem publicada com sucesso!");
    }

    @GetMapping("/visualizar")
    public List<String> visualizar() {
        return consumer.listarMensagens();
    }
}
