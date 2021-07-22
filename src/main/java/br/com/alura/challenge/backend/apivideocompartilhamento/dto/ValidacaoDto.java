package br.com.alura.challenge.backend.apivideocompartilhamento.dto;

public class ValidacaoDto {
	
	private String erro;
	private String campo;
	
	public ValidacaoDto(String erro, String campo) {
		this.erro=erro;
		this.campo=campo;
	}

	public String getErro() {
		return erro;
	}

	public String getCampo() {
		return campo;
	}
	
}
