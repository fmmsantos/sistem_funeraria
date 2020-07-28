package com.sistem_funeraria.config.jpa;

public enum Operador implements java.io.Serializable {
	
	
	IGUAL("="),
	MAIOR_IGUAL(">="),
	MENOR_IGUAL("<="),
	DIFERENTE("!="),
	PARECIDO("LIKE"),
	MAIOR(">"),
	MENOR("<");
	
	private String operador;
	
	Operador(String operador){
		this.operador = operador;
	}

	public String getOperador() {
		return operador;
	}
}
