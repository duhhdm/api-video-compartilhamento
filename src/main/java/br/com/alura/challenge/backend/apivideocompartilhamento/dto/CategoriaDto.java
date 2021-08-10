package br.com.alura.challenge.backend.apivideocompartilhamento.dto;

import javax.validation.constraints.NotEmpty;

import br.com.alura.challenge.backend.apivideocompartilhamento.domain.Categoria;
import br.com.alura.challenge.backend.apivideocompartilhamento.dto.en.CorEnum;

public class CategoriaDto {
	
	@NotEmpty(message = "Campo Titulo obrigatorio")
	private String dsTitulo;
	@NotEmpty(message = "Campo Cor obrigatorio")
	private CorEnum cor;
	
	private Integer idCategoria;
	
	public CategoriaDto(String dsTitulo, CorEnum cor) {
		super();
		this.dsTitulo = dsTitulo;
		this.cor = cor;
	}

	public String getDsTitulo() {
		return dsTitulo;
	}

	public void setDsTitulo(String dsTitulo) {
		this.dsTitulo = dsTitulo;
	}

	public CorEnum getCor() {
		return cor;
	}

	public void setCor(CorEnum cor) {
		this.cor = cor;
	}
	
	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
	
	public Categoria converterCategoria(CategoriaDto categoriaDto) {
		return new Categoria(null,categoriaDto.getDsTitulo(),categoriaDto.getCor());
	}
	
	public Categoria converterCategoriaInput(CategoriaDto categoriaDto) {
		return new Categoria(categoriaDto.getIdCategoria(),categoriaDto.getDsTitulo(),categoriaDto.getCor());
	}

	
}
