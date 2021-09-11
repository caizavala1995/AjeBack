package com.function.app.functionmicroservice.service.function;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;
import com.function.app.functionmicroservice.service.ProdColeccService;

import lombok.AllArgsConstructor;


@Component(value = "prodCol")
@AllArgsConstructor
public class ProdColeccFunction implements Function<LambdaRequest, LambdaResponse> {
	
	   private ProdColeccService coProdColeccService;

	    @Override
	    public LambdaResponse apply(LambdaRequest request) {

		    		//return distritoservice.ObtenerDistrito(request);
		    		
		    		
		    		
		    		if(request.getMetodo().equals("POST")) {
			    		return coProdColeccService.crearProdColeccion(request);
			    	}else {
				    	if(request.getMetodo().equals("GET")) {
				    		return coProdColeccService.ObtenerProdColeccion(request);
				    		 
				    	}else {
				    		if (request.getMetodo().equals("PUT")) {
				    			return coProdColeccService.actualizarProdColeccion(request);
				    		}else {
				    			return coProdColeccService.eliminarProdColeccion(request);
				    		}
				    	}
			    		
			    	}
	    		
	    	}
}
