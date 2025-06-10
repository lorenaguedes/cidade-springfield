package com.springfield.cidade_springfield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaComunicadoProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void publicarComunicado(String mensagem) {
        kafkaTemplate.send("topico-comunicados", mensagem);
    }
}