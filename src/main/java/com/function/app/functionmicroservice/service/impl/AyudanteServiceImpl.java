package com.function.app.functionmicroservice.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.function.app.functionmicroservice.common.NetSuiteApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import java.util.concurrent.ExecutionException;
import com.function.app.functionmicroservice.model.ResponseCliente;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;
import com.function.app.functionmicroservice.service.AyudanteService;

@Service
public class AyudanteServiceImpl implements AyudanteService{
	
	@Autowired
	private JdbcTemplate jdbcTemplate; 


	@Override
	public LambdaResponse ObtenerAyudantesXnegocio(LambdaRequest lambdaRequest) {
		// TODO Auto-generated method stub
		Map<String,Object> m=new HashMap<String,Object>();
		   
		 String sql="";
		 
	   sql=" "
			  +"select u.NomUsu as nombres, \r\n" 
			  +"u.ApePatUsu as apellidoPat,\r\n" 
			  +"u.ApeMatUsu as apellidoMat,\r\n"
			  +"u.IdUsu as id,\r\n"
			  +"u.CorUsu as correo,\r\n"
			  +"u.TipDocUsu as tipdoc,\r\n"
			  +"u.NroDocUsu as nrodocumento,\r\n"
			  +"u.TelUsu as telefono,\r\n"
			  +"u.CorUsu as correo,\r\n"
			  +"p.IdPer as idpermido,\r\n"
			  +"p.DesPer as descripcionPer \r\n"
			  +" from Negocio n\r\n"
			  +"inner join Negocio_Usuario_Rol nur \r\n"
			  +"on n.IdNeg = nur.IdNeg_NegUsuRol \r\n"
			  +"inner join Usuario u \r\n"
			  +"on nur.IdUsu_NegUsuRol = u.IdUsu \r\n"
			  +"inner join Rol_Usuario ru \r\n"
			  +"on nur.IdRolUsu_NegUsuRol = ru.IdRolUsu \r\n"
			  +"inner join Rol r \r\n"
			  +"on ru.IdRol_RolUsu = r.IdRol \r\n"
			  +"inner join Rol_Permiso rp \r\n"
			  +"on r.IdRol = rp.IdRol_RolPer \r\n"
			  +"inner join Permiso p \r\n"
			  +"on rp.IdPer_RolPer = p.IdPer \r\n"
			  +"inner join Maestros m \r\n"
			  +"on u.PerUsu = m.CodMaestro \r\n"
			  +"where  u.PerUsu = '2' and m.NombreTabla = 'Tipo Usuario' \r\n"
	  		;
	   
	   if(lambdaRequest.getParametros().get("Idnegocio")!=null) {
			sql=sql+ " and n.IdNeg = "+lambdaRequest.getParametros().get("Idnegocio")+" ";
		 }

	  List<Map<String, Object>> lstayudantes = jdbcTemplate.queryForList(sql);
	  m.put("ayudante", lstayudantes);
	  
	  return LambdaResponse.builder().mensaje("OK").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).data(m).build();
	  
	}

	@Override
	public LambdaResponse ActualizarAyudantes(LambdaRequest lambdaRequest) {
		// TODO Auto-generated method stub
		String sql="update Usuario set \r\n"
				+ " \r\n"
				+ " NomUsu='"+((Map)lambdaRequest.getData().get("ayudante")).get("nombreboayudante")+"',\r\n"
				+ " ApePatUsu='"+((Map)lambdaRequest.getData().get("ayudante")).get("apellidopaternoayudante")+"',\r\n"
				+ " ApeMatUsu='"+((Map)lambdaRequest.getData().get("ayudante")).get("apellidomaternoayudante")+"',\r\n"
				+ " TipDocUsu='"+((Map)lambdaRequest.getData().get("ayudante")).get("tipdocayudante")+"',\r\n"
				+ " NroDocUsu="+((Map)lambdaRequest.getData().get("ayudante")).get("nrodocayudante")+", \r\n"
				+ " CorUsu='"+((Map)lambdaRequest.getData().get("ayudante")).get("correoayudanteo")+"', \r\n"
				+ " PasUsu='"+((Map)lambdaRequest.getData().get("ayudante")).get("pasayudanteo")+"', \r\n"
				+ " TelUsu="+((Map)lambdaRequest.getData().get("ayudante")).get("tele1ayudanteo")+", \r\n"
				+ " Tel2Usu="+((Map)lambdaRequest.getData().get("ayudante")).get("tele2ayudante")+", \r\n"
				+ " FecModUsu=current_date, \r\n"
				+ " UsuModUsu='"+((Map)lambdaRequest.getData().get("ayudante")).get("usumodayudante")+"' \r\n"
				+ "where  IdUsu ="+((Map)lambdaRequest.getData().get("ayudante")).get("idayudante");
				
				int rows = jdbcTemplate.update(sql);
				
				/////////////////////////////////////////////////////////////////////////////////////////////////////
								  
				String CONSUMER_KEY = "a2b2ae2d4d6f962bc67b74dd703e35ccf7bf9c038d8f801cf5508be026c61244";
				String CONSUMER_SECRET = "fa2e9aaafc11dc3a4fd74b36a0a3f77c429dd5129cece2ad70b542761c8ae8a4";
				String TOKEN_ID = "07ade3b219273852ae134fc19e32ff55ee5044264023a9ec5c2cc5fd9cef9ce9";
				String TOKEN_SECRET = "cea51fdd3b316a533f68c8cdb0e7a12355a386c831e230f957fda06d3ac3950f";
				
				//String REST_URL = "https://7460686.suitetalk.api.netsuite.com/services/rest/record/v1/customer";
				String REST_URL = "https://7460686.restlets.api.netsuite.com/app/site/hosting/restlet.nl?script=196&deploy=1";
				String REALM = "7460686";
				
				//////////////////////////////////////////////////////////////////////////////////////////////////////////////
				//id del negocio de netusuite
				String sql3="select n.idContactERPNeg from Negocio n\r\n"
						+ "inner join Negocio_Usuario_Rol nur\r\n"
						+ "on n.IdNeg = nur.IdNeg_NegUsuRol\r\n"
						+ "inner join Usuario u \r\n"
						+ "on nur.IdUsu_NegUsuRol = u.IdUsu\r\n"
						+ "where u.IdUsu = "+((Map)lambdaRequest.getData().get("ayudante")).get("idayudante");
			    
			    int idnegocionetsuite = jdbcTemplate.queryForObject(sql3,int.class);
				
			    //id del usuario del netsuite
			    String sql4=" "
						  +" select idContactERP  as ID\r\n" 
						  +" from Usuario \r\n"
						  +" where IdUsu='"+((Map)lambdaRequest.getData().get("ayudante")).get("idayudante")+"'\r\n"
				  		;
			    
			    int idusuarionetsuite = jdbcTemplate.queryForObject(sql4,int.class);
				
				String POSTBODY="{\r\n"
						+ " \"id\": "+idusuarionetsuite+",\r\n"
						+ " \"action\": \"update\",\r\n"
						+ " \"body\":{ \"firstname\": \""+((Map)lambdaRequest.getData().get("ayudante")).get("nombreboayudante")+"\",\r\n"
				+ " \"lastname\": \""+((Map)lambdaRequest.getData().get("ayudante")).get("apellidopaternoayudante")+" "+((Map)lambdaRequest.getData().get("ayudante")).get("apellidomaternoayudante")+"\",\r\n"            
				+ "\"company\": "+idnegocionetsuite+",\r\n"
				+ " \"job\": \"Comercial\", "
				+ "\"document_type\": \""+((Map)lambdaRequest.getData().get("ayudante")).get("tipdocayudante")+"\",\r\n"
				+ "\"document_number\": \""+((Map)lambdaRequest.getData().get("ayudante")).get("nrodocayudante")+"\",\r\n"
				+ "\"contact_rol\": \"2\", "
				+ "\"phone1\": \""+((Map)lambdaRequest.getData().get("ayudante")).get("tele1ayudanteo")+"\",\r\n"
				+ "\"phone2\": \""+((Map)lambdaRequest.getData().get("ayudante")).get("tele2ayudante")+"\",\r\n"
				+ "\"email\": \""+((Map)lambdaRequest.getData().get("ayudante")).get("correoayudanteo")+"\",\r\n"
				+ "\"password\": \""+((Map)lambdaRequest.getData().get("ayudante")).get("pasayudanteo")+"\",\r\n"
				+ "\"contact_state\": \"1\", "
				+ "\"subsidiary\": \"3\" } }";
				////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				
				final OAuth10aService service = new ServiceBuilder(CONSUMER_KEY).apiSecret(CONSUMER_SECRET)
				.build(NetSuiteApi.instance());
				OAuth1AccessToken accessToken = new OAuth1AccessToken(TOKEN_ID, TOKEN_SECRET);
				
				//  This is POST method call 
				OAuthRequest request = new OAuthRequest(Verb.PUT, REST_URL);
				request.addHeader("Content-Type", "application/json");
				// Without next line, you'll get a "Request media type is not valid." error, even though this is not needed with Postman
				request.addHeader("Accept", "application/json");
				request.addHeader("Content-Language", "en");
				request.setRealm(REALM);
				request.setPayload(POSTBODY);
				
				service.signRequest(accessToken, request);
				
				System.out.println("Sending this request...");
				System.out.println(request.getHeaders());
				System.out.println(request.getCompleteUrl());
				System.out.println(request.getStringPayload());
				
				Response response;
				try {
				response = service.execute(request);
				System.out.println("Got this response...");
				System.out.println(response.getCode() + "\n" + response.getHeaders());
				System.out.println(response.getBody().toString());
				
				ObjectMapper objectMapper = new ObjectMapper();
				ResponseCliente cliente = objectMapper.readValue(response.getBody().toString(), ResponseCliente.class);
				//System.out.println(" cliente : "+cliente.getId());
				
			
				
				} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return LambdaResponse.builder().appCode("3").mensaje("Error en la llamada al servicio netsuite").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).build();
				} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return LambdaResponse.builder().appCode("3").mensaje("Error en la llamada al servicio netsuite").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).build();
				} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return LambdaResponse.builder().appCode("3").mensaje("Error en la llamada al servicio netsuite").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).build();
				}
				
				
				
				
				System.out.print(idnegocionetsuite);
				
				
				return LambdaResponse.builder().mensaje("OK").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).build();
				
	}

	@Override
	public LambdaResponse EliminarAyudantesXnegocio(LambdaRequest lambdaRequest) {
		// TODO Auto-generated method stub
		String sql="update Usuario set \r\n"
				+ " \r\n"
				+ " EstUsu=0 \r\n";
				
				if(lambdaRequest.getParametros().get("idayudante")!=null) {
					sql=sql+ " where  IdUsu = "+lambdaRequest.getParametros().get("idayudante")+" ";
				 }
				
				int rows = jdbcTemplate.update(sql);
				
				/////////////////////////////////////////////////////////////////////////////////////////////////////
								  
				String CONSUMER_KEY = "a2b2ae2d4d6f962bc67b74dd703e35ccf7bf9c038d8f801cf5508be026c61244";
				String CONSUMER_SECRET = "fa2e9aaafc11dc3a4fd74b36a0a3f77c429dd5129cece2ad70b542761c8ae8a4";
				String TOKEN_ID = "07ade3b219273852ae134fc19e32ff55ee5044264023a9ec5c2cc5fd9cef9ce9";
				String TOKEN_SECRET = "cea51fdd3b316a533f68c8cdb0e7a12355a386c831e230f957fda06d3ac3950f";
				
				//String REST_URL = "https://7460686.suitetalk.api.netsuite.com/services/rest/record/v1/customer";
				String REST_URL = "https://7460686.restlets.api.netsuite.com/app/site/hosting/restlet.nl?script=196&deploy=1";
				String REALM = "7460686";
				
				//////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				//id del usuario del netsuite
				String sql4=" "
				+" select idContactERP  as ID\r\n" 
				+" from Usuario \r\n"
				+" where IdUsu="+lambdaRequest.getParametros().get("idayudante")+" ";
				;
				
				int idusuarionetsuite = jdbcTemplate.queryForObject(sql4,int.class);
				
				String POSTBODY="{\r\n"
				+ " \"id\": "+idusuarionetsuite+",\r\n"
				+ " \"action\": \"delete\"\r\n"
				+ "}";
				////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				
				final OAuth10aService service = new ServiceBuilder(CONSUMER_KEY).apiSecret(CONSUMER_SECRET)
				.build(NetSuiteApi.instance());
				OAuth1AccessToken accessToken = new OAuth1AccessToken(TOKEN_ID, TOKEN_SECRET);
				
				//  This is POST method call 
				OAuthRequest request = new OAuthRequest(Verb.PUT, REST_URL);
				request.addHeader("Content-Type", "application/json");
				// Without next line, you'll get a "Request media type is not valid." error, even though this is not needed with Postman
				request.addHeader("Accept", "application/json");
				request.addHeader("Content-Language", "en");
				request.setRealm(REALM);
				request.setPayload(POSTBODY);
				
				service.signRequest(accessToken, request);
				
				System.out.println("Sending this request...");
				System.out.println(request.getHeaders());
				System.out.println(request.getCompleteUrl());
				System.out.println(request.getStringPayload());
				
				Response response;
				try {
				response = service.execute(request);
				System.out.println("Got this response...");
				System.out.println(response.getCode() + "\n" + response.getHeaders());
				System.out.println(response.getBody().toString());
				
				ObjectMapper objectMapper = new ObjectMapper();
				ResponseCliente cliente = objectMapper.readValue(response.getBody().toString(), ResponseCliente.class);
				//System.out.println(" cliente : "+cliente.getId());
				
				
				
				} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return LambdaResponse.builder().appCode("3").mensaje("Error en la llamada al servicio netsuite").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).build();
				} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return LambdaResponse.builder().appCode("3").mensaje("Error en la llamada al servicio netsuite").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).build();
				} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return LambdaResponse.builder().appCode("3").mensaje("Error en la llamada al servicio netsuite").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).build();
				}
				
				
				return LambdaResponse.builder().mensaje("OK").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).build();
				
	}

	@Override
	public LambdaResponse crearAyudante(LambdaRequest lambdaRequest) {
		// TODO Auto-generated method stub
		
		
		Map<String,Object> m=new HashMap<String,Object>();
		
		//id netsuite del negocio
		String sql3=" "
				  +" select idContactERPNeg  as ID\r\n" 
				  +" from Negocio \r\n"
				  +" where IdNeg='"+((Map)lambdaRequest.getData().get("negocio_usuario_rol")).get("IdNeg_NegUsuRolnegusurol")+"'\r\n"
		  		;
	    
	    int idnegocionetsuite = jdbcTemplate.queryForObject(sql3,int.class);
		
		String seq="select nextval('usuario_idusu_seq'::regclass)";
		 
		 Long seqneg = jdbcTemplate.queryForObject(
	                seq,
	                Long.class);
		 //jdbcTemplate.execute(seq);
	//insertamos datos en la tabla Negocio
	  String sql="insert into Usuario\r\n"
	  		+ "	  (\r\n"
	  		+ "		IdUsu,\r\n"
	  		+ "     NomUsu,\r\n"
	  		+ "		TipDocUsu,\r\n"
	  		+ "		NroDocUsu,\r\n"
	  		+ "		TelUsu,\r\n"
	  		+ "		CorUsu,\r\n"
	  		+ "		PasUsu,\r\n"
	  		+ "		FlaTerUsu,\r\n"
	  		+ "		Tel2Usu,\r\n"
	  		+ "		PerUsu,\r\n"
	  		+ "		EstUsu,\r\n"
	  		+ "		FecCreUsu,\r\n"
	  		+ "		UsuCreUsu,\r\n"
	  		+ "		ApePatUsu,\r\n"
	  		+ "		ApeMatUsu,\r\n"
	  		+ "		FlaTraDatUsu,\r\n"
	  		+ "		EstSolUsu,\r\n"
	  		+ "		FecAprRecSol,\r\n"
	  		+ "		UsuAprRecSol,\r\n"
	  		+ "		ObsUsu,\r\n"
	  		+ "		flaAcepComZup,\r\n"
	  		+ "		idContactERP\r\n"
	  		+ "	  )\r\n"
	  		+ "	  values\r\n"
	  		+ "	  (\r\n"
	  		+ "	  	"+seqneg+","
			+ "     '"+((Map)lambdaRequest.getData().get("ayudante")).get("nombreayudante")+"',\r\n"
			+ "		'"+((Map)lambdaRequest.getData().get("ayudante")).get("tipdocayudante")+"',\r\n"
	  		+ "		"+((Map)lambdaRequest.getData().get("ayudante")).get("nrodocayudante")+",\r\n"
	  		+ "		"+((Map)lambdaRequest.getData().get("ayudante")).get("telayudante")+",\r\n"
	  		+ "		'"+((Map)lambdaRequest.getData().get("ayudante")).get("correoayudante")+"',\r\n"
	  		+ "		'"+((Map)lambdaRequest.getData().get("ayudante")).get("pasayudante")+"',\r\n"
	  		+ "		"+((Map)lambdaRequest.getData().get("ayudante")).get("flgterminoayudante")+",\r\n"
	  		+ "		"+((Map)lambdaRequest.getData().get("ayudante")).get("tel2ayudante")+",\r\n"
	  		+ "		'2',\r\n"
	  		+ "		1,\r\n"
	  		+ "		current_date,\r\n"
	  		+ "		'"+((Map)lambdaRequest.getData().get("ayudante")).get("usucreayudante")+"',\r\n"
	  		+ "		'"+((Map)lambdaRequest.getData().get("ayudante")).get("apepaternoayudante")+"',\r\n"
	  		+ "		'"+((Map)lambdaRequest.getData().get("ayudante")).get("apematernoayudante")+"',\r\n"
	  		+ "		"+((Map)lambdaRequest.getData().get("ayudante")).get("flgtradatayudante")+",\r\n"
	  		+ "		"+((Map)lambdaRequest.getData().get("ayudante")).get("estsolayudante")+",\r\n"			
	  		+ "		'"+((Map)lambdaRequest.getData().get("ayudante")).get("FecAprRecSolayudante")+"',\r\n"
			+ "		'"+((Map)lambdaRequest.getData().get("ayudante")).get("UsuAprRecSolayudante")+"',\r\n"
			+ "		'"+((Map)lambdaRequest.getData().get("ayudante")).get("ObsUsuayudante")+"',\r\n"
			+ "		"+((Map)lambdaRequest.getData().get("ayudante")).get("flaAcepComZup")+",\r\n"
			
            + "		"+null+"\r\n"
//	  		+ "		FecModNegF,\r\n"
//	  		+ "		UsumodNegF,\r\n"
	  		+ "	  )";
	  int rows = jdbcTemplate.update(sql);
	  
	  String seq2="select nextval('negocio_usuario_rol_idnegusurol_seq'::regclass)";
		 
		 Long seqneusu = jdbcTemplate.queryForObject(
				 seq2,
	                Long.class);
		 //jdbcTemplate.execute(seq2);
	//insertamos datos en la tabla Negocio
	  String sql2="insert into Negocio_Usuario_Rol \r\n"
	  		+ "	  (\r\n"
	  		+ "		IdNegUsuRol,\r\n"
	  		+ "     IdNeg_NegUsuRol,\r\n"
	  		+ "		IdUsu_NegUsuRol,\r\n"
	  		+ "		IdRolUsu_NegUsuRol,\r\n"
	  		+ "		EstNegUsuRol,\r\n"
	  		+ "		FecCreNegUsuRol,\r\n"
	  		+ "		UsuCreNegUsuRol\r\n"
	  		+ "	  )\r\n"
	  		+ "	  values\r\n"
	  		+ "	  (\r\n"
	  		+ "	  	"+seqneusu+","
			+ "     '"+((Map)lambdaRequest.getData().get("negocio_usuario_rol")).get("IdNeg_NegUsuRolnegusurol")+"',\r\n"
			+ "		"+seqneg+","
	  		+ "		1,\r\n"
	  		+ "		1,\r\n"
	  		+ "		current_date,\r\n"
	  		+ "		'"+((Map)lambdaRequest.getData().get("negocio_usuario_rol")).get("UsuCreNegUsuRolnegusurol")+"'\r\n"
	  		+ "	  )";
	  int rows2 = jdbcTemplate.update(sql2);
	  
	  /////////////////////////////////////////////////////////////////////////////////////////////////////
	  
	  String CONSUMER_KEY = "a2b2ae2d4d6f962bc67b74dd703e35ccf7bf9c038d8f801cf5508be026c61244";
	    String CONSUMER_SECRET = "fa2e9aaafc11dc3a4fd74b36a0a3f77c429dd5129cece2ad70b542761c8ae8a4";
	    String TOKEN_ID = "07ade3b219273852ae134fc19e32ff55ee5044264023a9ec5c2cc5fd9cef9ce9";
	    String TOKEN_SECRET = "cea51fdd3b316a533f68c8cdb0e7a12355a386c831e230f957fda06d3ac3950f";
	    
	    //String REST_URL = "https://7460686.suitetalk.api.netsuite.com/services/rest/record/v1/customer";
	    String REST_URL = "https://7460686.restlets.api.netsuite.com/app/site/hosting/restlet.nl?script=196&deploy=1";
	    String REALM = "7460686";
	   
	    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    
	    String idnetsuite="";

	    String POSTBODY="{ \"firstname\": \""+((Map)lambdaRequest.getData().get("ayudante")).get("nombreayudante")+"\",\r\n"
	    		+ " \"lastname\": \""+((Map)lambdaRequest.getData().get("ayudante")).get("apepaternoayudante")+" "+((Map)lambdaRequest.getData().get("ayudante")).get("apematernoayudante")+"\",\r\n"            
	    		+ "\"company\": \""+idnegocionetsuite+"\",\r\n"
	    		+ " \"job\": \"Comercial\", "
	    		+ "\"document_type\": \""+((Map)lambdaRequest.getData().get("ayudante")).get("tipdocayudante")+"\",\r\n"
	    		+ "\"document_number\": \""+((Map)lambdaRequest.getData().get("ayudante")).get("nrodocayudante")+"\",\r\n"
	    		+ "\"contact_rol\": \"2\", "
	    		+ "\"phone1\": \""+((Map)lambdaRequest.getData().get("ayudante")).get("telayudante")+"\",\r\n"
	    		+ "\"phone2\": \""+((Map)lambdaRequest.getData().get("ayudante")).get("tel2ayudante")+"\",\r\n"
	    		+ "\"email\": \""+((Map)lambdaRequest.getData().get("ayudante")).get("correoayudante")+"\",\r\n"
	    		+ "\"password\": \""+((Map)lambdaRequest.getData().get("ayudante")).get("pasayudante")+"\",\r\n"
	    		+ "\"contact_state\": \"1\", "
	    		+ "\"subsidiary\": \"3\" }";
	    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    
	    
	    final OAuth10aService service = new ServiceBuilder(CONSUMER_KEY).apiSecret(CONSUMER_SECRET)
              .build(NetSuiteApi.instance());
      OAuth1AccessToken accessToken = new OAuth1AccessToken(TOKEN_ID, TOKEN_SECRET);
      
      //  This is POST method call 
    OAuthRequest request = new OAuthRequest(Verb.POST, REST_URL);
    request.addHeader("Content-Type", "application/json");
    // Without next line, you'll get a "Request media type is not valid." error, even though this is not needed with Postman
    request.addHeader("Accept", "application/json");
    request.addHeader("Content-Language", "en");
    request.setRealm(REALM);
    request.setPayload(POSTBODY);
      
      service.signRequest(accessToken, request);
      
      System.out.println("Sending this request...");
      System.out.println(request.getHeaders());
      System.out.println(request.getCompleteUrl());
       System.out.println(request.getStringPayload());
              
      Response response;
		try {
			response = service.execute(request);
			  System.out.println("Got this response...");
		        System.out.println(response.getCode() + "\n" + response.getHeaders());
		        System.out.println(response.getBody().toString());
		        
		        ObjectMapper objectMapper = new ObjectMapper();
		        ResponseCliente cliente = objectMapper.readValue(response.getBody().toString(), ResponseCliente.class);
		        System.out.println(" cliente : "+cliente.getId());
		        
		        //////////////////////////////////////////////////////////////////////////////////////////////
		        //obtenemos el id ayudante
		        //se ingresa el id del contac de netsuite
		        String sqlUdp="update Usuario set \r\n"
						+ " \r\n"
						+ " idContactERP='"+cliente.getId()+"' \r\n"
						+ "where  IdUsu ="+seqneg+" ";
						
						int rowsUdp = jdbcTemplate.update(sqlUdp);
		        
		        idnetsuite= response.getBody().toString().substring(response.getBody().toString().indexOf(":")+1);
		        idnetsuite=idnetsuite.replace("}", "");
		        
		        if(idnetsuite.equals("")) {
		        	return LambdaResponse.builder().appCode("2").mensaje("Servicio netsuite no genero cliente").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).data(m).build();
		        }
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return LambdaResponse.builder().appCode("3").mensaje("Error en la llamada al servicio netsuite").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).data(m).build();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return LambdaResponse.builder().appCode("3").mensaje("Error en la llamada al servicio netsuite").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).data(m).build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return LambdaResponse.builder().appCode("3").mensaje("Error en la llamada al servicio netsuite").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).data(m).build();
		}
	    
		
 
	  
		System.out.print(idnegocionetsuite);
	  
	  return LambdaResponse.builder().mensaje("OK").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).build();
	
	}

}
