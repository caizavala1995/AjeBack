package com.function.app.functionmicroservice.service;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;

public interface ColeccionService {
	LambdaResponse ObtenerColeccion(LambdaRequest lambdaRequest);
	
	LambdaResponse CrearColeccion(LambdaRequest lambdaRequest);
	
	LambdaResponse ActualizarColeccion(LambdaRequest lambdaRequest);
	
	LambdaResponse EliminarColeccion(LambdaRequest lambdaRequest);

}
