package com.function.app.functionmicroservice.service.function;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;
import com.function.app.functionmicroservice.service.BodegueroService;

import lombok.AllArgsConstructor;


@Component(value = "bodeguero")
@AllArgsConstructor
public class BodegueroFunction implements Function<LambdaRequest, LambdaResponse> {
	
	   private BodegueroService BodegueroFunction;

	    @Override
	    public LambdaResponse apply(LambdaRequest request) {

	    	if(request.getMetodo().equals("POST")) {
	    		return BodegueroFunction.CrearBodeguero(request);
	    	}else {
	    		if(request.getMetodo().equals("GET")) {
	    			return BodegueroFunction.ObtenerBodeguero(request);
	    		}else {
	    			if (request.getMetodo().equals("PUT")) {
	    				return BodegueroFunction.ActualizarrBodeguero(request);
	    			}else {
	    				return BodegueroFunction.EliminarBodeguero(request);
	    			}
	    		}
	    	}
		    		
	    }
}