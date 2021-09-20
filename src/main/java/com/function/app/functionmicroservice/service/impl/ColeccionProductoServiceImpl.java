package com.function.app.functionmicroservice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;
import com.function.app.functionmicroservice.service.ColeccionProdService;

@Service
public class ColeccionProductoServiceImpl implements ColeccionProdService{
	
	@Autowired
	private JdbcTemplate jdbcTemplate; 

	@Override
	public LambdaResponse ObtenerColeccionProdid(LambdaRequest lambdaRequest) {
		// TODO Auto-generated method stub
		Map<String,Object> m=new HashMap<String,Object>();
		   
		 String sql="";
		 
		 String idcol = lambdaRequest.getParametros().get("IdColeccion");
		 //idtab = (int) maplistaColPro.get("idcoleccionproducto");
		 
	   sql=" "
			  +"select p.* from productos p \r\n" 
			  +"inner join coleccion_productos cp on p.idpro=cp.idpro_colpro \r\n" 
			  +" where cp.estcolpro=0 \r\n"
			  
			  +"and cp.idcol_colpro="+idcol+" \r\n "
			  
			  +" union all \r\n"
			  +" select p.* from productos p \r\n"
			  +" where p.estpro=1 \r\n"
			  +" and not exists (select 1 from coleccion_productos cp \r\n"
			  +" where p.idpro=cp.idpro_colpro \r\n"
			  
			  +" and cp.idcol_colpro="+idcol+") \r\n ";
	  		;


	  		List<Map<String, Object>> lstProdCol  = jdbcTemplate.queryForList(sql);
	  	     m.put("coleccionproductoId", lstProdCol );

	  return LambdaResponse.builder().mensaje("OK").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).data(m).build();
	}

}
