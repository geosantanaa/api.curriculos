package com.example.curiculos.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CurriculoSaidaDto {

	
    private Long id;
	private String nome;
	private String telefone;
	private String educacao;
	private String experiencias;

	public CurriculoSaidaDto(Long id, String nome, String telefone, String educacao, String experiencias) {
    }
}