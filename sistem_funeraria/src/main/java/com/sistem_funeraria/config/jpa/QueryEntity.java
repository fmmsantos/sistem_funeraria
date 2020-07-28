package com.sistem_funeraria.config.jpa;

import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class QueryEntity<T> {
	EntityManager em;
	
	private  Query queryFinal;
	
	public QueryEntity(EntityManager em) {
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> execute(RequestPage requestPage , Class<T> clazz){
		CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(clazz);
        Root root = query.from(clazz);
 
        if (requestPage == null || requestPage.getFiltros() ==null  ) {
        	return em.createQuery(query).getResultList();
        }
        
        @SuppressWarnings("rawtypes")
		LinkedHashMap filtros  = ((LinkedHashMap)requestPage.getFiltros());
        
        Predicate predicate = builder.conjunction();
        
        for (Object key : filtros.keySet()) {
        	
        	if (filtros.get(key) == null || filtros.get(key).toString().trim().length() ==0) {
        		continue;
        	}
        	System.out.println("Key:" + key);
        	System.out.println("Value:" + filtros.get(key) );
        	
        	if (filtros.get(key) instanceof LinkedHashMap ) {
        		LinkedHashMap linkedHashMap  = (LinkedHashMap) filtros.get(key);
        		if (linkedHashMap.isEmpty()) {
        			continue;
        		}
        	}
        	
        	Filtro filtro = new Filtro();
        	filtro.setCampo(key.toString());
        	filtro.setValor(filtros.get(key));
        	
        	filtro.setOperador(Operador.IGUAL);
        	
			if (filtro.getOperador().equals(Operador.MAIOR)) {
				predicate = builder.and(predicate, builder.greaterThanOrEqualTo(root.get(filtro.getCampo()), filtro.getValor().toString()));
			} else if (filtro.getOperador().equals(Operador.MENOR)) {
				predicate = builder.and(predicate, builder.lessThanOrEqualTo(root.get(filtro.getCampo()), filtro.getValor().toString()));
			} else if (filtro.getOperador().equals(Operador.IGUAL)) {
				if (root.get(filtro.getCampo()).getJavaType() == String.class) {
					predicate = builder.and(predicate, builder.like(root.get(filtro.getCampo()), "%" + filtro.getValor() + "%"));
				} else {
					predicate = builder.and(predicate, builder.equal(root.get(filtro.getCampo()), filtro.getValor()));
				}
			}
		}

        query.where(predicate);
        queryFinal = em.createQuery(query);
        aplicarRegrasPaginacao(queryFinal, requestPage);
        
        List<T> result = queryFinal.getResultList();
        return result;
	}
	
	private void aplicarRegrasPaginacao(Query query, RequestPage<T>  requestPage) {
		
		int paginalAtual = 0;
		int totalRegistroPorPagina = 0;
		
		if (requestPage.getSize() > 0 ) {
			totalRegistroPorPagina = Integer.valueOf(requestPage.getSize());
        }
		
		if (requestPage.getPage()  > 0 ) {
			paginalAtual = Integer.valueOf(requestPage.getPage());
        }
		
		query.setFirstResult(paginalAtual * totalRegistroPorPagina);
		query.setMaxResults(totalRegistroPorPagina);
	}

	public Query getQueryFinal() {
		return queryFinal;
	}

	public void setQueryFinal(Query queryFinal) {
		this.queryFinal = queryFinal;
	}
}