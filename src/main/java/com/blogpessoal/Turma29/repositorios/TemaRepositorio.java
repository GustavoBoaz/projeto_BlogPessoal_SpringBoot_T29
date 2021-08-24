package com.blogpessoal.Turma29.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogpessoal.Turma29.modelos.Tema;

@Repository
public interface TemaRepositorio extends JpaRepository<Tema, Long> {

}
