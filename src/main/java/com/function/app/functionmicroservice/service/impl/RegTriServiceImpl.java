package com.function.app.functionmicroservice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;
import com.function.app.functionmicroservice.service.RegTribService;

@Service
public class RegTriServiceImpl implements RegTribService{
	
	@Autowired
	private JdbcTemplate jdbcTemplate; 

	@Override
	public LambdaResponse ObtenerRegTri(LambdaRequest lambdaRequest) {
		// TODO Auto-generated method stub
		Map<String,Object> m=new HashMap<String,Object>();
		   
		 String sql="";
		 
		 sql=" "
				  +" select CodMaestro as codigo, \r\n" 
				  +"NombreTabla as tabla, \r\n" 
				  +"DescMaestro as descripcion \r\n"
				  +"from Maestros \r\n"
				  +"where NombreTabla = 'Regimen Tributario' \r\n"
				  +"and EstMaestro = '1' \r\n"
		  		;

	  List<Map<String, Object>> lstregtri = jdbcTemplate.queryForList(sql);
	  m.put("regtri", lstregtri);

	  return LambdaResponse.builder().mensaje("OK").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).data(m).build();
	 
	}

}
