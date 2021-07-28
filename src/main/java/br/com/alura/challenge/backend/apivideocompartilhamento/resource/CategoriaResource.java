package br.com.alura.challenge.backend.apivideocompartilhamento.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.challenge.backend.apivideocompartilhamento.domain.Categoria;
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
	
	@PostMapping
	public ResponseEntity<HttpServletResponse> incluirCategoria(@RequestBody CategoriaDto categoriaDto){
		
		categoriaService.save(categoriaDto.converterCategoria(categoriaDto));
		return ResponseEntity.ok().build();
	}
	
}
