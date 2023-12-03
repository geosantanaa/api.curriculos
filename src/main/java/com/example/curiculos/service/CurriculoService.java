package com.example.curiculos.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.curiculos.model.Curriculo;
import com.example.curiculos.model.dto.CurriculoEntradaDto;
import com.example.curiculos.model.dto.CurriculoSaidaDto;
import com.example.curiculos.repository.CurriculoRepository;

@org.springframework.stereotype.Service
public class CurriculoService {

	@Autowired
	private CurriculoRepository repository;


	public ResponseEntity<CurriculoSaidaDto> criar(CurriculoEntradaDto curriculoEntrada) {
        Curriculo curriculo = new Curriculo(curriculoEntrada.getNome(), curriculoEntrada.getTelefone(), curriculoEntrada.getEducacao(), curriculoEntrada.getExperiencias());
        repository.save(curriculo);
        return new ResponseEntity<>(mapToDto(curriculo), HttpStatus.CREATED);
    }

	public ResponseEntity<Boolean> alterar(Long id, CurriculoEntradaDto curriculoEntrada) {
        Optional<Curriculo> buscandoCurriculo = repository.findById(id);
        if (buscandoCurriculo.isPresent()) {
            Curriculo curriculo = buscandoCurriculo.get();
            curriculo.setNome(curriculoEntrada.getNome());
            curriculo.setTelefone(curriculoEntrada.getTelefone());
			curriculo.setEducacao(curriculoEntrada.getEducacao());
			curriculo.setExperiencias(curriculoEntrada.getExperiencias());
            repository.save(curriculo);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

	public ResponseEntity<CurriculoSaidaDto> pegarUm(Long id) {
        Optional<Curriculo> buscandoCurriculo = repository.findById(id);
        return buscandoCurriculo.map(curriculo -> new ResponseEntity<>(mapToDto(curriculo), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

	public ResponseEntity<Boolean> excluir(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

	public ResponseEntity<List<CurriculoSaidaDto>> listar() {
        List<Curriculo> listaCurriculos = repository.findAll();
        List<CurriculoSaidaDto> listaSaida = listaCurriculos.stream().map(this::mapToDto).collect(Collectors.toList());
        return new ResponseEntity<>(listaSaida, HttpStatus.OK);
    }

	private CurriculoSaidaDto mapToDto(Curriculo curriculo) {
        return new CurriculoSaidaDto(curriculo.getId(), curriculo.getNome(), curriculo.getTelefone(), curriculo.getEducacao(), curriculo.getExperiencias());
    }

}