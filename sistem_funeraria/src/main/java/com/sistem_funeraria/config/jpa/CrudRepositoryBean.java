package com.sistem_funeraria.config.jpa;

import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CrudRepositoryBean<T> implements CrudRepository<T> {

	@PersistenceContext
	protected EntityManager em;

	private  Class<T> clazz;
	
	protected void prepare(T entity) {
	}
	
	public CrudRepositoryBean() {}
	
	@SuppressWarnings("unchecked")
	public CrudRepositoryBean(Class<T> clazz) {
		this.clazz = clazz;
	}

	public T inserir(T entity) {
		em.persist(entity);
		return entity;
	}

	public T buscar(Integer id) {

		if (id == null) {
			throw new RuntimeException(
					String.format("Erro a realizar consulta, id não informado: %s", clazz.getSimpleName()));
		}
		return em.find(clazz, id);
	}

	public List<T> listar() {
		String jpql = String.format("Select a from %s a", clazz.getSimpleName());
		List<T> list = em.createQuery(jpql, clazz).getResultList();
		return list;
	}

	public Boolean deletar(Integer id) {
		T entity = buscar(id);

		if (entity == null) {
			throw new RuntimeException(String.format("Erro,  %s , não localizado", clazz.getSimpleName()));
		}

		em.remove(entity);
		return true;
	}

	public T atualizar(T entity) {
		entity = em.merge(entity);
		return entity;
	}

	public Page<T> consultar(Pageable paginador) {
		String jpql = String.format("Select a from %s a", clazz.getSimpleName());

		Query query = em.createQuery(jpql);
		List<T> list = query.getResultList();
		int totalRegistros = list.size();

		aplicarRegrasPaginacao(query, paginador);
		list = query.getResultList();

		return new PageImpl<T>(list, paginador, totalRegistros);
	}

	private Long totalElementos() {
		try {
			String jpql = String.format("Select count(a.id) from %s a", clazz.getSimpleName());
			Query query = em.createQuery(jpql, Long.class);
			Long totalRegistros = (Long) query.getSingleResult();
			return totalRegistros;
		} catch (Exception e) {
			return 0l;
		}
	}

	private void aplicarRegrasPaginacao(Query query, Pageable paginador) {
		int paginalAtual = paginador.getPageNumber();
		int totalRegistroPorPagina = paginador.getPageSize();
		int primeiroRegistroPagina = paginalAtual * totalRegistroPorPagina;

		query.setFirstResult(primeiroRegistroPagina);
		query.setMaxResults(totalRegistroPorPagina);
	}

	public Page<T> consultar(RequestPage requestPage) {
		/*Prepare Parametros*/
		List<Filtro> parametros = requestPage.getParametros();
		if (parametros != null && parametros.isEmpty() == false ) {
			LinkedHashMap linkedHashMap  = (LinkedHashMap) requestPage.getFiltros();
			for (Filtro parametro: parametros) {
				linkedHashMap.put(parametro.getCampo(), parametro.getValor());
			}
			
			requestPage.setFiltros(linkedHashMap);
		}
		
		List<T> lista = new QueryEntity<T>(em).execute(requestPage, this.clazz);
		Long totalRegistros = totalElementos();
		Pageable pageable = new PageRequest(requestPage.getPage(), requestPage.getSize());

		return new PageImpl<T>(lista, pageable, totalRegistros);
	}

	public T detalhar(Integer id) {
		T entity = buscar(id);
		prepare(entity);
		return entity;
	}
}