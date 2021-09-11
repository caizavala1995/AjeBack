package com.function.app.functionmicroservice.service;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;

public interface ProdColeccService {
	
	LambdaResponse ObtenerProdColeccion(LambdaRequest lambdaRequest);
	
	LambdaResponse crearProdColeccion(LambdaRequest lambdaRequest);
	
	LambdaResponse actualizarProdColeccion(LambdaRequest lambdaRequest);
	
	LambdaResponse eliminarProdColeccion(LambdaRequest lambdaRequest);


}
