package br.com.alura.challenge.backend.apivideocompartilhamento.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.alura.challenge.backend.apivideocompartilhamento.domain.Video;


public class VideoDto {
	
	private Long idTitulo;
	
	@NotEmpty(message = "Campo Titulo obrigatorio")
	private String dsTitulo;
	
	@NotEmpty(message = "Campo Video obrigatorio")
	private String dsVideo;
	
	@NotEmpty(message = "Campo Url obrigatorio")
	private String dsUrl;
	
	@NotNull(message = "Campo id Categoria obrigatorio")
	private Integer idCategoria;
	
	public VideoDto() {
		
	}
	
	public String getDsTitulo() {
		return dsTitulo;
	}

	public void setDsTitulo(String dsTitulo) {
		this.dsTitulo = dsTitulo;
	}
	
	public Long getIdTitulo() {
		return idTitulo;
	}

	public void setIdTitulo(Long idTitulo) {
		this.idTitulo = idTitulo;
	}

	public String getDsVideo() {
		return dsVideo;
	}

	public void setDsVideo(String dsVideo) {
		this.dsVideo = dsVideo;
	}

	public String getDsUrl() {
		return dsUrl;
	}

	public void setDsUrl(String dsUrl) {
		this.dsUrl = dsUrl;
	}
	
	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public VideoDto(Long idTitulo, @NotEmpty(message = "Campo Titulo obrigatorio") String dsTitulo,
			@NotEmpty(message = "Campo Video obrigatorio") String dsVideo,
			@NotEmpty(message = "Campo Url obrigatorio") String dsUrl,
			@NotNull(message = "Campo id Categoria obrigatorio") Integer idCategoria) {
		super();
		this.idTitulo = idTitulo;
		this.dsTitulo = dsTitulo;
		this.dsVideo = dsVideo;
		this.dsUrl = dsUrl;
		this.idCategoria = idCategoria;
	}

	public Video converterVideoPost(VideoDto videoDto) {
		return new Video(null,videoDto.getDsTitulo(),videoDto.getDsVideo(),videoDto.getDsUrl(),null);
	}
	
}
