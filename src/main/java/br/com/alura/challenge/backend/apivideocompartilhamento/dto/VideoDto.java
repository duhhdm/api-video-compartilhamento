package br.com.alura.challenge.backend.apivideocompartilhamento.dto;

import javax.validation.constraints.NotEmpty;

import br.com.alura.challenge.backend.apivideocompartilhamento.domain.Video;


public class VideoDto {
	
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

	public Video converterVideoPost(VideoDto videoDto) {
		Video video = new Video(null,videoDto.getDsTitulo(),videoDto.getDsVideo(),videoDto.getDsUrl());
		return video;
	}
}
