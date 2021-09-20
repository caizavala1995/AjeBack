package com.function.app.functionmicroservice.common;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LambdaRequest {
	
	private String metodo;
	
	private Map<String, String> parametros;
	
	private String entidad;
	
	private Map<String, Object> data;

	


}
