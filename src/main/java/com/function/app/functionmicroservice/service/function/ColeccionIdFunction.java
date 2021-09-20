package com.function.app.functionmicroservice.service.function;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;
import com.function.app.functionmicroservice.service.ColeccionId;

import lombok.AllArgsConstructor;


@Component(value = "coleccionid")
@AllArgsConstructor
public class ColeccionIdFunction implements Function<LambdaRequest, LambdaResponse> {
	
	   private ColeccionId coleccionId;

	    @Override
	    public LambdaResponse apply(LambdaRequest request) {

		    		return coleccionId.ObtenerColeccionId(request);
	    		
	    	}
}