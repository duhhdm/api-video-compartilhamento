package br.com.alura.challenge.backend.apivideocompartilhamento.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.challenge.backend.apivideocompartilhamento.domain.Categoria;
import br.com.alura.challenge.backend.apivideocompartilhamento.domain.Video;
import br.com.alura.challenge.backend.apivideocompartilhamento.dto.CategoriaDto;
import br.com.alura.challenge.backend.apivideocompartilhamento.service.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	static final Logger log = LogManager.getLogger(CategoriaResource.class);

	@Autowired
	CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<List<Categoria>> listarTodos() {
		log.info("INICIANDO LISTAR CATEGORIAS");
		List<Categoria> list = categoriaService.findAll();

		if (list.isEmpty()) {
			log.info("FINALIZANDO LISTAR CATEGORIAS");
			return ResponseEntity.notFound().build();
		}

		else {
			log.info("FINALIZANDO LISTAR CATEGORIAS");
			return ResponseEntity.ok().body(list);
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable("id") Integer id) {
		log.info("INICIANDO LISTAR CATEGORIA POR ID -> " + id);
		Categoria categoria = categoriaService.findById(id);
		if (categoria != null) {
			log.info("FINALIZANDO LISTAR CATEGORIA POR ID -> " + id);
			return ResponseEntity.ok(categoria);
		} else {
			log.info("FINALIZANDO LISTAR CATEGORIA POR ID -> " + id);
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<HttpServletResponse> incluirCategoria(@Valid @RequestBody CategoriaDto categoriaDto) {
		try {
			log.info("INICIANDO INCLUIR CATEGORIA");
			categoriaService.save(categoriaDto.converterCategoria(categoriaDto));
			return ResponseEntity.created(URI.create("")).build();
		} catch (Exception e) {
			log.error("HOUVE UM ERRO AO INCLUIR CATEGORIA -> " + e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping
	public ResponseEntity<HttpServletResponse> alterarCategoria(@RequestBody CategoriaDto categoriaDto) {
		log.info("INICIANDO ALTERAR CATEGORIA -> " + categoriaDto.getIdCategoria());
		try {
			if (categoriaService.findById(categoriaDto.getIdCategoria()) != null) {
				categoriaService.save(categoriaDto.converterCategoriaInput(categoriaDto));
				return ResponseEntity.ok().build();
			}
			else {
				log.info("FINALIZANDO ALTERAR CATEGORIA -> " + categoriaDto.getIdCategoria()+" ID NAO EXISTENTE");
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			log.error("HOUVE UM ERRO AO INCLUIR CATEGORIA -> " + e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpServletResponse> deletarCategoria(@PathVariable("id") Integer id) {
		log.info("INICIANDO DELETAR CATEGORIA -> " + id);
		Categoria categoria = categoriaService.findById(id);
		if (categoria != null) {
			categoriaService.delete(categoria);
			log.info("FINALIZANDO DELETAR CATEGORIA -> " + id);
			return ResponseEntity.ok().build();
		} else {
			log.info("FINALIZANDO DELETAR CATEGORIA -> " + id +" ID NAO ENCONTRADO");
			return ResponseEntity.notFound().build();
		}

	}

	@GetMapping("/{id}/videos")
	public ResponseEntity<List<Video>> buscarVideosPorCategoria(@PathVariable("id") Integer id) {
		log.info("INICIANDO BUSCAR VIDEO POR CATEGORIA -> " + id);
		Categoria categoria = categoriaService.findById(id);
		if (categoria != null) {
			List<Video> list = categoriaService.findByVideoPorCategoria(categoria);
			if (list.isEmpty()) {
				log.info("FINALIZANDO BUSCAR VIDEO POR CATEGORIA -> " + id + " VIDEO NAO ENCONTRADO");
				return ResponseEntity.notFound().build();
			} else {
				log.info("FINALIZANDO BUSCAR VIDEO POR CATEGORIA");
				return ResponseEntity.ok(list);
			}
		} else {
			log.info("FINALIZANDO BUSCAR VIDEO POR CATEGORIA -> " + id + " CATEGORIA NAO ENCONTRADA");
			return ResponseEntity.notFound().build();
		}
	}

}
