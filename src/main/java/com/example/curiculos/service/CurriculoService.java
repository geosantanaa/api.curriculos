package com.example.curiculos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.curiculos.model.Curriculo;
import com.example.curiculos.repository.CurriculoRepository;

@Service
public class CurriculoService {

    @Autowired
    private CurriculoRepository repository;

    public ResponseEntity<Curriculo> criar(Curriculo curriculo) {
        repository.save(curriculo);
        return new ResponseEntity<>(curriculo, HttpStatus.CREATED);
    }

    public ResponseEntity<Boolean> alterar(Long id, Curriculo curriculo) {
        Optional<Curriculo> buscandoCurriculo = repository.findById(id);
        if (buscandoCurriculo.isPresent()) {
            Curriculo curriculoExistente = buscandoCurriculo.get();
            curriculoExistente.setNome(curriculo.getNome());
            curriculoExistente.setTelefone(curriculo.getTelefone());
            curriculoExistente.setEducacao(curriculo.getEducacao());
            curriculoExistente.setExperiencias(curriculo.getExperiencias());
            repository.save(curriculoExistente);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Curriculo> pegarUm(Long id) {
        Optional<Curriculo> buscandoCurriculo = repository.findById(id);
        return buscandoCurriculo.map(curriculo -> new ResponseEntity<>(curriculo, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Boolean> excluir(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Curriculo>> listar() {
        List<Curriculo> listaCurriculos = repository.findAll();
        return new ResponseEntity<>(listaCurriculos, HttpStatus.OK);
    }
}
