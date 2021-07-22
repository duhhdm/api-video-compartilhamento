package br.com.alura.challenge.backend.apivideocompartilhamento.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbVideo")
public class Video{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idVideo")
	private Long idVideo;
	
	@Column(name="dsTitulo")
	private String dsTitulo;
	
	@Column(name="dsVideo")
	private String dsVideo;
	
	@Column(name="dsUrl")
	private String dsUrl;
	
	public Video() {
		
	}

	public Video(Long idVideo, String dsTitulo, String dsVideo, String dsUrl) {
		super();
		this.idVideo = idVideo;
		this.dsTitulo = dsTitulo;
		this.dsVideo = dsVideo;
		this.dsUrl = dsUrl;
	}

	public Long getIdVideo() {
		return idVideo;
	}

	public void setIdVideo(Long idVideo) {
		this.idVideo = idVideo;
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
	
	
}
