package br.com.alura.challenge.backend.apivideocompartilhamento.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.alura.challenge.backend.apivideocompartilhamento.dto.en.CorEnum;

@Entity
@Table(name="tbCategoria")
public class Categoria {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idCategoria")
	private Integer idCategoria;
	
	@Column(name="dsTitulo")
	private String dsTitulo;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="idCor")
	private CorEnum idCor;
	
	
	
	public Categoria() {
		
	}

	public Categoria(Integer idCategoria, String dsTitulo, CorEnum idCor) {
		super();
		this.idCategoria = idCategoria;
		this.dsTitulo = dsTitulo;
		this.idCor = idCor;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdColuna(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getDsTitulo() {
		return dsTitulo;
	}

	public void setDsTitulo(String dsTitulo) {
		this.dsTitulo = dsTitulo;
	}

	public CorEnum getDsCor() {
		return idCor;
	}

	public void setDsCor(CorEnum idCor) {
		this.idCor = idCor;
	}
	
	
}
