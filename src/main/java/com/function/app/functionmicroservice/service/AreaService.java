package com.function.app.functionmicroservice.service;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;
import com.function.app.functionmicroservice.model.UsuarioRq;
import com.function.app.functionmicroservice.model.UsuarioRs;


public interface AreaService {
	
	
	LambdaResponse ObtenerArea(LambdaRequest lambdaRequest);
	

}