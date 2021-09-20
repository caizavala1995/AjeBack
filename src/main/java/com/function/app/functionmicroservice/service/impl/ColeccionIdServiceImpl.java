package com.function.app.functionmicroservice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;
import com.function.app.functionmicroservice.service.ColeccionId;

@Service
public class ColeccionIdServiceImpl implements ColeccionId{
	
	@Autowired
	private JdbcTemplate jdbcTemplate; 

	@Override
	public LambdaResponse ObtenerColeccionId(LambdaRequest lambdaRequest) {
		// TODO Auto-generated method stub
		Map<String,Object> m=new HashMap<String,Object>();
		   
		 String sql="";
		 
	   sql=" "
			  +" select IdCol as id,\r\n" 
			  +"DesCol as descripcion,\r\n" 
			  +"NomCol as nombre \r\n"
			  +" from Coleccion \r\n"

	  		;
	   
	   if(lambdaRequest.getParametros().get("idcoleccion")!=null) {
			sql=sql+ " where IdCol = "+lambdaRequest.getParametros().get("idcoleccion")+" ";
		 }
	   

	  List<Map<String, Object>> lstcoleccion = jdbcTemplate.queryForList(sql);
	  m.put("coleccionid", lstcoleccion);

	  return LambdaResponse.builder().mensaje("OK").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).data(m).build();
	 
	}

}
