package com.function.app.functionmicroservice.service;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;

public interface BodegueroService {
	LambdaResponse ObtenerBodeguero(LambdaRequest lambdaRequest);
	LambdaResponse CrearBodeguero(LambdaRequest lambdaRequest);
	LambdaResponse ActualizarrBodeguero(LambdaRequest lambdaRequest);
	LambdaResponse EliminarBodeguero(LambdaRequest lambdaRequest);
}
