package com.function.app.functionmicroservice.service.function;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;
import com.function.app.functionmicroservice.service.MaestroService;

import lombok.AllArgsConstructor;


@Component(value = "maestro")
@AllArgsConstructor
public class MaestroFunction implements Function<LambdaRequest, LambdaResponse> {
	
	   private MaestroService maestro;

	    @Override
	    public LambdaResponse apply(LambdaRequest request) {

		    		return maestro.ObtenerMaestro(request);
	    		
	    	}
}