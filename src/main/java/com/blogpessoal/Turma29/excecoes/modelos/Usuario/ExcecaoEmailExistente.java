package com.blogpessoal.Turma29.excecoes.modelos.Usuario;

public class ExcecaoEmailExistente extends RuntimeException {
	
	/**
	 * Classe reponsavel por construtor de excecao caso email seja existente no
	 * cadastro de um novo usuario
	 * 
	 * @since 1.0
	 * @author Turma 29
	 *
	 */
		private static final long serialVersionUID = 1L;

		public ExcecaoEmailExistente(String emailExistente) {
			super("O email " + emailExistente + " já existe!");
	}
}
