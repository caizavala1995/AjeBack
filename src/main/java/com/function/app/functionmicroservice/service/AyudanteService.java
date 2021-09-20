package com.function.app.functionmicroservice.service;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;

public interface AyudanteService {
	LambdaResponse ObtenerAyudantesXnegocio(LambdaRequest lambdaRequest);
	LambdaResponse crearAyudante(LambdaRequest lambdaRequest);
	LambdaResponse ActualizarAyudantes(LambdaRequest lambdaRequest);
	LambdaResponse EliminarAyudantesXnegocio(LambdaRequest lambdaRequest);
	
}
