package com.function.app.functionmicroservice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;
import com.function.app.functionmicroservice.service.DistritoService;


@Service
public class DistritoServiceImpl implements DistritoService{

	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
	@Override
	public LambdaResponse ObtenerDistrito(LambdaRequest lambdaRequest) {
		Map<String,Object> m=new HashMap<String,Object>();
		   
		 String sql="";
		 
		 sql=" "
				  +"select IdDis as id,\r\n" 
				  +"NomDis as nombreDistrito \r\n" 
				  +"from Distrito d \r\n"
				  +"inner join Provincia p \r\n"
				  +"on d.IdProvDis = p.IdProv \r\n"
		  		;
		   
		   if(lambdaRequest.getParametros().get("IdProvDistrito")!=null) {
				sql=sql+ " where d.IdProvDis ="+lambdaRequest.getParametros().get("IdProvDistrito")+" ";
			 }
		   
	  List<Map<String, Object>> lstDistrito = jdbcTemplate.queryForList(sql);
	  m.put("distrito", lstDistrito);

	  return LambdaResponse.builder().mensaje("OK").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).data(m).build();
	 
	}
}
