package com.sistem_funeraria.config.jpa;

import java.util.List;

public interface CrudRepository<T> {
	T inserir(T entity);
	
	T atualizar(T entity);
	
	T detalhar(Integer id);
	
	T buscar(Integer id);
	
	Boolean deletar(Integer id);
	
	List<T> listar();
	
	//Page<T> consultar(Pageable paginador);
}
