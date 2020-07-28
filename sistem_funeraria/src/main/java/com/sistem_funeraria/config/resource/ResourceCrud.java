package com.sistem_funeraria.config.resource;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sistem_funeraria.config.jpa.RequestPage;

@RestController
@RequestMapping("/")
public interface ResourceCrud<T> {

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/inserir", method = RequestMethod.POST)
	public T inserir(@RequestBody T entidade);

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/{id}/atualizar", method = RequestMethod.PUT)
	public T atualizar(@PathVariable("id") Integer id, @RequestBody T entidade);

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/detalhar/{id}")
	public T detalhar(@PathVariable("id") Integer id);

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public List<T> listar();

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/buscar/{id}")
	public T buscar(@PathVariable("id") Integer id);

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/deletar/{id}")
	public Boolean deletar(@PathVariable("id") Integer id);

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/consultar", method = RequestMethod.POST)
	public Page<T> consultar(RequestPage<T> requestPage);
}
