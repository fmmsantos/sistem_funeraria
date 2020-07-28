package com.sistem_funeraria.repository;

import org.springframework.stereotype.Repository;

import com.sistem_funeraria.config.jpa.CrudRepositoryBean;
import com.sistem_funeraria.model.Cidade;

@Repository
public class CidadeRepository extends CrudRepositoryBean<Cidade> {
	public CidadeRepository() {
		super(Cidade.class);
	}

}
