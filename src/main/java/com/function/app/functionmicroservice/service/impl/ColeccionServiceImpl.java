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
			  +" select IdCol as id, \r\n" 
			  +"DesCol as descripcion, \r\n"
			  +"NomCol as nombre \r\n" 
			  +" from Coleccion \r\n" 
			  +"where EstCcol = 1 \r\n" 
	  		;


	  List<Map<String, Object>> lstColeccion = jdbcTemplate.queryForList(sql);
	  m.put("coleccion", lstColeccion);

	  return LambdaResponse.builder().mensaje("OK").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).data(m).build();
	 
	}

	@Override
	public LambdaResponse CrearColeccion(LambdaRequest lambdaRequest) {
		// TODO Auto-generated method stub
		String seq="select nextval('coleccion_idcol_seq'::regclass)";
		 
		 Long seqneg = jdbcTemplate.queryForObject(
	                seq,
	                Long.class);
		 jdbcTemplate.execute(seq);
	//insertamos datos en la tabla Negocio
	  String sql="insert into Coleccion\r\n"
	  		+ "	  (\r\n"
	  		+ "		IdCol,\r\n"
	  		+ "     DesCol,\r\n"
	  		+ "		NomCol,\r\n"
//	  		+ "		IdPad,\r\n"
	  		+ "		EstCcol,\r\n"
	  		+ "		FecCreCol,\r\n"
	  		+ "		UsuCreCol\r\n"
//	  		+ "		FecModCol,\r\n"
//	  		+ "		UsuModCol\r\n"
	  		+ "	  )\r\n"
	  		+ "	  values\r\n"
	  		+ "	  (\r\n"
	  		+ "	  	"+seqneg+","
			+ "     '"+((Map)lambdaRequest.getData().get("coleccion")).get("descripcioncoleccion")+"',\r\n"
			+ "		'"+((Map)lambdaRequest.getData().get("coleccion")).get("nombrecoleccion")+"',\r\n"
//	  		+ "		'"+lambdaRequest.getData().get("coleccion").get("idpadrecoleccion")+"',\r\n"
	  		+ "		1,\r\n"
	  		+ "		current_date,\r\n"
	  		+ "		'"+((Map)lambdaRequest.getData().get("coleccion")).get("usucrecoleccion")+"'\r\n"
//	  		+ "		FecModNegF,\r\n"
//	  		+ "		UsumodNegF,\r\n"
	  		+ "	  )";
	  int rows = jdbcTemplate.update(sql);
	  
	  
	  return LambdaResponse.builder().mensaje("OK").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).build();
	}

	@Override
	public LambdaResponse ActualizarColeccion(LambdaRequest lambdaRequest) {
		// TODO Auto-generated method stub
		//actualizamos tabla Negocio
				String sql="update Coleccion set \r\n"
				+ " \r\n"
				+ " DesCol='"+((Map)lambdaRequest.getData().get("coleccion")).get("descripcioncoleccion")+"',\r\n"
//				+ " TipDocNeg='"+lambdaRequest.getData().get("coleccion").get("nombrecoleccion")+"',\r\n"
				+ " NomCol='"+((Map)lambdaRequest.getData().get("coleccion")).get("nombrecoleccion")+"',\r\n"
				+ " FecModCol= current_date,\r\n"
				+ " UsuModCol='"+((Map)lambdaRequest.getData().get("coleccion")).get("usumodcoleccion")+"' \r\n"
				;
				
				if(lambdaRequest.getParametros().get("idcoleccion")!=null) {
					sql=sql+ " where IdCol = "+lambdaRequest.getParametros().get("idcoleccion")+" ";
				 }
				
				System.out.print(sql);
				
				int rows = jdbcTemplate.update(sql);
				
				return LambdaResponse.builder().mensaje("OK").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).build();
	}

	@Override
	public LambdaResponse EliminarColeccion(LambdaRequest lambdaRequest) {
		// TODO Auto-generated method stub
		String sql="update Coleccion set \r\n"
				+ " \r\n"
				+ " EstCcol= 0 \r\n";
		
		if(lambdaRequest.getParametros().get("idcoleccion")!=null) {
			sql=sql+ " where IdCol ="+lambdaRequest.getParametros().get("idcoleccion")+" ";
		 }
				
				System.out.print(sql);
				
				int rows = jdbcTemplate.update(sql);
				
				return LambdaResponse.builder().mensaje("OK").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).build();
	
	} 
	
	
}
