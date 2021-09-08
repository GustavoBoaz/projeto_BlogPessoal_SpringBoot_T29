package com.blogpessoal.Turma29.excecoes;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.blogpessoal.Turma29.excecoes.modelos.Usuario.ExcecaoEmailExistente;

/**
 * Classe responsavel pelo gerenciamento das respostas caso erro em requisiçoes
 * 
 * @since 1.0
 * @author Turma 29
 *
 */

@ControllerAdvice
public class ManipuladorDeExcecoes {

	/**
	 * Método reponsavel por alterar mensagem de erro do tipo response BAD_REQUEST
	 * caso o email cadastrado ja exista no sistema (400) para saída
	 * padronizada
	 *
	 * @param e do tipo ExcecaoEmailExistente
	 * @return Map com resposta padrão
	 */
	
	@ExceptionHandler(ExcecaoEmailExistente.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, String> emailExistenteNotFoundHandler (ExcecaoEmailExistente excecaoemailexistente){
		Map<String, String> response = new HashMap<>();
		response.put("Status", "400");
		response.put("Mensagem", "Insira um email diferente!");
		response.put("Problema", excecaoemailexistente.getMessage());
		
		return response;
	}
}
