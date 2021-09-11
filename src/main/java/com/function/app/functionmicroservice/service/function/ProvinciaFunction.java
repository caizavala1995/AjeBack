package com.function.app.functionmicroservice.service.function;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;
import com.function.app.functionmicroservice.service.ProvinciaService;

import lombok.AllArgsConstructor;


@Component(value = "provinciaA")
@AllArgsConstructor
public class ProvinciaFunction implements Function<LambdaRequest, LambdaResponse> {
	
	   private ProvinciaService provinciaservice;

	    @Override
	    public LambdaResponse apply(LambdaRequest request) {

		    		return provinciaservice.ObtenerProvincia(request);
	    		
	    	}
}
