package br.com.alura.challenge.backend.apivideocompartilhamento.resource;

import static org.mockito.Mockito.when;
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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.google.gson.Gson;

import br.com.alura.challenge.backend.apivideocompartilhamento.domain.Video;
import br.com.alura.challenge.backend.apivideocompartilhamento.dto.VideoDto;
import br.com.alura.challenge.backend.apivideocompartilhamento.repository.VideoRepository;
import br.com.alura.challenge.backend.apivideocompartilhamento.service.VideoService;


@RunWith(SpringRunner.class)
@WebMvcTest(VideoResource.class)
class VideoResourceTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	private VideoService videoService;
	
	@MockBean
	private VideoRepository videoRepository;
	
	
	@Test
	void chamadaListarVideosNotFound() throws Exception {
		this.mockMvc.perform(get("/videos")).andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	void chamadaListarVideos() throws Exception {
		List<Video> list = new ArrayList<>();
		Video video1 = new Video(Long.parseLong("1"),"testando","teste", "");
		list.add(video1);
		Mockito.when(videoService.findAll()).thenReturn(list);
		this.mockMvc.perform(get("/videos")).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void chamadaVideosPorIdNotFound() throws Exception {
		List<Video> list = new ArrayList<>();
		Video video1 = new Video(Long.parseLong("1"),"testando","teste", "");
		list.add(video1);
		Mockito.when(videoService.findById(video1.getIdVideo())).thenReturn(video1);
		this.mockMvc.perform(get("/videos/2")).andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	void chamadaVideosPorId() throws Exception {
		List<Video> list = new ArrayList<>();
		Video video1 = new Video(Long.parseLong("1"),"testando","teste", "");
		list.add(video1);
		Mockito.when(videoService.findById(video1.getIdVideo())).thenReturn(video1);
		this.mockMvc.perform(get("/videos/1")).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void criarVideos() throws Exception{
		VideoDto videoDto = new VideoDto("testando","teste","www.danadinho");
		Video video = videoDto.converterVideoPost(videoDto);
		Gson gson = new Gson();
		String json = gson.toJson(videoDto);
		video.setIdVideo(Long.parseLong("1"));
		
		when(videoRepository.save(video)).thenReturn(video);
		
		this.mockMvc.perform(post("/videos").content(json)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void criarVideosBadRequest() throws Exception{
		VideoDto videoDto = new VideoDto("testando","teste",null);
		Video video = videoDto.converterVideoPost(videoDto);
		Gson gson = new Gson();
		String json = gson.toJson(videoDto);
		video.setIdVideo(Long.parseLong("1"));
		
		when(videoRepository.save(video)).thenReturn(video);
		
		this.mockMvc.perform(post("/videos").content(json)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	void atualizarVideos() throws Exception{
		VideoDto videoDto = new VideoDto("testando","teste","www");
		Video video = videoDto.converterVideoPost(videoDto);
		Gson gson = new Gson();
		Video video1 = new Video(Long.parseLong("1"),"testando","teste", "");
		video.setIdVideo(Long.parseLong("1"));
		String json = gson.toJson(video);
		
		when(videoRepository.save(video)).thenReturn(video);
		Mockito.when(videoService.findById(video.getIdVideo())).thenReturn(video1);
		this.mockMvc.perform(put("/videos").content(json)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void atualizarVideosNotFound() throws Exception{
		VideoDto videoDto = new VideoDto("testando","teste","www");
		Video video = videoDto.converterVideoPost(videoDto);
		Gson gson = new Gson();
		video.setIdVideo(Long.parseLong("1"));
		String json = gson.toJson(video);
		
		when(videoRepository.save(video)).thenReturn(video);
		this.mockMvc.perform(put("/videos").content(json)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	void deletarVideo() throws Exception {
		Video video1 = new Video(Long.parseLong("1"),"testando","teste", "");
		Mockito.when(videoService.findById(video1.getIdVideo())).thenReturn(video1);
		this.mockMvc.perform(delete("/videos/1")).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void deletarVideoNotFound() throws Exception {
		Video video1 = new Video(Long.parseLong("1"),"testando","teste", "");
		Mockito.when(videoService.findById(video1.getIdVideo())).thenReturn(video1);
		this.mockMvc.perform(delete("/videos/2")).andExpect(MockMvcResultMatchers.status().isNotFound());
	}

}
