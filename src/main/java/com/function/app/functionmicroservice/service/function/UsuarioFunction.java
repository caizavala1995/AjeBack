package com.function.app.functionmicroservice.service.function;

import java.util.function.Function;

import org.springframework.stereotype.Component;


import com.function.app.functionmicroservice.model.UsuarioRq;
import com.function.app.functionmicroservice.model.UsuarioRs;
import com.function.app.functionmicroservice.service.UsuarioService;

import lombok.AllArgsConstructor;

@Component(value = "usuario")
@AllArgsConstructor
public class UsuarioFunction implements Function<UsuarioRq, UsuarioRs> {

    private UsuarioService usuarioService;

    @Override
    public UsuarioRs apply(UsuarioRq request) {
        return usuarioService.validarUsuario(request);
    	
    }

}
