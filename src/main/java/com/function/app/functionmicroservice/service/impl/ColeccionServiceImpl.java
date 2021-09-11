package com.function.app.functionmicroservice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;
import com.function.app.functionmicroservice.service.ColeccionService;

@Service
public class ColeccionServiceImpl implements ColeccionService{

	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
	@Override
	public LambdaResponse ObtenerColeccion(LambdaRequest lambdaRequest) {
		// TODO Auto-generated method stub
		Map<String,Object> m=new HashMap<String,Object>();
		   
		 String sql="";
		 
	   sql=" "
			  +" select  c.NomCol as NombreColeccion, \r\n" 
			  +"c.DesCol as DescColeccion \r\n" 
			  +"from Coleccion c \r\n"
			  +"where c.estCcol=1 \r\n"

	  		;


	  List<Map<String, Object>> lstColeccion = jdbcTemplate.queryForList(sql);
	  m.put("coleccion", lstColeccion);

	  return LambdaResponse.builder().mensaje("OK").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).data(m).build();
	 
	}

}
