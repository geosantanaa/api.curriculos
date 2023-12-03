package com.example.curiculos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.curiculos.model.Curriculo;

public interface CurriculoRepository extends JpaRepository<Curriculo, Long>{
	
}