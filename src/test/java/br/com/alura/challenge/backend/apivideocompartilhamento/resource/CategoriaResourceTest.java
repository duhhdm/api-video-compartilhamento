package br.com.alura.challenge.backend.apivideocompartilhamento.resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.google.gson.Gson;

import br.com.alura.challenge.backend.apivideocompartilhamento.domain.Categoria;
import br.com.alura.challenge.backend.apivideocompartilhamento.domain.Video;
import br.com.alura.challenge.backend.apivideocompartilhamento.dto.CategoriaDto;
import br.com.alura.challenge.backend.apivideocompartilhamento.dto.en.CorEnum;
import br.com.alura.challenge.backend.apivideocompartilhamento.repository.CategoriaRepository;
import br.com.alura.challenge.backend.apivideocompartilhamento.repository.VideoRepository;
import br.com.alura.challenge.backend.apivideocompartilhamento.service.CategoriaService;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CategoriaResourceTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	CategoriaService categoriaService;
	
	@MockBean
	VideoRepository videoRepository;
	
	@MockBean
	CategoriaRepository categoriaRepository;
	
	
	
	@Test
	void findAll200() throws Exception {
		List<Categoria> list = new ArrayList<>();
		Categoria categoria = new Categoria(1, "teste categoria", CorEnum.LIVRE);
		list.add(categoria);
		Mockito.when(categoriaService.findAll()).thenReturn(list);
		this.mockMvc.perform(get("/categorias")).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void chamadaListarCategoriasNotFound() throws Exception {
		this.mockMvc.perform(get("/categorias")).andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	void findById200() throws Exception {
		Categoria categoria = new Categoria(1, "teste categoria", CorEnum.LIVRE);
		Mockito.when(categoriaService.findById(1)).thenReturn(categoria);
		this.mockMvc.perform(get("/categorias/1")).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void findByIdNotFound() throws Exception {
		Categoria categoria = new Categoria(1, "teste categoria", CorEnum.LIVRE);
		Mockito.when(categoriaService.findById(1)).thenReturn(categoria);
		this.mockMvc.perform(get("/categorias/2")).andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	void findByVideoCategoria200() throws Exception {
		Categoria categoria = new Categoria(1, "teste categoria", CorEnum.LIVRE);
		Video video = new Video(Long.parseLong("1"), "testando video", "Video de teste", "wwww",categoria);
		List<Video> list = new ArrayList<>();
		list.add(video);
		Mockito.when(categoriaService.findById(1)).thenReturn(categoria);
		Mockito.when(categoriaService.findByVideoPorCategoria(categoria)).thenReturn(list);
		Mockito.when(videoRepository.findByIdCategoria(categoria)).thenReturn(list);
		this.mockMvc.perform(get("/categorias/1/videos")).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void findByVideoCategoriaNotFound() throws Exception {
		Categoria categoria = new Categoria(1, "teste categoria", CorEnum.LIVRE);
		Video video = new Video(Long.parseLong("1"), "testando video", "Video de teste", "wwww",categoria);
		List<Video> list = new ArrayList<>();
		list.add(video);
		Mockito.when(categoriaService.findById(1)).thenReturn(categoria);
		Mockito.when(categoriaService.findByVideoPorCategoria(categoria)).thenReturn(list);
		Mockito.when(videoRepository.findByIdCategoria(categoria)).thenReturn(list);
		this.mockMvc.perform(get("/categorias/2/videos")).andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	void saveCategoria201() throws Exception {
		CategoriaDto categoriaDto = new CategoriaDto("teste categoria", CorEnum.LIVRE);
		Categoria categoria = new Categoria(null, categoriaDto.getDsTitulo(),categoriaDto.getCor()); 
		Gson gson = new Gson();
		String json = gson.toJson(categoriaDto);
		categoria.setIdColuna(1);
		Mockito.when(categoriaRepository.save(categoria)).thenReturn(categoria);
		this.mockMvc.perform(post("/categorias").content(json)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isCreated());
		
	}

	@Test
	void saveCategoriaBadRequest() throws Exception {
		CategoriaDto categoriaDto = new CategoriaDto("teste categoria", null);
		Categoria categoria = new Categoria(null, categoriaDto.getDsTitulo(),categoriaDto.getCor()); 
		Gson gson = new Gson();
		String json = gson.toJson(categoriaDto);
		categoria.setIdColuna(1);
		Mockito.when(categoriaRepository.save(categoria)).thenReturn(categoria);
		this.mockMvc.perform(post("/categorias").content(json)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isBadRequest());
		
	}
	
	@Test
	void updateCategoria200() throws Exception{
		CategoriaDto categoriaDto = new CategoriaDto("teste",CorEnum.LIVRE);
		categoriaDto.setIdCategoria(1);
		Categoria categoria = new Categoria(1,"testando update",CorEnum.VERDE);
		Categoria categoriaUpdate = categoria;
		categoriaUpdate.setDsTitulo(categoriaDto.getDsTitulo());
		categoriaUpdate.setDsCor(categoriaDto.getCor());
		Gson gson = new Gson();
		String json = gson.toJson(categoriaDto);
		Mockito.when(categoriaService.findById(categoria.getIdCategoria())).thenReturn(categoria);
		Mockito.when(categoriaRepository.save(categoriaUpdate)).thenReturn(categoriaUpdate);
		this.mockMvc.perform(put("/categorias").content(json)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void updateCategoriaNotFound() throws Exception{
		CategoriaDto categoriaDto = new CategoriaDto("teste",CorEnum.LIVRE);
		Categoria categoria = new Categoria(1,"testando update",CorEnum.VERDE);
		Categoria categoriaUpdate = categoria;
		categoriaUpdate.setDsTitulo(categoriaDto.getDsTitulo());
		categoriaUpdate.setDsCor(categoriaDto.getCor());
		Gson gson = new Gson();
		String json = gson.toJson(categoriaDto);
		Mockito.when(categoriaRepository.save(categoriaUpdate)).thenReturn(categoriaUpdate);
		this.mockMvc.perform(put("/categorias").content(json)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	void updateCategoriaBadRequest() throws Exception{
		CategoriaDto categoriaDto = new CategoriaDto("teste",CorEnum.LIVRE);
		Categoria categoria = new Categoria(1,"testando update",CorEnum.VERDE);
		Categoria categoriaUpdate = categoria;
		categoriaUpdate.setDsTitulo(categoriaDto.getDsTitulo());
		categoriaUpdate.setDsCor(categoriaDto.getCor());
		Mockito.when(categoriaRepository.save(categoriaUpdate)).thenReturn(categoriaUpdate);
		this.mockMvc.perform(put("/categorias").content("")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	void deleteCategoria200() throws Exception{
		Categoria categoria = new Categoria(1,"testando update",CorEnum.VERDE);
		Mockito.when(categoriaService.findById(1)).thenReturn(categoria);
		this.mockMvc.perform(delete("/categorias/1")).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void deleteCategoriaNotFound() throws Exception{
		Categoria categoria = new Categoria(1,"testando update",CorEnum.VERDE);
		Mockito.when(categoriaService.findById(categoria.getIdCategoria())).thenReturn(categoria);
		this.mockMvc.perform(delete("/categorias/2")).andExpect(MockMvcResultMatchers.status().isNotFound());
	}
}
