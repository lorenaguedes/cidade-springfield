package com.springfield.cidade_springfield.controller;

import com.springfield.cidade_springfield.model.Cidadao;
import com.springfield.cidade_springfield.repository.CidadaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cidadao")
public class CidadaoController {

    private static final Logger logger = LoggerFactory.getLogger(CidadaoController.class);

    @Autowired
    private CidadaoRepository cidadaoRepository;

    // Listar todos os cidadãos
    @GetMapping
    public List<Cidadao> listarCidadaos() {
        return cidadaoRepository.findAll();
    }

    // Consultar um cidadão pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Cidadao> consultarCidadao(@PathVariable Integer id) {
        Optional<Cidadao> cidadao = cidadaoRepository.findById(id);
        if (cidadao.isPresent()) {
            return new ResponseEntity<>(cidadao.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Cadastrar um novo cidadão
    @PostMapping
    public ResponseEntity<Cidadao> cadastrarCidadao(@RequestBody Cidadao cidadao) {
        try {
            if (cidadao.getNome() == null || cidadao.getEndereco() == null || cidadao.getBairro() == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Cidadao novoCidadao = cidadaoRepository.save(cidadao);
            return new ResponseEntity<>(novoCidadao, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Atualizar os dados de um cidadão
    @PutMapping("/{id}")
    public ResponseEntity<Cidadao> atualizarCidadao(@PathVariable Integer id, @RequestBody Cidadao cidadao) {
        if (!cidadaoRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cidadao.setId(id); 
        Cidadao cidadaoAtualizado = cidadaoRepository.save(cidadao);
        return new ResponseEntity<>(cidadaoAtualizado, HttpStatus.OK);
    }
}
