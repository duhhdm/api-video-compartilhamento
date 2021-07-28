package br.com.alura.challenge.backend.apivideocompartilhamento.dto;

import br.com.alura.challenge.backend.apivideocompartilhamento.domain.Categoria;
import br.com.alura.challenge.backend.apivideocompartilhamento.dto.en.CorEnum;

public class CategoriaDto {
	
	private String dsTitulo;
	private CorEnum cor;
	
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
	
	public Categoria converterCategoria(CategoriaDto categoriaDto) {
		return new Categoria(null,categoriaDto.getDsTitulo(),categoriaDto.getCor());
	}
	
	
}
