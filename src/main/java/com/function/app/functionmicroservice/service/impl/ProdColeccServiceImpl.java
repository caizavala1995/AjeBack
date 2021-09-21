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
				  +"select cd.IdColPro as idcoleccionProd, \r\n"
				  +"c.IdCol as idcoleccion, \r\n" 
				  +"c.DesCol as descColeccion, \r\n" 
				  +"c.NomCol as nombreCol, \r\n" 
				  +"p.IdPro as idproducto, \r\n" 
				  +"p.DesPro as descripcionPro, \r\n" 
				  +"p.MarPro as marca, \r\n"
				  +"p.ForPro as formato ,\r\n"
				  +"p.SKUPro as sku \r\n"
				  +" from Coleccion_Productos cd \r\n"
				  +"inner join Coleccion c \r\n"
				  +"on cd.IdCol_ColPro = c.IdCol \r\n"
				  +"inner join Productos p \r\n"
				  +"on cd.IdPro_ColPro = p.IdPro \r\n"
				  +"where cd.EstColPro = 1 \r\n"
		  		;
		 
		 //c.IdCol = 2 and 
		 if(lambdaRequest.getParametros().get("Idcoleccion")!=null) {
				sql=sql+ " and  c.IdCol ="+lambdaRequest.getParametros().get("Idcoleccion")+" ";
			 }
		   
		   
	  List<Map<String, Object>> lstProdColecc = jdbcTemplate.queryForList(sql);
	  m.put("prodColec", lstProdColecc);

	  return LambdaResponse.builder().mensaje("OK").appCode("0").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).data(m).build();
	 
	}

	@Override
	public LambdaResponse crearProdColeccion(LambdaRequest lambdaRequest) {
		// TODO Auto-generated method stub
		
		//ingresar todo dentro del for
//		String seq="select nextval('coleccion_productos_idcolpro_seq'::regclass)";
//		 
//		 Long seqcolpro = jdbcTemplate.queryForObject(
//	                seq,
//	                Long.class);
//		 jdbcTemplate.execute(seq);
		 
		 for (int i = 0; i < ((List)lambdaRequest.getData().get("coleccionproducto")).size(); i++) {
			 
			 Map maplistaColPro=(Map)((List)lambdaRequest.getData().get("coleccionproducto")).get(i);
			 
			 String sql="insert into Coleccion_Productos\r\n"
				  		+ "	  (\r\n"
//				  		+ "		IdColPro,"
				  		+ "     IdCol_ColPro,\r\n"
				  		+ "		IdPro_ColPro,\r\n"
				  		+ "		OrdColPro,\r\n"
				  		+ "		EstColPro,\r\n"
				  		+ "		FecCreColPro,\r\n"
				  		+ "		UsuCreColPro\r\n"
//				  		+ "		FecModColPro,\r\n"
//				  		+ "		UsuModColPro,\r\n"
				  		+ "	  )\r\n"
				  		+ "	  values\r\n"
				  		+ "	  (\r\n"
//				  		+ "	  	"+seqcolpro+","
						+ "     "+maplistaColPro.get("idcoleccion")+",\r\n"
						+ "		"+maplistaColPro.get("idproducto")+",\r\n"
				  		+ "		"+maplistaColPro.get("ordenproducto")+",\r\n"
				  		+ "		1,\r\n"
				  		+ "		current_date,\r\n"
				  		+ "		'"+maplistaColPro.get("usucreproducto")+"'\r\n"
//				  		+ "		UsuCreNegF,\r\n"
//				  		+ "		FecModNegF,\r\n"
//				  		+ "		UsumodNegF,\r\n"
				  		+ "	  )";
				  int rows = jdbcTemplate.update(sql);
		 }

	  Map<String,Object> m=new HashMap<String,Object>();

//	  m.put("idcoleccionproducto", seqcolpro);
	  
	  return LambdaResponse.builder().mensaje("OK").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).data(m).build();

	}

	@Override
	public LambdaResponse actualizarProdColeccion(LambdaRequest lambdaRequest) {
		// TODO Auto-generated method stub
		 //insert colecccion - producto
		
		Long seqcolpro = 0L;
		int idtab = 0;
				 
		 for (int i = 0; i < ((List)lambdaRequest.getData().get("coleccionproducto")).size(); i++) {
			 
			 Map maplistaColPro=(Map)((List)lambdaRequest.getData().get("coleccionproducto")).get(i);
			 
			 String seq="select nextval('coleccion_productos_idcolpro_seq'::regclass)";
			 
			  seqcolpro = jdbcTemplate.queryForObject(
		                seq,
		                Long.class);
			 jdbcTemplate.execute(seq);

			 idtab = (int) maplistaColPro.get("idcoleccionproducto");
			 
			 //actualiza la coleccion
			 if(idtab!= 0 ) {
				 String sql="update Coleccion_Productos set \r\n"
					  		+ "	  \r\n"
					  		+ "     IdCol_ColPro="+maplistaColPro.get("idcoleccion")+",\r\n"
							+ "		IdPro_ColPro="+maplistaColPro.get("idproducto")+",\r\n"
					  		+ "		OrdColPro= "+maplistaColPro.get("ordencoleccionproducto")+",\r\n"
					  		+ "		EstColPro="+maplistaColPro.get("estadocoleccionproducto")+",\r\n"
					  		+ "		FecModColPro = current_date,\r\n"
					  		+ "		UsuModColPro = '"+maplistaColPro.get("usumodcoleccionproducto")+"'\r\n"
					  		+ "	  where IdColPro ="+maplistaColPro.get("idcoleccionproducto")+"\r\n"
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
					  		+ "		UsuCreColPro\r\n"
					  		+ "	  )\r\n"
					  		+ "	  values\r\n"
					  		+ "	  (\r\n"
					  		+ "	  	"+seqcolpro+","
							+ "     "+maplistaColPro.get("idcoleccion")+",\r\n"
							+ "		"+maplistaColPro.get("idproducto")+",\r\n"
					  		+ "		"+maplistaColPro.get("ordencoleccionproducto")+",\r\n"
					  		+ "		1,\r\n"
					  		+ "		current_date,\r\n"
					  		+ "		'"+maplistaColPro.get("usucrecoleccionproducto")+"'\r\n"
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
