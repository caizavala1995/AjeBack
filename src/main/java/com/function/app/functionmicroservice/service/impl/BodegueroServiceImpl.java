package com.function.app.functionmicroservice.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;
import com.function.app.functionmicroservice.common.NetSuiteApi;
import com.function.app.functionmicroservice.model.ResponseCliente;
import com.function.app.functionmicroservice.service.BodegueroService;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;

@Service
public class BodegueroServiceImpl implements BodegueroService{
	
	@Autowired
	private JdbcTemplate jdbcTemplate; 

	@Override
	public LambdaResponse ObtenerBodeguero(LambdaRequest lambdaRequest) {
		// TODO Auto-generated method stub
		Map<String,Object> m=new HashMap<String,Object>();
		   
		 String sql="";
		 
	   sql=" "
			  +"select IdUsu as id, \r\n" 
			  +"NomUsu as nombres,\r\n" 
			  +"ApePatUsu as paterno,\r\n"
			  +"ApeMatUsu as materno,\r\n"
			  +"TipDocUsu as tipoDoc,\r\n"
			  +"NroDocUsu as nroDoc,\r\n"
			  +"CorUsu as correo1,\r\n"
			  +"TelUsu as tel1,\r\n"
			  +"Tel2Usu as tel2 \r\n"
			  +" from Usuario \r\n"
			  +"where EstUsu=1 \r\n"
	  		;
	   
	   if(lambdaRequest.getParametros().get("IdBodeguero")!=null) {
			sql=sql+ " and IdUsu ="+lambdaRequest.getParametros().get("IdBodeguero")+" ";
		 }

	  List<Map<String, Object>> lstBodeguero = jdbcTemplate.queryForList(sql);
	  m.put("bodeguero", lstBodeguero);
	  
	  return LambdaResponse.builder().mensaje("OK").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).data(m).build();
	  
	}

	@Override
	public LambdaResponse CrearBodeguero(LambdaRequest lambdaRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LambdaResponse ActualizarrBodeguero(LambdaRequest lambdaRequest) {
		// TODO Auto-generated method stub
		String sql="update Usuario set \r\n"
				+ " \r\n"
				+ " NomUsu='"+((Map)lambdaRequest.getData().get("bodeguero")).get("nombrebodeguero")+"',\r\n"
				+ " ApePatUsu='"+((Map)lambdaRequest.getData().get("bodeguero")).get("apellidopaternobodeguero")+"',\r\n"
				+ " ApeMatUsu='"+((Map)lambdaRequest.getData().get("bodeguero")).get("apellidomaternobodeguero")+"',\r\n"
				+ " TipDocUsu='"+((Map)lambdaRequest.getData().get("bodeguero")).get("tipdocbodeguero")+"',\r\n"
				+ " NroDocUsu="+((Map)lambdaRequest.getData().get("bodeguero")).get("nrodocbodeguero")+", \r\n"
				+ " CorUsu='"+((Map)lambdaRequest.getData().get("bodeguero")).get("correobodeguero")+"', \r\n"
				+ " PasUsu='"+((Map)lambdaRequest.getData().get("bodeguero")).get("pasbodeguero")+"', \r\n"
				+ " TelUsu='"+((Map)lambdaRequest.getData().get("bodeguero")).get("tele1bodeguero")+"', \r\n"
				+ " Tel2Usu='"+((Map)lambdaRequest.getData().get("bodeguero")).get("tele2bodeguero")+"', \r\n"
				+ " FecModUsu=current_date, \r\n"
				+ " UsuModUsu='"+((Map)lambdaRequest.getData().get("bodeguero")).get("usumodbodeguero")+ "' \r\n"
				+ " where  IdUsu ="+((Map)lambdaRequest.getData().get("bodeguero")).get("idbodeguero");
				
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
				+ "where u.IdUsu = "+((Map)lambdaRequest.getData().get("bodeguero")).get("idbodeguero");
				
				int idnegocionetsuite = jdbcTemplate.queryForObject(sql3,int.class);
				
				//id del usuario del netsuite
				String sql4=" "
				+" select idContactERP  as ID\r\n" 
				+" from Usuario \r\n"
				+" where IdUsu='"+((Map)lambdaRequest.getData().get("bodeguero")).get("idbodeguero")+"'\r\n"
				;
				
				int idusuarionetsuite = jdbcTemplate.queryForObject(sql4,int.class);
				
				String POSTBODY="{\r\n"
				+ " \"id\": "+idusuarionetsuite+",\r\n"
				+ " \"action\": \"update\",\r\n"
				+ " \"body\":{ \"firstname\": \""+((Map)lambdaRequest.getData().get("bodeguero")).get("nombrebodeguero")+"\",\r\n"
				+ " \"lastname\": \""+((Map)lambdaRequest.getData().get("bodeguero")).get("apellidopaternobodeguero")+" "+((Map)lambdaRequest.getData().get("bodeguero")).get("apellidomaternobodeguero")+"\",\r\n"            
				+ "\"company\": "+idnegocionetsuite+",\r\n"
				+ " \"job\": \"Comercial\", "
				+ "\"document_type\": \""+((Map)lambdaRequest.getData().get("bodeguero")).get("tipdocbodeguero")+"\",\r\n"
				+ "\"document_number\": \""+((Map)lambdaRequest.getData().get("bodeguero")).get("nrodocbodeguero")+"\",\r\n"
				+ "\"contact_rol\": \"1\", "
				+ "\"phone1\": \""+((Map)lambdaRequest.getData().get("bodeguero")).get("tele1bodeguero")+"\",\r\n"
				+ "\"phone2\": \""+((Map)lambdaRequest.getData().get("bodeguero")).get("tele2bodeguero")+"\",\r\n"
				+ "\"email\": \""+((Map)lambdaRequest.getData().get("bodeguero")).get("correobodeguero")+"\",\r\n"
				+ "\"password\": \""+((Map)lambdaRequest.getData().get("bodeguero")).get("pasbodeguero")+"\",\r\n"
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
	public LambdaResponse EliminarBodeguero(LambdaRequest lambdaRequest) {
		// TODO Auto-generated method stub
		String sql="update Usuario set \r\n"
				+ " \r\n"
				+ " EstUsu=0 \r\n";
				
				if(lambdaRequest.getParametros().get("idbodeguero")!=null) {
					sql=sql+ " where  IdUsu = "+lambdaRequest.getParametros().get("idbodeguero")+" ";
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
				+" where IdUsu="+lambdaRequest.getParametros().get("idbodeguero")+" ";
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

}
