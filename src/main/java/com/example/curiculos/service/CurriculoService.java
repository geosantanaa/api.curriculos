package com.example.curiculos.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.curiculos.model.Curriculo;
import com.example.curiculos.model.dto.CurriculoEntradaDto;
import com.example.curiculos.model.dto.CurriculoSaidaDto;
import com.example.curiculos.repository.CurriculoRepository;

@Service
public class CurriculoService {

    @Autowired
    private CurriculoRepository repository;

    @Autowired
    public ModelMapper mapper;

    public CurriculoSaidaDto criar(CurriculoEntradaDto curriculoEntrada) {

        Curriculo curriculo = mapper.map(curriculoEntrada, Curriculo.class);
        repository.save(curriculo);

        CurriculoSaidaDto saida = mapper.map(curriculo, CurriculoSaidaDto.class);
        return saida;
    }

    public boolean alterar(Long id, CurriculoEntradaDto curriculoEntrada) {

        Optional <Curriculo> buscandoCurriculo = repository.findById(id);

        if(buscandoCurriculo.isPresent()){
            Curriculo curriculo = buscandoCurriculo.get();
            mapper.map(curriculoEntrada, Curriculo.class);
            repository.save(curriculo);
            return true;
        }
        return false;
    }

    public CurriculoSaidaDto pegarUm(Long id) {
        Optional<Curriculo> buscandoCurriculo = repository.findById(id);
        if(buscandoCurriculo.isPresent()){
            Curriculo curriculo = buscandoCurriculo.get();
            CurriculoSaidaDto saida = mapper.map(curriculo, CurriculoSaidaDto.class);
            return saida;
        }
        return null;
    }

    public boolean excluir(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<CurriculoSaidaDto> listar() {
        List<Curriculo> listaCurriculos = repository.findAll();
        return listaCurriculos.stream().map(curriculo -> mapper.map(curriculo, CurriculoSaidaDto.class)).collect(Collectors.toList());
    }
}
