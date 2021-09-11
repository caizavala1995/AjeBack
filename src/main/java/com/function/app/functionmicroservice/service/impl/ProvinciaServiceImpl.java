package com.function.app.functionmicroservice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;
import com.function.app.functionmicroservice.service.ProvinciaService;

@Service
public class ProvinciaServiceImpl implements ProvinciaService{
	
	@Autowired
	private JdbcTemplate jdbcTemplate; 

	@Override
	public LambdaResponse ObtenerProvincia(LambdaRequest lambdaRequest) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				Map<String,Object> m=new HashMap<String,Object>();
				   
				 String sql="";
				 
			   sql=" "
					  +"select IdProv as id,\r\n" 
					  +"NomProv as nombreProvincia \r\n" 
					  +"from Provincia p \r\n"
					  +"inner join Departamento d \r\n"
					  +"on p.IdDepProv = d.IdDep \r\n"
			  		;
			   
			   if(lambdaRequest.getParametros().get("IdDepProvincia")!=null) {
					sql=sql+ " where p.IdDepProv = "+lambdaRequest.getParametros().get("IdDepProvincia")+" ";
				 }


			  List<Map<String, Object>> lstProvincia = jdbcTemplate.queryForList(sql);
			  m.put("provinciaA", lstProvincia);

			  return LambdaResponse.builder().mensaje("OK").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).data(m).build();
			 
	}

}
