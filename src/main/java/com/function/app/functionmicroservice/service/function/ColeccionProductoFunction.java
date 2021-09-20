package com.function.app.functionmicroservice.service.function;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;
import com.function.app.functionmicroservice.service.ColeccionId;
import com.function.app.functionmicroservice.service.ColeccionProdService;

import lombok.AllArgsConstructor;


@Component(value = "coleccionproductoId")
@AllArgsConstructor
public class ColeccionProductoFunction implements Function<LambdaRequest, LambdaResponse> {
	
	   private ColeccionProdService coleccionproducto;

	    @Override
	    public LambdaResponse apply(LambdaRequest request) {

		    		return coleccionproducto.ObtenerColeccionProdid(request);
	    		
	    	}
}