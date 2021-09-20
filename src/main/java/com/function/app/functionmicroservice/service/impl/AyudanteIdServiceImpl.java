package com.function.app.functionmicroservice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;
import com.function.app.functionmicroservice.service.AyudanteId;

@Service
public class AyudanteIdServiceImpl implements AyudanteId{
	
	@Autowired
	private JdbcTemplate jdbcTemplate; 

	@Override
	public LambdaResponse ObtenerAyudante(LambdaRequest lambdaRequest) {
		Map<String,Object> m=new HashMap<String,Object>();
		   
		 String sql="";
		 
		 sql=" "
				  +"select IdUsu as id,\r\n" 
				  +"NomUsu as nombres, \r\n" 
				  +"ApePatUsu as paterno, \r\n"
				  +"ApeMatUsu as materno, \r\n"
				  +"TipDocUsu as tipoDoc, \r\n"
				  +"NroDocUsu as nroDoc, \r\n" 
				  +"CorUsu as correo1, \r\n" 
				  +"TelUsu as tel1, \r\n" 
				  +"Tel2Usu as tel2 \r\n" 
				  +"from Usuario  \r\n" 
		  		;
		   
		   if(lambdaRequest.getParametros().get("idayudante")!=null) {
				sql=sql+ " where IdUsu ="+lambdaRequest.getParametros().get("idayudante")+" ";
			 }
		   
	  List<Map<String, Object>> ayudate = jdbcTemplate.queryForList(sql);
	  m.put("ayudanteid", ayudate);

	  return LambdaResponse.builder().mensaje("OK").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).data(m).build();
	 
	}

}
