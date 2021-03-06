package com.function.app.functionmicroservice.service.function;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;
import com.function.app.functionmicroservice.service.ColeccionService;

import lombok.AllArgsConstructor;


@Component(value = "coleccion")
@AllArgsConstructor
public class ColeccionFunction implements Function<LambdaRequest, LambdaResponse> {
	
	   private ColeccionService coleccionService;

	    @Override
	    public LambdaResponse apply(LambdaRequest request) {

	    	if(request.getMetodo().equals("POST")) {
	    		return coleccionService.CrearColeccion(request);
	    	}else {
	    		if(request.getMetodo().equals("GET")) {
	    			return coleccionService.ObtenerColeccion(request);
	    		}else {
	    			if (request.getMetodo().equals("PUT")) {
	    				return coleccionService.ActualizarColeccion(request);
	    			}else {
	    				return coleccionService.EliminarColeccion(request);
	    			}
	    		}
	    	}
		    		
	    }
}