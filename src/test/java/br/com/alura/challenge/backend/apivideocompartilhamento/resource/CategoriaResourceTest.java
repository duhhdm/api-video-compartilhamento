package br.com.alura.challenge.backend.apivideocompartilhamento.resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.alura.challenge.backend.apivideocompartilhamento.domain.Categoria;
import br.com.alura.challenge.backend.apivideocompartilhamento.dto.en.CorEnum;
import br.com.alura.challenge.backend.apivideocompartilhamento.service.CategoriaService;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CategoriaResourceTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	CategoriaService categoriaService;
	
	
	
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
	
	

}
