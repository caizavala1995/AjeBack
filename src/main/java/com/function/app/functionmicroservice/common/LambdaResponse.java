package com.function.app.functionmicroservice.common;

import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LambdaResponse {
	
	private String httpCode;
	
	private String mensaje;
	
	private String appCode;
	
	private String metodo;
	
	private String entidad;
	
	private Map<String, Object> data;

}
