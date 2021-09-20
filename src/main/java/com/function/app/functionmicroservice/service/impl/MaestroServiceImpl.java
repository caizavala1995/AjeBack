package com.function.app.functionmicroservice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;
import com.function.app.functionmicroservice.service.MaestroService;

@Service
public class MaestroServiceImpl implements MaestroService{
	
	@Autowired
	private JdbcTemplate jdbcTemplate; 

	@Override
	public LambdaResponse ObtenerMaestro(LambdaRequest lambdaRequest) {
		// TODO Auto-generated method stub
		Map<String,Object> m=new HashMap<String,Object>();
		   
		 String sql="";
		 
	   sql=" "
			  +"select IdMaestro as id,\r\n" 
			  +"NombreTabla as nombretabla, \r\n" 
			  +"CodMaestro as codMaestro, \r\n"
			  +"DescMaestro as descripcion \r\n"
			  +"from Maestros \r\n"
	  		;
	   
	   if(lambdaRequest.getParametros().get("nombreMaestro")!=null) {
			sql=sql+ " where NombreTabla= '"+lambdaRequest.getParametros().get("nombreMaestro")+"' ";
		 }



	  List<Map<String, Object>> lstMaestro = jdbcTemplate.queryForList(sql);
	  m.put("maestro", lstMaestro);

	  return LambdaResponse.builder().mensaje("OK").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).data(m).build();
	 
	}

}
