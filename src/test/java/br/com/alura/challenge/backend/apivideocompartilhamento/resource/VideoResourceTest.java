package br.com.alura.challenge.backend.apivideocompartilhamento.resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.google.gson.Gson;

import br.com.alura.challenge.backend.apivideocompartilhamento.domain.Categoria;
import br.com.alura.challenge.backend.apivideocompartilhamento.domain.Video;
import br.com.alura.challenge.backend.apivideocompartilhamento.dto.VideoDto;
import br.com.alura.challenge.backend.apivideocompartilhamento.dto.en.CorEnum;
import br.com.alura.challenge.backend.apivideocompartilhamento.repository.CategoriaRepository;
import br.com.alura.challenge.backend.apivideocompartilhamento.repository.VideoRepository;
import br.com.alura.challenge.backend.apivideocompartilhamento.service.VideoService;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class VideoResourceTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	private VideoService videoService;
	
	@MockBean
	private VideoRepository videoRepository;
	
	@MockBean 
	private CategoriaRepository categoriaRepository;
	
	@Test
	void chamadaListarVideosBadRequest() throws Exception {
		this.mockMvc.perform(get("/videos")).andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	void chamadaListarVideos() throws Exception {
		List<Video> list = new ArrayList<>();
		Pageable paginacao = PageRequest.of(0, 5);
		Video video1 = new Video(Long.parseLong("1"),"testando","teste", "",new Categoria(1, "danadinho", CorEnum.AMARELO));
		list.add(video1);
		Page<Video> listPage = new PageImpl<>(list,paginacao,list.size());
		Mockito.when(videoService.findAll(paginacao)).thenReturn(listPage);
		this.mockMvc.perform(get("/videos?pagina=0")).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void chamadaVideosPorIdNotFound() throws Exception {
		List<Video> list = new ArrayList<>();
		Video video1 = new Video(Long.parseLong("1"),"testando","teste", "",new Categoria(1, "danadinho", CorEnum.AMARELO));
		list.add(video1);
		Mockito.when(videoService.findById(video1.getIdVideo())).thenReturn(video1);
		this.mockMvc.perform(get("/videos/2")).andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	void chamadaVideosPorId() throws Exception {
		List<Video> list = new ArrayList<>();
		Video video1 = new Video(Long.parseLong("1"),"testando","teste", "",new Categoria(1, "danadinho", CorEnum.AMARELO));
		list.add(video1);
		Mockito.when(videoService.findById(video1.getIdVideo())).thenReturn(video1);
		this.mockMvc.perform(get("/videos/1")).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void criarVideos() throws Exception{
		VideoDto videoDto = new VideoDto(null,"testando","teste","www.danadinho",1);
		Video video = videoDto.converterVideoPost(videoDto);
		Categoria categoria = new Categoria(1,"danadinho",CorEnum.AZUL);
		Optional<Categoria> categoriaOptional = Optional.of(categoria);
		video.setIdCategoria(categoria);
		Gson gson = new Gson();
		String json = gson.toJson(videoDto);
		video.setIdVideo(Long.parseLong("1"));
		Mockito.when(categoriaRepository.findById(videoDto.getIdCategoria())).thenReturn(categoriaOptional);
		Mockito.when(videoRepository.save(video)).thenReturn(video);
		this.mockMvc.perform(post("/videos").content(json)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	void criarVideosBadRequest() throws Exception{
		VideoDto videoDto = new VideoDto(null,"testando","teste",null, 1);
		Video video = videoDto.converterVideoPost(videoDto);
		Gson gson = new Gson();
		String json = gson.toJson(videoDto);
		video.setIdVideo(Long.parseLong("1"));
		
		Mockito.when(videoRepository.save(video)).thenReturn(video);
		this.mockMvc.perform(post("/videos").content(json)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	void atualizarVideos() throws Exception{
		VideoDto videoDto = new VideoDto(Long.parseLong("1"),"testando","teste","www",null);
		Video video = videoDto.converterVideoPost(videoDto);
		Gson gson = new Gson();
		Categoria categoria = new Categoria(1,"danadinho",CorEnum.AZUL);
		Video video1 = new Video(Long.parseLong("1"),"testando","teste", "",categoria);
		video.setIdVideo(Long.parseLong("1"));
		String json = gson.toJson(videoDto);
		
		Mockito.when(videoRepository.save(video)).thenReturn(video);
		Mockito.when(videoService.findById(video.getIdVideo())).thenReturn(video1);
		this.mockMvc.perform(put("/videos").content(json)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void atualizarVideosNotFound() throws Exception{
		VideoDto videoDto = new VideoDto(null,"testando","teste","www",null);
		Video video = videoDto.converterVideoPost(videoDto);
		Gson gson = new Gson();
		video.setIdVideo(Long.parseLong("1"));
		String json = gson.toJson(video);
		
		Mockito.when(videoRepository.save(video)).thenReturn(video);
		this.mockMvc.perform(put("/videos").content(json)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	void deletarVideo() throws Exception {
		Video video1 = new Video(Long.parseLong("1"),"testando","teste", "",null);
		Mockito.when(videoService.findById(video1.getIdVideo())).thenReturn(video1);
		this.mockMvc.perform(delete("/videos/1")).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void deletarVideoNotFound() throws Exception {
		Video video1 = new Video(Long.parseLong("1"),"testando","teste", "",null);
		Mockito.when(videoService.findById(video1.getIdVideo())).thenReturn(video1);
		this.mockMvc.perform(delete("/videos/2")).andExpect(MockMvcResultMatchers.status().isNotFound());
	}

}
