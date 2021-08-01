package br.com.alura.challenge.backend.apivideocompartilhamento.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.challenge.backend.apivideocompartilhamento.domain.Categoria;
import br.com.alura.challenge.backend.apivideocompartilhamento.domain.Video;
import br.com.alura.challenge.backend.apivideocompartilhamento.repository.CategoriaRepository;
import br.com.alura.challenge.backend.apivideocompartilhamento.repository.VideoRepository;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	VideoRepository videoRepository;
	
	public List<Categoria> findAll(){
		return categoriaRepository.findAll();
	}

	public void save(Categoria categoria) {
		categoriaRepository.save(categoria);
	}

	public Categoria findById(Integer id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		if(categoria.isPresent())
			return categoria.get();
		else
			return null;
		
	}

	public void delete(Categoria categoria) {
		categoriaRepository.delete(categoria);
		
	}

	public List<Video> findByVideoPorCategoria(Categoria id) {
		return videoRepository.findByIdCategoria(id);
	}
	
}
