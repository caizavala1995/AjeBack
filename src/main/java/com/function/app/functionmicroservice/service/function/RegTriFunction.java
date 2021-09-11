package com.function.app.functionmicroservice.service.function;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;
import com.function.app.functionmicroservice.service.RegTribService;

import lombok.AllArgsConstructor;


@Component(value = "regtri")
@AllArgsConstructor
public class RegTriFunction implements Function<LambdaRequest, LambdaResponse> {
	
	   private RegTribService regTribService;

	    @Override
	    public LambdaResponse apply(LambdaRequest request) {

		    		return regTribService.ObtenerRegTri(request);
	    		
	    	}
}