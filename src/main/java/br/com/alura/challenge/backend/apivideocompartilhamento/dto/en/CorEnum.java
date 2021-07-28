package br.com.alura.challenge.backend.apivideocompartilhamento.dto.en;


public enum CorEnum {
	
	AZUL(0),VERMELHO(1),VERDE(2), AMARELO(3);

	private final int valor;
	
	CorEnum(int i) {
		this.valor=i;
	}
	
	public int getValor() {
		return valor;
	}
}
