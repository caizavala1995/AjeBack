package com.function.app.functionmicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Negocio {
	private String idNegocio;
	
	private String nombrecomercial;
	
	private String tipodocumento;
	
	private String nrodocumento;
	
	private String correo;
	
	private String razonsocial;

}
