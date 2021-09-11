package com.function.app.functionmicroservice.service;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;

public interface TipoDocIdent {
	
	LambdaResponse ObtenerTipoDocIdent(LambdaRequest lambdaRequest);

}
