package com.example.curiculos.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Entity(name = "curriculo")
public class Curriculo {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String nome;
	private String telefone;
	private String educacao;
	private String experiencias;

	 public Curriculo(String nome, String telefone, String educacao, String experiencias) {
    }
}