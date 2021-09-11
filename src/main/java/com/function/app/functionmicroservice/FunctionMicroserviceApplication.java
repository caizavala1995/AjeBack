package com.function.app.functionmicroservice;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

@SpringBootApplication
public class FunctionMicroserviceApplication {//implements CommandLineRunner{

	//@Autowired
	//private JdbcTemplate jdbcTemplate; 
	
	public static void main(String[] args) {
		SpringApplication.run(FunctionMicroserviceApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		// TODO Auto-generated method stub
//		
//		SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withFunctionName("validarusuario2");
//		HashMap<String, Object> params = new HashMap<String, Object>();
//		params.put("pv_usuario", "edwin");
//		//params = new HashMap<String, Object>();
//		params.put("pv_password", "edwin");
//		Map<String, Object> result = call.execute(params);
//		for (String s : result.keySet()) {
//		     System.out.println("6.0  " + result.get(s));
//		}
//		
	  /*SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
              .withProcedureName("validarusuario")
              .declareParameters(
                  new SqlParameter("pv_usuario", Types.VARCHAR),
                  new SqlParameter("pv_password", Types.VARCHAR),
                  new SqlOutParameter("pv_codigoerror", Types.VARCHAR),
                  new SqlOutParameter("pv_mensajeerror", Types.VARCHAR));
          
          
           simpleJdbcCall.execute(
              new MapSqlParameterSource("pv_usuario", "edwin"),
              new MapSqlParameterSource("pv_password", "edwin"));
      */
    
//		
	//}
	
	

}
