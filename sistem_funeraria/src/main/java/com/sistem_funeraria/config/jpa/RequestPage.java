package com.sistem_funeraria.config.jpa;

import java.util.List;

import com.sistem_funeraria.config.jsonview.JsonBasic;
import com.fasterxml.jackson.annotation.JsonView;

public class RequestPage<T> implements java.io.Serializable {
	private static final long serialVersionUID = 8188292048028418096L;

	@JsonView(JsonBasic.class)
	private T filtros;

	@JsonView(JsonBasic.class)
	private Integer page;

	@JsonView(JsonBasic.class)
	private Integer size;

	@JsonView(JsonBasic.class)
	private List<Filtro> parametros;
	
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public T getFiltros() {
		return filtros;
	}

	public void setFiltros(T filtros) {
		this.filtros = filtros;
	}

	public List<Filtro> getParametros() {
		return parametros;
	}

	public void setParametros(List<Filtro> parametros) {
		this.parametros = parametros;
	}
}
