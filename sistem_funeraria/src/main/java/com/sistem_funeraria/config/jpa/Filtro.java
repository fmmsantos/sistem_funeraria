package com.sistem_funeraria.config.jpa;

public class Filtro implements java.io.Serializable {
	private static final long serialVersionUID = 1086074173280188712L;
	
	private String campo;
	private Operador operador;
	private Object valor;

	
	public static Filtro of(String campo, Object valor) {
		Filtro filtro = new Filtro();
		filtro.setCampo(campo);
		filtro.setValor(valor);
		filtro.setOperador(Operador.IGUAL);
		return filtro;
	}
	
	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public Operador getOperador() {
		return operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}

	public Object getValor() {
		return valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}
}
