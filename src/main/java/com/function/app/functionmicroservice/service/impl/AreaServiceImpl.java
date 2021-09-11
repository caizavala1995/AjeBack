package com.function.app.functionmicroservice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;

import com.function.app.functionmicroservice.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService{

	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
	@Override
	public LambdaResponse ObtenerArea(LambdaRequest lambdaRequest) {
		// TODO Auto-generated method stub
		Map<String,Object> m=new HashMap<String,Object>();
		   
		 String sql="";
		 
	   sql=" "
			  +" select j.IdJer as ID,\r\n" 
			  +"j.DesJer as Descripcion,\r\n" 
			  +"j.NivJer as Nivel,\r\n"
			  +"j.IdJerPadJer as Padre,\r\n"
			  +"j.OrdJer as Orden\r\n"
			  +"from Jerarquia j\r\n"
			  +"where j.EstJer=1\r\n"
	  		;


	  List<Map<String, Object>> lstAreas = jdbcTemplate.queryForList(sql);
	  m.put("areas", lstAreas);

	  return LambdaResponse.builder().mensaje("OK").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).data(m).build();
	 
	}

	

}
