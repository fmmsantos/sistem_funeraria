package com.sistem_funeraria.config;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * Esta classe serve para printar no log todos os servicos REST mapeados.
 * Importante para verificar se estão mapeados corretamente
 * 
 * @author Frank Santos
 *
 */
@Component
public class PrintAllEndpoints implements ApplicationListener<ApplicationEvent> {
	
	static Logger LOGGER = LoggerFactory.getLogger(PrintAllEndpoints.class);
	
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            ApplicationContext applicationContext = ((ContextRefreshedEvent) event).getApplicationContext();
            Map<RequestMappingInfo, HandlerMethod> methods = applicationContext.getBean(RequestMappingHandlerMapping.class).getHandlerMethods();
            methods.forEach((key, value) -> {
            	LOGGER.info("REST Endpoint Configured: " + key);
            });
        }
    }
}
    
