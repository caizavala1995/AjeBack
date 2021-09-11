package com.function.app.functionmicroservice.service.function;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;
import com.function.app.functionmicroservice.service.AreaService;

import lombok.AllArgsConstructor;


@Component(value = "area")
@AllArgsConstructor
public class AreaFuntion implements Function<LambdaRequest, LambdaResponse> {
	
	   private AreaService areaservice;

	    @Override
	    public LambdaResponse apply(LambdaRequest request) {

		    		return areaservice.ObtenerArea(request);
	    		
	    	}
}