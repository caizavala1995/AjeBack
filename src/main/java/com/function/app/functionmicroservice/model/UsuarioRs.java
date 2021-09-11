package com.function.app.functionmicroservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioRs {
	
	private String codigoRespuesta;
	private String mensajeRespuesta;
	
	private String nombreRol;
	
	private String nombreUsuario;

}
