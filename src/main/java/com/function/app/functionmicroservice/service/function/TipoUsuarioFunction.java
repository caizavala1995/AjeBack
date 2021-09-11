package com.function.app.functionmicroservice.service.function;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;
import com.function.app.functionmicroservice.service.TipoNegocioService;
import com.function.app.functionmicroservice.service.TipoUsuarioService;

import lombok.AllArgsConstructor;


@Component(value = "tipousuario")
@AllArgsConstructor
public class TipoUsuarioFunction implements Function<LambdaRequest, LambdaResponse> {
	
	   private TipoUsuarioService tipoUsuarioService;

	    @Override
	    public LambdaResponse apply(LambdaRequest request) {

		    		return tipoUsuarioService.ObtenerTipoUsuario(request);
	    		
	    	}
}
