package br.com.alura.challenge.backend.apivideocompartilhamento.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	static final Logger log = LogManager.getLogger(CategoriaResource.class);
	
	@Autowired
	CategoriaService categoriaService;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> listarTodos(){
		
		List<Categoria> list = categoriaService.findAll();
		
		if(list.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		else{
			return ResponseEntity.ok().body(list);
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable("id") Integer id){
		Categoria categoria = categoriaService.findById(id);
		if(categoria!=null)
			return ResponseEntity.ok(categoria);
		else
			return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<HttpServletResponse> incluirCategoria(@RequestBody CategoriaDto categoriaDto){
		try{
			categoriaService.save(categoriaDto.converterCategoria(categoriaDto));
			return ResponseEntity.created(URI.create("")).build();
		}catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PutMapping
	public ResponseEntity<HttpServletResponse> alterarCategoria(@RequestBody CategoriaDto categoriaDto) {
		if(categoriaService.findById(categoriaDto.getIdCategoria())!=null) {
			categoriaService.save(categoriaDto.converterCategoriaInput(categoriaDto));
			return ResponseEntity.ok().build();
		}
		else
			return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpServletResponse> deletarCategoria(Integer id) {
		Categoria categoria = categoriaService.findById(id);
		if(categoria!=null) {
			categoriaService.delete(categoria);
			return ResponseEntity.ok().build();
		}
		else
			return ResponseEntity.notFound().build();
			
	}
	
	@GetMapping("/{id}/videos")
	public ResponseEntity<List<Video>> buscarVideosPorCategoria(@PathVariable("id") Integer id){
		Categoria categoria = categoriaService.findById(id);
		if(categoria!=null) {
			List<Video> list = categoriaService.findByVideoPorCategoria(categoria);
			if(list.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			else {
				return ResponseEntity.ok(list);
			}
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
}
