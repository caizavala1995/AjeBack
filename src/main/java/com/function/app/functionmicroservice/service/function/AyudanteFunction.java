package com.function.app.functionmicroservice.service.function;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;
import com.function.app.functionmicroservice.service.AyudanteService;

import lombok.AllArgsConstructor;


@Component(value = "ayudante")
@AllArgsConstructor
public class AyudanteFunction implements Function<LambdaRequest, LambdaResponse> {
	
	   private AyudanteService ayudanteService;

	    @Override
	    public LambdaResponse apply(LambdaRequest request) {

	    	if(request.getMetodo().equals("POST")) {
	    		return ayudanteService.crearAyudante(request);
	    	}else {
	    		if(request.getMetodo().equals("GET")) {
	    			return ayudanteService.ObtenerAyudantesXnegocio(request);
	    			
	    	}else {
	    			if (request.getMetodo().equals("PUT") ) {
	    				return ayudanteService.ActualizarAyudantes(request);
	    			}else {
	    				return ayudanteService.EliminarAyudantesXnegocio(request);
	    			}
	    		}
	    	}
	    }
		    		
	    
}