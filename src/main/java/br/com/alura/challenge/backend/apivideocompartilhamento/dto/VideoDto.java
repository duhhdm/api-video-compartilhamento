package br.com.alura.challenge.backend.apivideocompartilhamento.dto;

import javax.validation.constraints.NotEmpty;

import br.com.alura.challenge.backend.apivideocompartilhamento.domain.Video;


public class VideoDto {
	
	private Long idTitulo;
	
	@NotEmpty(message = "Campo Titulo obrigatorio")
	private String dsTitulo;
	
	@NotEmpty(message = "Campo Video obrigatorio")
	private String dsVideo;
	
	@NotEmpty(message = "Campo Url obrigatorio")
	private String dsUrl;
	
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

	public VideoDto(String dsTitulo, String dsVideo, String dsUrl) {
		super();
		this.dsTitulo = dsTitulo;
		this.dsVideo = dsVideo;
		this.dsUrl = dsUrl;
	}
	
	public VideoDto(Long idTitulo, String dsTitulo, String dsVideo, String dsUrl) {
		super();
		this.idTitulo = idTitulo;
		this.dsTitulo = dsTitulo;
		this.dsVideo = dsVideo;
		this.dsUrl = dsUrl;
	}

	public Video converterVideoPost(VideoDto videoDto) {
		return new Video(null,videoDto.getDsTitulo(),videoDto.getDsVideo(),videoDto.getDsUrl());
	}
	
	public Video converterVideoPut(VideoDto videoDto) {
		return new Video(videoDto.getIdTitulo(),videoDto.getDsTitulo(),videoDto.getDsVideo(),videoDto.getDsUrl());
	}
}
