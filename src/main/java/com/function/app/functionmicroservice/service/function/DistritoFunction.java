package com.function.app.functionmicroservice.service.function;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;
import com.function.app.functionmicroservice.service.DistritoService;


import lombok.AllArgsConstructor;


@Component(value = "distrito")
@AllArgsConstructor
public class DistritoFunction implements Function<LambdaRequest, LambdaResponse> {
	
	   private DistritoService distritoservice;

	    @Override
	    public LambdaResponse apply(LambdaRequest request) {

		    		return distritoservice.ObtenerDistrito(request);
	    		
	    	}
}