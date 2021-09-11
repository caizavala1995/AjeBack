package com.function.app.functionmicroservice.service.function;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;
import com.function.app.functionmicroservice.service.TipoDocIdent;

import lombok.AllArgsConstructor;


@Component(value = "tipodocident")
@AllArgsConstructor
public class TipoDocIdentFunction implements Function<LambdaRequest, LambdaResponse> {
	
	   private TipoDocIdent tipoDocIdent;

	    @Override
	    public LambdaResponse apply(LambdaRequest request) {

		    		return tipoDocIdent.ObtenerTipoDocIdent(request);
	    		
	    	}
}
