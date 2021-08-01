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
	
	public boolean insertVideo(VideoDto videoDto) {
		if(videoDto.getIdTitulo()==null) {
			Video video = videoDto.converterVideoPost(videoDto);
			Optional<Categoria> categoria = categoriaRepository.findById(videoDto.getIdCategoria());
			if(categoria.isPresent()) {
				video.setIdCategoria(categoria.get());
				videoRepository.save(video);
				return true;
			}
			else {
				return false;
			}
			
		}
		return false;
		
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
}
