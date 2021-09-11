package com.function.app.functionmicroservice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;
import com.function.app.functionmicroservice.service.ProdColeccService;

@Service
public class ProdColeccServiceImpl implements ProdColeccService{
	
	@Autowired
	private JdbcTemplate jdbcTemplate; 

	@Override
	public LambdaResponse ObtenerProdColeccion(LambdaRequest lambdaRequest) {
		// TODO Auto-generated method stub
		Map<String,Object> m=new HashMap<String,Object>();
		   
		 String sql="";
		 
		 sql=" "
				  +"select * from Coleccion_Productos \r\n" 
		  		;
		   
	  List<Map<String, Object>> lstProdColecc = jdbcTemplate.queryForList(sql);
	  m.put("prodColec", lstProdColecc);

	  return LambdaResponse.builder().mensaje("OK").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).data(m).build();
	 
	}

	@Override
	public LambdaResponse crearProdColeccion(LambdaRequest lambdaRequest) {
		// TODO Auto-generated method stub
		
		String seq="select nextval('coleccion_producto_idcolpro_seq'::regclass)";
		 
		 Long seqcolpro = jdbcTemplate.queryForObject(
	                seq,
	                Long.class);
		 jdbcTemplate.execute(seq);
		 
		 for (int i = 0; i < ((List)lambdaRequest.getData().get("coleccionproducto")).size(); i++) {
			 
			 Map maplistaColPro=(Map)((List)lambdaRequest.getData().get("coleccionproducto")).get(i);
			 
			 String sql="insert into Coleccion_Productos \r\n"
				  		+ "	  (\r\n"
				  		+ "		IdColPro,"
				  		+ "     IdCol_ColPro,\r\n"
				  		+ "		IdPro_ColPro,\r\n"
				  		+ "		OrdColPro,\r\n"
				  		+ "		EstColPro,\r\n"
				  		+ "		FecCreColPro,\r\n"
				  		+ "		UsuCreColPro,\r\n"
				  		+ "		FecModColPro,\r\n"
				  		+ "		UsuModColPro,\r\n"
				  		+ "	  )\r\n"
				  		+ "	  values\r\n"
				  		+ "	  (\r\n"
				  		+ "	  	"+seqcolpro+","
						+ "     '"+maplistaColPro.get("idcoleccion")+"',\r\n"
						+ "		'"+maplistaColPro.get("idproducto")+"',\r\n"
				  		+ "		'"+maplistaColPro.get("ordenproducto")+"',\r\n"
				  		+ "		1,\r\n"
				  		+ "		current_date,\r\n"
				  		+ "		UsuCreNegF,\r\n"
				  		+ "		FecModNegF,\r\n"
				  		+ "		UsumodNegF,\r\n"
				  		+ "	  )";
				  int rows = jdbcTemplate.update(sql);
		 }

	  Map<String,Object> m=new HashMap<String,Object>();

	  m.put("idcoleccionproducto", seqcolpro);
	  
	  return LambdaResponse.builder().mensaje("OK").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).data(m).build();

	}

	@Override
	public LambdaResponse actualizarProdColeccion(LambdaRequest lambdaRequest) {
		// TODO Auto-generated method stub
		 //insert colecccion - producto
		String seq="select nextval('coleccion_producto_idcolpro_seq'::regclass)";
		 
		 Long seqcolpro = jdbcTemplate.queryForObject(
	                seq,
	                Long.class);
		 jdbcTemplate.execute(seq);
		 
		 for (int i = 0; i < ((List)lambdaRequest.getData().get("coleccionproducto")).size(); i++) {
			 
			 Map maplistaColPro=(Map)((List)lambdaRequest.getData().get("coleccionproducto")).get(i);
			 
			 //actualiza la coleccion
			 if(seqcolpro!=null) {
				 String sql="update Coleccion_Productos set \r\n"
					  		+ "	  \r\n"

					  		+ "		OrdColPro= "+maplistaColPro.get("ordencoleccionproducto")+"',\r\n"
					  		+ "		EstColPro=1,\r\n"

					  		+ "		FecModColPro = current_date,\r\n"
					  		+ "		UsuModColPro = "+maplistaColPro.get("usumodcoleccionproducto")+"'\r\n"
					  		+ "	  where IdColPro ="+maplistaColPro.get("idcoleccionproducto")+"'\r\n"
	;
					  int rows = jdbcTemplate.update(sql);
			 }else {
				 //inserta nuevo producto a la coleccion
				 String sql="insert into Coleccion_Productos \r\n"
					  		+ "	  (\r\n"
					  		+ "		IdColPro,"
					  		+ "     IdCol_ColPro,\r\n"
					  		+ "		IdPro_ColPro,\r\n"
					  		+ "		OrdColPro,\r\n"
					  		+ "		EstColPro,\r\n"
					  		+ "		FecCreColPro,\r\n"
					  		+ "		UsuCreColPro,\r\n"
					  		+ "		FecModColPro,\r\n"
					  		+ "		UsuModColPro,\r\n"
					  		+ "	  )\r\n"
					  		+ "	  values\r\n"
					  		+ "	  (\r\n"
					  		+ "	  	"+seqcolpro+","
							+ "     '"+maplistaColPro.get("idcoleccion")+"',\r\n"
							+ "		'"+maplistaColPro.get("idproducto")+"',\r\n"
					  		+ "		'"+maplistaColPro.get("ordenproducto")+"',\r\n"
					  		+ "		1,\r\n"
					  		+ "		current_date,\r\n"
					  		+ "		UsuCreNegF,\r\n"
					  		+ "		FecModNegF,\r\n"
					  		+ "		UsumodNegF,\r\n"
					  		+ "	  )";
					  int rows = jdbcTemplate.update(sql);
			 }
			
		 }

	  Map<String,Object> m=new HashMap<String,Object>();

	  m.put("idcoleccionproducto", seqcolpro);
	  
	  return LambdaResponse.builder().mensaje("OK").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).data(m).build();

	}

	@Override
	public LambdaResponse eliminarProdColeccion(LambdaRequest lambdaRequest) {
		// TODO Auto-generated method stub
		String seq="select nextval('coleccion_producto_idcolpro_seq'::regclass)";
		 
		 Long seqcolpro = jdbcTemplate.queryForObject(
	                seq,
	                Long.class);
		 jdbcTemplate.execute(seq);
		 
		 for (int i = 0; i < ((List)lambdaRequest.getData().get("coleccionproducto")).size(); i++) {
			 
			 Map maplistaColPro=(Map)((List)lambdaRequest.getData().get("coleccionproducto")).get(i);
			 
			 
			 String sql="update Coleccion_Productos set \r\n"
				  		+ "	  \r\n"

				  		+ "		OrdColPro= "+maplistaColPro.get("ordenproducto")+"',\r\n"
				  		+ "		EstColPro=1,\r\n"

				  		+ "		FecModColPro=current_date,\r\n"
				  		+ "		UsuModColPro= "+maplistaColPro.get("ordenproducto")+"'\r\n"
				  		+ "	  )\r\n"
;
				  int rows = jdbcTemplate.update(sql);
		 }

	  Map<String,Object> m=new HashMap<String,Object>();

	  m.put("idcoleccionproducto", seqcolpro);
	  
	  return LambdaResponse.builder().mensaje("OK").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).data(m).build();

	}

}
