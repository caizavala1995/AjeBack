package com.function.app.functionmicroservice.service.function;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;
import com.function.app.functionmicroservice.service.TipoNegocioService;

import lombok.AllArgsConstructor;


@Component(value = "tiponegocio")
@AllArgsConstructor
public class TipoNegocioFunction implements Function<LambdaRequest, LambdaResponse> {
	
	   private TipoNegocioService tipoNegocioService;

	    @Override
	    public LambdaResponse apply(LambdaRequest request) {

		    		return tipoNegocioService.ObtenerTipoNegocio(request);
	    		
	    	}
}