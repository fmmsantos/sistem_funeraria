package com.sistem_funeraria.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistem_funeraria.config.resource.ResourceCrudBean;
import com.sistem_funeraria.model.Bairro;

@RestController
@RequestMapping("/bairros")
public class BairroResource extends ResourceCrudBean<Bairro> {

}
