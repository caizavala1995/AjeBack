package com.function.app.functionmicroservice.service;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;
import com.function.app.functionmicroservice.model.UsuarioRq;
import com.function.app.functionmicroservice.model.UsuarioRs;

public interface UsuarioService {
		
		UsuarioRs validarUsuario(UsuarioRq usuarioRq);
		
		LambdaResponse registrarNegocio(LambdaRequest lambdaRequest);
		
		LambdaResponse obtenerNegocio(LambdaRequest lambdaRequest);
		
		LambdaResponse obtenerDepartamentos(LambdaRequest lambdaRequest);

}
