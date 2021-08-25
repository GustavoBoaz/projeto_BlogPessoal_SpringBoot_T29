package com.blogpessoal.Turma29.modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * Classe utilizada como Entidade no Banco de dados para Tema, a mesma possui
 * atributos que seram colunas no banco com titulo : tema
 * 
 * @author Turma 29
 * @since 1.0
 */
@Entity
public class Tema {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idTema;
	private @NotBlank String tema;

	public Long getIdTema() {
		return idTema;
	}

	public void setIdTema(Long idTema) {
		this.idTema = idTema;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

}
