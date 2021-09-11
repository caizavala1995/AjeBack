package com.function.app.functionmicroservice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;
import com.function.app.functionmicroservice.service.TipoUsuarioService;

@Service
public class TipoUsuarioServiceImpl implements TipoUsuarioService{

	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
	@Override
	public LambdaResponse ObtenerTipoUsuario(LambdaRequest lambdaRequest) {
		// TODO Auto-generated method stub
		Map<String,Object> m=new HashMap<String,Object>();
		   
		 String sql="";
		 
		 sql=" "
				  +" select CodMaestro as codigo,\r\n" 
				  +"NombreTabla as tabla, \r\n" 
				  +"DescMaestro as descripcion \r\n"
				  +"from Maestros \r\n"
				  +"where NombreTabla = 'Tipo Usuario' \r\n"
				  +"and EstMaestro = '1' \r\n"
		  		;

	  List<Map<String, Object>> lstTipoUsuario = jdbcTemplate.queryForList(sql);
	  m.put("tipousuario", lstTipoUsuario);

	  return LambdaResponse.builder().mensaje("OK").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).data(m).build();
	 
	}

}
