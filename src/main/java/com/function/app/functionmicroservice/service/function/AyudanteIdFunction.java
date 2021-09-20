package com.function.app.functionmicroservice.service.function;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;
import com.function.app.functionmicroservice.service.AyudanteId;

import lombok.AllArgsConstructor;


@Component(value = "ayudanteid")
@AllArgsConstructor
public class AyudanteIdFunction implements Function<LambdaRequest, LambdaResponse> {
	
	   private AyudanteId ayudanteIdService;

	    @Override
	    public LambdaResponse apply(LambdaRequest request) {

		    		return ayudanteIdService.ObtenerAyudante(request);
	    		
	    	}
}