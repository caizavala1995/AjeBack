package com.function.app.functionmicroservice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;
import com.function.app.functionmicroservice.service.DepartamentoService;


@Service
public class DepartamentoServiceImpl implements DepartamentoService{

	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
	@Override
	public LambdaResponse ObtenerDepartamento(LambdaRequest lambdaRequest) {
		// TODO Auto-generated method stub
		Map<String,Object> m=new HashMap<String,Object>();
		   
		 String sql="";
		 
	   sql=" "
			  +" select IdDep as id,\r\n" 
			  +"NomDep as nombre\r\n" 
			  +"from Departamento j\r\n"
	  		;


	  List<Map<String, Object>> lstDepartamento = jdbcTemplate.queryForList(sql);
	  m.put("departamentoA", lstDepartamento);

	  return LambdaResponse.builder().mensaje("OK").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).data(m).build();
	 
	}
}

