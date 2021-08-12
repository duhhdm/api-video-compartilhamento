package br.com.alura.challenge.backend.apivideocompartilhamento.resource;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.challenge.backend.apivideocompartilhamento.domain.Video;
import br.com.alura.challenge.backend.apivideocompartilhamento.dto.VideoDto;
import br.com.alura.challenge.backend.apivideocompartilhamento.service.VideoService;

@RestController
@RequestMapping(value = "/videos")
public class VideoResource {

	@Autowired
	VideoService videoService;

	static final Logger log = LogManager.getLogger(VideoResource.class);

	@GetMapping
	public ResponseEntity<Page<Video>> listarVideos(
			@RequestParam(value="search", required=false) String consulta,
			@RequestParam(value="pagina", required=true)Integer pagina) {
		log.info("INICIANDO LISTAR VIDEOS");
		Pageable paginacao = PageRequest.of(pagina, 5);
		Page<Video> list;
		
		if(consulta==null)
			list=videoService.findAll(paginacao);
		else {
			list=videoService.buscarVideoPorNome(consulta,paginacao);
		}
		if (list.isEmpty()) {
			log.info("FINALIZANDO LISTAR VIDEOS");
			return ResponseEntity.notFound().build();
		} else {
			log.info("FINALIZANDO LISTAR VIDEOS");
			return ResponseEntity.ok(list);
		}
	}

	@PostMapping
	public ResponseEntity<HttpServletResponse> inserirVideos(@Valid @RequestBody VideoDto videoDto) {
		log.info("INICIANDO INCLUSAO DE VIDEOS");
		try {
			videoService.insertVideo(videoDto);
			log.info("VIDEO INSERIDO COM SUCESSO");
			return ResponseEntity.created(URI.create("")).build();
		} catch (Exception e) {
			log.error("OCORREU UM ERRO AO INCLUIR UM VIDEO");
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Video> listarVideoPorId(@PathVariable Long id) {
		log.info("INICIANDO LISTAR VIDEO POR ID -> " + id);
		Video video = videoService.findById(id);
		if (video != null) {
			log.info("FINALIZADO LISTAR VIDEO POR ID -> " + id);
			return ResponseEntity.ok(video);
		} else {
			log.info("FINALIZADO LISTAR VIDEO POR ID -> " + id + " VIDEO NAO ENCONTRADO");
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping
	public ResponseEntity<HttpServletResponse> atualizarVideo(@RequestBody VideoDto video) {
		log.info("INICIANDO ATUALIZAR VIDEO ID -> " + video.getIdTitulo());
		if (videoService.findById(video.getIdTitulo()) != null) {
			videoService.updateVideo(video);
			log.info("FINALIZADO ATUALIZAR VIDEO ID -> " + video.getIdTitulo());
			return ResponseEntity.ok().build();
		} else {
			log.info("FINALIZADO ATUALIZAR VIDEO ID -> " + video.getIdTitulo() + " VIDEO NAO ENCONTRADO");
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<HttpServletResponse> deletarVideoPorId(@PathVariable Long id) {
		log.info("INICIANDO DELETAR VIDEO ID -> " + id);
		Video video = videoService.findById(id);
		if (video != null) {
			videoService.deletar(video);
			log.info("FINALIZADO DELETAR VIDEO ID -> " + id);
			return ResponseEntity.ok().build();
		} else {
			log.info("INICIANDO DELETAR VIDEO ID -> " + id + " NAO ENCONTRADO");
			return ResponseEntity.notFound().build();
		}
	}

}
