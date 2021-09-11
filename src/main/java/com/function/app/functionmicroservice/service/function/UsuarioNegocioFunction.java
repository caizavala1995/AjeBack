package com.function.app.functionmicroservice.service.function;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;
import com.function.app.functionmicroservice.service.UsuarioService;

import lombok.AllArgsConstructor;

@Component(value = "usuarionegocio")
@AllArgsConstructor
public class UsuarioNegocioFunction implements Function<LambdaRequest, LambdaResponse> {
	
	   private UsuarioService usuarioService;

	    @Override
	    public LambdaResponse apply(LambdaRequest request) {
	    	if(request.getMetodo().equals("POST")) {
	    		return usuarioService.registrarNegocio(request);
	    	}else {
		    	if(request.getMetodo().equals("GET")) {
		    		return usuarioService.obtenerNegocio(request);
		    		 
		    	}else {
		    		return null;
		    	}
	    		
	    	}

	    }


}
