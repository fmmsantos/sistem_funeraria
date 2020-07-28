package com.sistem_funeraria.config;

import java.util.Map;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

/**
 * Essa classe eh responsavel por adicionar atributos no objeto de retorno 
 * quando acontecer um erro na requisicao
 * 
 * @author Frank Santos
 */
@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {
	
	public CustomErrorAttributes() {
		super(true);
	}
	
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        
        Exception exception = (Exception) webRequest.getAttribute("org.springframework.boot.web.servlet.error.DefaultErrorAttributes.ERROR" , RequestAttributes.SCOPE_REQUEST);
        
        errorAttributes.put("error", exception.getClass().getSimpleName());
        
        return errorAttributes;
    }
}