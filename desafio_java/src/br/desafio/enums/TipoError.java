package br.desafio.enums;

public enum TipoError {
	JAVA("Java");
	
	private String descricao;
	
	private TipoError(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
