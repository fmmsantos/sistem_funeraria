package com.sistem_funeraria.repository;

import org.springframework.stereotype.Repository;

import com.sistem_funeraria.config.jpa.CrudRepositoryBean;
import com.sistem_funeraria.model.Bairro;

@Repository
public class BairroRepository extends CrudRepositoryBean<Bairro> {
	public BairroRepository() {
		super(Bairro.class);

	}
}
