package com.function.app.functionmicroservice.service.function;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;
import com.function.app.functionmicroservice.service.TipoDireccionService;

import lombok.AllArgsConstructor;


@Component(value = "tipodireccion")
@AllArgsConstructor
public class TipoDireccionFunction implements Function<LambdaRequest, LambdaResponse> {
	
	   private TipoDireccionService tipoDireccionService;

	    @Override
	    public LambdaResponse apply(LambdaRequest request) {

		    		return tipoDireccionService.ObtenerTipoDireccion(request);
	    		
	    	}
}

