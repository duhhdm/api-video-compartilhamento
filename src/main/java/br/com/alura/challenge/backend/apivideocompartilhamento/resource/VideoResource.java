package br.com.alura.challenge.backend.apivideocompartilhamento.resource;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.challenge.backend.apivideocompartilhamento.domain.Video;
import br.com.alura.challenge.backend.apivideocompartilhamento.dto.VideoDto;
import br.com.alura.challenge.backend.apivideocompartilhamento.service.VideoService;

@RestController
@RequestMapping(value="/videos")
public class VideoResource {
	
	@Autowired
	VideoService videoService;
	
	static final Logger log = LogManager.getLogger(VideoResource.class);

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Video>> listarVideos(){
		log.info("INICIANDO LISTAR VIDEOS");
		List<Video> list = new ArrayList<>();
		list = videoService.findAll();
		if(list.isEmpty()) {
			log.info("FINALIZANDO LISTAR VIDEOS");
			return ResponseEntity.notFound().build();
		}
		else {
			log.info("FINALIZANDO LISTAR VIDEOS");
			return ResponseEntity.ok(list);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> inserirVideos(@Valid @RequestBody VideoDto videoDto) {
		log.info("INICIANDO INCLUSAO DE VIDEOS");
		Video video = videoDto.converterVideoPost(videoDto);
		try {
			videoService.insertVideo(video);
			log.info("VIDEO INSERIDO COM SUCESSO -> "+video.getIdVideo());
			return ResponseEntity.ok().build();
		}catch(Exception e) {
			log.error("OCORREU UM ERRO AO INCLUIR UM VIDEO");
			return ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Video> listarVideoPorId(@PathVariable Long id){
		log.info("INICIANDO LISTAR VIDEO POR ID -> "+id);
		Video video = new Video();
		video = videoService.findById(id);
		if(video != null) {
			log.info("FINALIZADO LISTAR VIDEO POR ID -> "+id);
			return ResponseEntity.ok(video);
		}
		else {
			log.info("FINALIZADO LISTAR VIDEO POR ID -> "+id+" VIDEO NAO ENCONTRADO");
			return ResponseEntity.notFound().build();
		}
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<?> atualizarVideo(@RequestBody Video video) {
		log.info("INICIANDO ATUALIZAR VIDEO ID -> "+video.getIdVideo());
		if(videoService.findById(video.getIdVideo())!=null) {
			videoService.insertVideo(video);
			log.info("FINALIZADO ATUALIZAR VIDEO ID -> "+video.getIdVideo());
			return ResponseEntity.ok().build();
		}else {
			log.info("FINALIZADO ATUALIZAR VIDEO ID -> "+video.getIdVideo()+" VIDEO NAO ENCONTRADO");
			return ResponseEntity.notFound().build();
		}
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<?> deletarVideoPorId(@PathVariable Long id){
		Video video = new Video();
		video = videoService.findById(id);
		log.info("INICIANDO DELETAR VIDEO ID -> "+ id);
		if(video!=null) {
			videoService.deletar(video);
			log.info("FINALIZADO DELETAR VIDEO ID -> "+ id);
			return ResponseEntity.ok().build();
		}else {
			log.info("INICIANDO DELETAR VIDEO ID -> "+ id +" NAO ENCONTRADO");
			return ResponseEntity.notFound().build();
		}
	}	
}
