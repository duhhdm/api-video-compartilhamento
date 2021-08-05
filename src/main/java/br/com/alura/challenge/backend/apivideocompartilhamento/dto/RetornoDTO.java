package br.com.alura.challenge.backend.apivideocompartilhamento.dto;

public class RetornoDTO {
	
	private String msg;
	private Integer status;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public RetornoDTO(String msg, Integer status) {
		super();
		this.msg = msg;
		this.status = status;
	}
	
	
	
}
