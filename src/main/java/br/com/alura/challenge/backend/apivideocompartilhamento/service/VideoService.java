package br.com.alura.challenge.backend.apivideocompartilhamento.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.challenge.backend.apivideocompartilhamento.domain.Categoria;
import br.com.alura.challenge.backend.apivideocompartilhamento.domain.Video;
import br.com.alura.challenge.backend.apivideocompartilhamento.dto.VideoDto;
import br.com.alura.challenge.backend.apivideocompartilhamento.repository.CategoriaRepository;
import br.com.alura.challenge.backend.apivideocompartilhamento.repository.VideoRepository;

@Service
public class VideoService {
	
	static final Logger log = LogManager.getLogger(VideoService.class);

	
	@Autowired
	VideoRepository videoRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	public List<Video> findAll(){
		return videoRepository.findAll();
	}
	
	public void insertVideo(VideoDto videoDto) {
			Video video = videoDto.converterVideoPost(videoDto);
			Optional<Categoria> categoria = categoriaRepository.findById(videoDto.getIdCategoria());
			if(categoria.isPresent()) {
				video.setIdCategoria(categoria.get());
				videoRepository.save(video);
				
			}
			else {
				Optional<Categoria> categoriaPadrao = categoriaRepository.findById(1);
				if(categoriaPadrao.isPresent()) {
					video.setIdCategoria(categoriaPadrao.get());
					videoRepository.save(video);
				}
			}
			
		
	}
	
	public Video findById(Long id) {
		Optional<Video> video = videoRepository.findById(id);
		if(video.isPresent())
			return video.get();
		else
			return null;
		
		
	}
	
	public void deletar(Video video) {
		videoRepository.delete(video);
	}
	
	public List<Video> buscarVideoPorNome(String consulta){
		return videoRepository.findByDsTituloContains(consulta);
	}
	
	public Video updateVideo(VideoDto videoDto) {
		if(videoDto.getIdTitulo()==null) {
			return null;
		}
		else {
			Video video = new Video(videoDto.getIdTitulo(), videoDto.getDsTitulo(), videoDto.getDsVideo(), videoDto.getDsUrl());
			if(videoDto.getIdCategoria()!=null) {
				Optional<Categoria> categoria = categoriaRepository.findById(videoDto.getIdCategoria());
				if(categoria.isPresent()) {
					video.setIdCategoria(categoria.get());
				}
			}
			return videoRepository.save(video);
		}
	}
}
