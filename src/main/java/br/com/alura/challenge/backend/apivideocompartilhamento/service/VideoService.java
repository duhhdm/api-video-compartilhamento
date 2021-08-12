package br.com.alura.challenge.backend.apivideocompartilhamento.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	public Page<Video> findAll(Pageable paginacao){
		return videoRepository.findAll(paginacao);
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
	
	public Page<Video> buscarVideoPorNome(String consulta, Pageable paginacao){
		return videoRepository.findByDsTituloContains(consulta, paginacao);
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
