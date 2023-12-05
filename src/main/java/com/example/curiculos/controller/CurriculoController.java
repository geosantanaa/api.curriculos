package com.example.curiculos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.curiculos.model.Curriculo;
import com.example.curiculos.service.CurriculoService;

import java.util.List;

@RestController
@RequestMapping("curriculo")
public class CurriculoController {

    @Autowired
    private CurriculoService service;

    @PostMapping
    public ResponseEntity<Curriculo> criar(@RequestBody Curriculo curriculo) {
        return service.criar(curriculo);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<Boolean> alterar(@PathVariable("id") Long id, @RequestBody Curriculo curriculo) {
        return service.alterar(id, curriculo);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Curriculo> pegarUm(@PathVariable("id") Long id) {
        return service.pegarUm(id);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<Boolean> excluir(@PathVariable("id") Long id) {
        return service.excluir(id);
    }

    @GetMapping
    public ResponseEntity<List<Curriculo>> listar() {
        return service.listar();
    }
}
