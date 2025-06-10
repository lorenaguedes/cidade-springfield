package com.springfield.cidade_springfield.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaComunicadoConsumer {

    private final List<String> mensagensRecebidas = new ArrayList<>();

    @KafkaListener(topics = "topico-comunicados", groupId = "springfield-group")
    public void consumir(String mensagem) {
        mensagensRecebidas.add(mensagem);
    }

    public List<String> listarMensagens() {
        return mensagensRecebidas;
    }
}
