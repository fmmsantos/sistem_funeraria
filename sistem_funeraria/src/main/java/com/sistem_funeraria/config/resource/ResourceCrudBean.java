package com.sistem_funeraria.config.resource;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistem_funeraria.config.jpa.CrudRepositoryBean;
import com.sistem_funeraria.config.jpa.RequestPage;



@RestController
@RequestMapping("/")
public class ResourceCrudBean<T> implements ResourceCrud<T> {

	@Autowired
	protected CrudRepositoryBean<T> crudRepositoryBean;

	
	public T inserir(@RequestBody T entidade) {
		entidade = crudRepositoryBean.inserir(entidade);
		return entidade;
	}
	
	public List<T> listar() {
		List<T> list = crudRepositoryBean.listar();
		return list;
	}

	public T buscar(@PathVariable Integer id) {
		T grupoUsuario = crudRepositoryBean.buscar(id);
		return grupoUsuario;
	}

	public Boolean deletar(@PathVariable Integer id) {
		Boolean retorno = crudRepositoryBean.deletar(id);
		return retorno;
	}

	public T atualizar(@PathVariable Integer id, @RequestBody T entidade) {
		T entidadeBase = buscar(id);
		BeanUtils.copyProperties(entidade, entidadeBase, "id");
		entidadeBase = crudRepositoryBean.atualizar(entidadeBase);
		return entidadeBase;
	}
//
//	public Page<T> consultar(Pageable pageable) {
//		Page<T> listaPaginada = crudRepositoryBean.consultar(pageable);
//		return listaPaginada;
//	}

	/*@JsonView(JsonBasic.class)
	public Page<T> consultar(@RequestBody RequestPage requestPage) {
		return crudRepositoryBean.consultar(requestPage);
	}
*/
	public T detalhar(@PathVariable Integer id) {
		return crudRepositoryBean.detalhar(id);
	}
	
	public Page<T> consultar(@RequestBody RequestPage requestPage) {
		return crudRepositoryBean.consultar(requestPage);
	}
}
