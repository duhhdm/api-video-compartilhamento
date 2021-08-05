package br.com.alura.challenge.backend.apivideocompartilhamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.challenge.backend.apivideocompartilhamento.domain.Categoria;
import br.com.alura.challenge.backend.apivideocompartilhamento.domain.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long>{
	List<Video> findByIdCategoria(Categoria id);
	
	List<Video> findByDsTituloContains(String consulta);
}
