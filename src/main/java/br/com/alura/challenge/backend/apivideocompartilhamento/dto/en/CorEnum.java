package br.com.alura.challenge.backend.apivideocompartilhamento.dto.en;


public enum CorEnum {
	
	LIVRE(0),AZUL(1),VERMELHO(2),VERDE(3), AMARELO(4);

	private final int valor;
	
	CorEnum(int i) {
		this.valor=i;
	}
	
	public int getValor() {
		return valor;
	}
}
