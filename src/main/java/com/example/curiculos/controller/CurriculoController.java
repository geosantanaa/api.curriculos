package com.example.curiculos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.curiculos.model.dto.CurriculoEntradaDto;
import com.example.curiculos.model.dto.CurriculoSaidaDto;
import com.example.curiculos.service.CurriculoService;

import java.util.List;

@RestController
@RequestMapping("curriculo")
public class CurriculoController {

    @Autowired
    private CurriculoService service;

    @PostMapping
    public ResponseEntity<CurriculoSaidaDto> criar(@RequestBody CurriculoEntradaDto curriculoEntrada) {
        return service.criar(curriculoEntrada);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<Boolean> alterar(@PathVariable("id") Long id, @RequestBody CurriculoEntradaDto curriculoEntrada) {
        return service.alterar(id, curriculoEntrada);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<CurriculoSaidaDto> pegarUm(@PathVariable("id") Long id) {
        return service.pegarUm(id);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<Boolean> excluir(@PathVariable("id") Long id) {
        return service.excluir(id);
    }

    @GetMapping
    public ResponseEntity<List<CurriculoSaidaDto>> listar() {
    List<CurriculoSaidaDto> curriculos = service.listar();
    return ResponseEntity.ok(curriculos);    }
}
