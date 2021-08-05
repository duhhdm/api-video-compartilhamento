package br.com.alura.challenge.backend.apivideocompartilhamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.challenge.backend.apivideocompartilhamento.domain.Categoria;


@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
