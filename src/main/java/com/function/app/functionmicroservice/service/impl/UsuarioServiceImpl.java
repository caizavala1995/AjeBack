package com.function.app.functionmicroservice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;
import com.function.app.functionmicroservice.model.Negocio;
import com.function.app.functionmicroservice.model.UsuarioRq;
import com.function.app.functionmicroservice.model.UsuarioRs;
import com.function.app.functionmicroservice.service.UsuarioService;


@Service
public class UsuarioServiceImpl implements UsuarioService {	

	
	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
	
	 @Override
	    public UsuarioRs validarUsuario(UsuarioRq usuarioRq) {
	        String corusu = usuarioRq.getCorusu();
	        String pasusu = usuarioRq.getPasusu();
	        String codigoRespuesta="0";
	        String mensajeRespuesta="Usuario Validado";

	        
        
			SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withFunctionName("validarusuario2");
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("pv_usuario", corusu);
			params.put("pv_password", pasusu);
			Map<String, Object> result = call.execute(params);
			//for (String s : result.keySet()) {
			  //   System.out.println("6.0  " + result.get(s));
			//}
			
			codigoRespuesta=result.get("pv_codigoerror").toString();
	        mensajeRespuesta=result.get("pv_mensajeerror").toString();
			
			
	        return UsuarioRs.builder().codigoRespuesta(codigoRespuesta).mensajeRespuesta(mensajeRespuesta).build();
	    }
	 
	 @Override
	    public LambdaResponse registrarNegocio(LambdaRequest lambdaRequest) {
		 
		 String seq="select nextval('negocio_idneg_seq'::regclass)";
		 
		 Long seqneg = jdbcTemplate.queryForObject(
	                seq,
	                Long.class);
		 jdbcTemplate.execute(seq);
	//insertamos datos en la tabla Negocio
	  String sql="insert into Negocio\r\n"
	  		+ "	  (\r\n"
	  		+ "		idneg,"
	  		+ "     NomComNeg,\r\n"
	  		+ "		TipDocNeg,\r\n"
	  		+ "		NroDocNeg,\r\n"
	  		+ "		CorEleNeg,\r\n"
	  		+ "		RazSocNeg,\r\n"
	  		+ "		EstNeg,\r\n"
	  		+ "		FecCreNeg,\r\n"
//	  		+ "		UsuCreNeg,\r\n"
//	  		+ "		FecModNeg,\r\n"
//	  		+ "		UsumodNeg,\r\n"
	  		+ "		TipNeg\r\n"
	  		+ "	  )\r\n"
	  		+ "	  values\r\n"
	  		+ "	  (\r\n"
	  		+ "	  	"+seqneg+","
			+ "     '"+lambdaRequest.getData().get("negocio").get("nombrecomercial")+"',\r\n"
			+ "		'"+lambdaRequest.getData().get("negocio").get("tipodocumento")+"',\r\n"
	  		+ "		'"+lambdaRequest.getData().get("negocio").get("nrodocumento")+"',\r\n"
	  		+ "		'"+lambdaRequest.getData().get("negocio").get("correo")+"',\r\n"
	  		+ "		'"+lambdaRequest.getData().get("negocio").get("razonsocial")+"',\r\n"
//	  		+ "		'"+lambdaRequest.getData2().getTipodocumento()+"',\r\n"
//	  		+ "		'"+lambdaRequest.getData2().getNrodocumento()+"',\r\n"
//	  		+ "		'"+lambdaRequest.getData2().getCorreo()+"',\r\n"
//	  		+ "		'"+lambdaRequest.getData2().getRazonsocial()+"',\r\n"
	  		+ "		1,\r\n"
	  		+ "		current_date,\r\n"
//	  		+ "		UsuCreNegF,\r\n"
//	  		+ "		FecModNegF,\r\n"
//	  		+ "		UsumodNegF,\r\n"
	  		+ "		'1'\r\n"
	  		+ "	  )";
	  int rows = jdbcTemplate.update(sql);
	  
	   seq="select nextval('usuario_idusu_seq'::regclass)";
		 
		 Long sequsu = jdbcTemplate.queryForObject(
	                seq,
	                Long.class);
		 jdbcTemplate.execute(seq);
	  
	  //insertamos datos a la tabla Usuario
      sql="insert into Usuario\r\n"
      		+ "	  (	\r\n"
      		+ "		idusu,"
      		+ "     NomUsu,       \r\n"
      		+ "		TipDocUsu,  \r\n"
      		+ "		NroDocUsu,    \r\n"
      		+ "		TelUsu,       \r\n"
      		+ "		CorUsu,      \r\n"
      		+ "		PasUsu,     \r\n"
      		+ "		FlaTerUsu,    \r\n"
      		+ "		Tel2Usu,  \r\n"
      		+ "		PerUsu,      \r\n"
      		+ "		EstUsu,     \r\n"
      		+ "		FecCreUsu    \r\n"
//      		+ "		UsuCreUsu,   \r\n"
//      		+ "		FecModUsu,    \r\n"
//      		+ "		UsuModUsu,    \r\n"
//      		+ "		ApePatUsu,   \r\n"
//      		+ "		ApeMatUsu,   \r\n"
//      		+ "		FlaTraDatUsu, \r\n"
//      		+ "		EstSolUsu,    \r\n"
//      		+ "		FecAprRecSol, \r\n"
//      		+ "		UsuAprRecSol, \r\n"
//      		+ "		ObsUsu       \r\n"
      		+ "	  )\r\n"
      		+ "	  values\r\n"
      		+ "	  (\r\n"
      		+ "		"+sequsu+","
      		+ "     '"+lambdaRequest.getData().get("usuario").get("nombre")+"',       \r\n"
      		+ "		'"+lambdaRequest.getData().get("usuario").get("tipodocumento")+"',    \r\n"
      		+ "		'"+lambdaRequest.getData().get("usuario").get("nrodocumento")+"',   \r\n"
      		+ "		'"+lambdaRequest.getData().get("usuario").get("telefono")+"',      \r\n"
      		+ "		'"+lambdaRequest.getData().get("usuario").get("correo")+"',       \r\n"
      		+ "		'"+lambdaRequest.getData().get("usuario").get("password")+"',       \r\n"
      		+ "		'"+lambdaRequest.getData().get("usuario").get("flgterminoscondiciones")+"',    \r\n"
      		+ "		'"+lambdaRequest.getData().get("usuario").get("telefono2")+"',    \r\n"
      		+ "		'1',     \r\n"
      		+ "		1,--estado inicial       \r\n"
      		+ "		current_date --fecha creacion    \r\n"
//      		+ "		UsuCreUsuF,    \r\n"
//      		+ "		FecModUsuF,    \r\n"
//      		+ "		UsuModUsuF,  \r\n"
//      		+ "		ApePatUsuF,    \r\n"
//      		+ "		ApeMatUsuF,    \r\n"
//      		+ "		FlaTraDatUsuF, \r\n"
//      		+ "		EstSolUsuF,    \r\n"
//      		+ "		FecAprRecSolF, \r\n"
//      		+ "		UsuAprRecSolF, \r\n"
//      		+ "		ObsUsuF      \r\n"
      		+ "	  )";

	  
      rows = jdbcTemplate.update(sql);
      seq="select nextval('direccion_negocio_iddirneg_seq'::regclass)";
		 
		 Long seqdir = jdbcTemplate.queryForObject(
	                seq,
	                Long.class);
		 jdbcTemplate.execute(seq);
      sql="INSERT INTO direccion_negocio(\r\n"
      		+ "    			iddirneg, \r\n"
      		+ "    			idneg_dirneg, \r\n"
      		+ "    			teldirneg, \r\n"
      		+ "    			refdirneg, \r\n"
      		+ "    			tipdirdirneg, \r\n"
      		+ "    			x_dirneg, \r\n"
      		+ "    			y_dirneg, \r\n"
      		//+ "    			iddis_dirneg, \r\n"
      		//+ "    			kmdirneg, \r\n"
      		//+ "    			mzdirneg, \r\n"
      		//+ "    			lotdirneg, \r\n"
      		//+ "    			tiphudirneg, \r\n"
      		//+ "    			haburbdirneg, \r\n"
      		//+ "    			tipcondirneg, \r\n"
      		//+ "    			condirneg, \r\n"
      		+ "    			estdirneg, \r\n"
      		+ "    			feccredirneg, \r\n"
//      		+ "    			usucredirneg, \r\n"
//      		+ "    			fecmoddirneg, \r\n"
//      		+ "    			usumoddirneg, \r\n"
      		+ "    			direntdirneg \r\n"
//      		+ "    			dirrefdirneg"
      		+ " )\r\n"
      		+ "    			VALUES (\r\n"
      		+ "    					"+seqdir+", \r\n"
      		+ "    					"+seqneg+", \r\n"
      		+ "    					'"+lambdaRequest.getData().get("direccion").get("telefono")+"', \r\n"
      		+ "    					'"+lambdaRequest.getData().get("direccion").get("referencia")+"', \r\n"
      		+ "    					'"+lambdaRequest.getData().get("direccion").get("tipodireccion")+"', \r\n"
      		+ "    					"+lambdaRequest.getData().get("direccion").get("x")+", \r\n"
      		+ "    					"+lambdaRequest.getData().get("direccion").get("y")+", \r\n"
//      		+ "    					?, \r\n"
//      		+ "    					?, \r\n"
//      		+ "    					?, \r\n"
//      		+ "    					?, \r\n"
//      		+ "    					?, \r\n"
//      		+ "    					?, \r\n"
//      		+ "    					?,\r\n"
//      		+ "    					?, \r\n"
      		+ "    					1, \r\n"
      		+ "    					current_date, \r\n"
//      		+ "    					?, \r\n"
//      		+ "    					?, \r\n"
//      		+ "    					?, \r\n"
      		+ "    					'"+lambdaRequest.getData().get("direccion").get("direccionentrega")+"' \r\n"
//      		+ "    					?\r\n"
      		+ "    					)";
      
      rows = jdbcTemplate.update(sql);
      
      
      
      //insertamos datos en la tabla Negocio Usuario Rol
	  
      seq="select nextval('negocio_usuario_rol_idnegusurol_seq'::regclass)";
		 
		 Long seqnegusurol = jdbcTemplate.queryForObject(
	                seq,
	                Long.class);
		 jdbcTemplate.execute(seq);
      sql="insert into Negocio_Usuario_Rol\r\n"
      		+ "	  (\r\n"
      		+ "		IdNegUsuRol,IdNeg_NegUsuRol,\r\n"
      		+ "		IdUsu_NegUsuRol,\r\n"
      		+ "		IdRolUsu_NegUsuRol,\r\n"
      		+ "		EstNegUsuRol,   \r\n"
      		+ "		FecCreNegUsuRol \r\n"
//      		+ "		UsuCreNegUsuRol,\r\n"
//      		+ "		FecModNegUsuRol,\r\n"
//      		+ "		UsuModNegUsuRol\r\n"
      		+ "	  )\r\n"
      		+ "	  values \r\n"
      		+ "	  (\r\n"
      		+ "	 	"+seqnegusurol+","+seqneg+",\r\n"
      		+ "		"+sequsu+",\r\n"
      		+ "		1,\r\n"
      		+ "		1,\r\n"
      		+ "		current_date \r\n"
//      		+ "		UsuCreNegUsuRolF,\r\n"
//      		+ "		FecModNegUsuRolF,\r\n"
//      		+ "		UsuModNegUsuRolF\r\n"
      		+ "	  )";
      
      
	  try {
		 String prueba= lambdaRequest.toString();
		 System.out.println("prueba : "+ prueba);
	} catch (Exception e) {
		// TODO: handle exception
	}
	  
	  return LambdaResponse.builder().mensaje("OK").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).build();
	 
	 }
	 
	 
	 
	 @Override
	    public LambdaResponse obtenerNegocio(LambdaRequest lambdaRequest) {
		 
		 Map<String,Object> m=new HashMap<String,Object>();
		   // jdbctemplate.queryForMap("SELECT userid,username  FROM USER");
		 String sql="";
		 
	   sql="  "
	  		+ "select dn.DirEntDirNeg as direccionEntrega,\r\n"
	  		+ "n.NomComNeg as nombreComercial,\r\n"
	  		+ "n.TipNeg as tipoNegocio,\r\n"
	  		+ "n.NroDocNeg as nroDocumentoNegocio,\r\n"
	  		+ "n.CorEleNeg as correoNegocio,\r\n"
	  		+ "n.RazSocNeg as razonSocial,\r\n"
	  		+ "n.TipDocNeg as tipoDocumento,\r\n"
	  		+ "u.NomUsu as nombreDueño,\r\n"
	  		+ "u.ApePatUsu as apellidoPaternoDueño,\r\n"
	  		+ "u.ApeMatUsu as apellidoMaternoDueño,\r\n"
	  		+ "u.PerUsu as perfilDueño,\r\n"
	  		+ "u.FecCreUsu as fechaCreacion,\r\n"
	  		+ "u.TelUsu as numeroContacto,\r\n"
	  		+ "u.CorUsu as correoUsuario,\r\n"
	  		+ "m.DescMaestro as tipoNegocio,\r\n"
	  		+ "d.NomDis as nombreDistrito,\r\n"
	  		+ "p.NomProv as nombreProvincia,\r\n"
	  		+ "de.NomDep as nombreDepartamento,\r\n"
	  		+ "dn2.DescMaestro as descripcionUsuario,\r\n"
	  		+ "dn3.DescMaestro as tipoDocumentoNegocio,\r\n"
	  		+ "dn4.DescMaestro as tipoDocumentoUsuario\r\n"
	  		+ "from Direccion_Negocio dn\r\n"
	  		+ "inner join Negocio n\r\n"
	  		+ "on dn.IdNeg_DirNeg = n.IdNeg\r\n"
	  		+ "inner join Negocio_Usuario_Rol nur\r\n"
	  		+ "on n.IdNeg = nur.IdNeg_NegUsuRol\r\n"
	  		+ "inner join Usuario u\r\n"
	  		+ "on nur.IdUsu_NegUsuRol = u.IdUsu\r\n"
	  		+ "inner join Maestros m\r\n"
	  		+ "on n.TipNeg = m.CodMaestro\r\n"
	  		+ "inner join Distrito d\r\n"
	  		+ "on dn.IdDis_DirNeg = d.IdDis\r\n"
	  		+ "inner join Provincia p\r\n"
	  		+ "on d.IdProvDis = p.IdProv\r\n"
	  		+ "inner join Departamento de\r\n"
	  		+ "on p.IdDepProv = de.IdDep\r\n"
	  		+ "inner join Maestros dn2\r\n"
	  		+ "on u.PerUsu = dn2.CodMaestro\r\n"
	  		+ "inner join Maestros dn3\r\n"
	  		+ "on n.TipDocNeg = dn3.CodMaestro\r\n"
	  		+ "inner join Maestros dn4\r\n"
	  		+ "on u.TipDocUsu = dn4.CodMaestro\r\n"
	  		+ "where  m.NombreTabla = 'Tipo Negocio'\r\n"
	  		+ "and dn2.NombreTabla='Tipo Usuario'\r\n"
	  		+ "and dn3.NombreTabla='Tipo Documento Identidad'\r\n"
	  		+ "and dn4.NombreTabla='Tipo Documento Identidad' "
	  		;
 
	   
	   if(lambdaRequest.getParametros().get("idnegocio")!=null) {
			sql=sql+ "and 	  	n.IdNeg= "+lambdaRequest.getParametros().get("idnegocio")+" ";
		 }
	  //m = jdbcTemplate.queryForMap(sql);
	  List<Map<String, Object>> lstNegocios = jdbcTemplate.queryForList(sql);
	  m.put("negocios", lstNegocios);
//	  List<Negocio> customers = jdbcTemplate.query(
//              sql,
//              new BeanPropertyRowMapper(Negocio.class));
	  return LambdaResponse.builder().mensaje("OK").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).data(m).build();
	 
	 }
	 
	 
	 @Override
	    public LambdaResponse obtenerDepartamentos(LambdaRequest lambdaRequest) {
		 
		 Map<String,Object> m=new HashMap<String,Object>();
		   // jdbctemplate.queryForMap("SELECT userid,username  FROM USER");
		 String sql="";
		 
	   sql="  "
	  		+ "select dn.DirEntDirNeg as direccionEntrega,\r\n"
	  		+ "n.NomComNeg as nombreComercial,\r\n"
	  		+ "n.TipNeg as tipoNegocio,\r\n"
	  		+ "n.NroDocNeg as nroDocumentoNegocio,\r\n"
	  		+ "n.CorEleNeg as correoNegocio,\r\n"
	  		+ "n.RazSocNeg as razonSocial,\r\n"
	  		+ "n.TipDocNeg as tipoDocumento,\r\n"
	  		+ "u.NomUsu as nombreDueño,\r\n"
	  		+ "u.ApePatUsu as apellidoPaternoDueño,\r\n"
	  		+ "u.ApeMatUsu as apellidoMaternoDueño,\r\n"
	  		+ "u.PerUsu as perfilDueño,\r\n"
	  		+ "u.FecCreUsu as fechaCreacion,\r\n"
	  		+ "u.TelUsu as numeroContacto,\r\n"
	  		+ "u.CorUsu as correoUsuario,\r\n"
	  		+ "m.DescMaestro as tipoNegocio,\r\n"
	  		+ "d.NomDis as nombreDistrito,\r\n"
	  		+ "p.NomProv as nombreProvincia,\r\n"
	  		+ "de.NomDep as nombreDepartamento,\r\n"
	  		+ "dn2.DescMaestro as descripcionUsuario,\r\n"
	  		+ "dn3.DescMaestro as tipoDocumentoNegocio,\r\n"
	  		+ "dn4.DescMaestro as tipoDocumentoUsuario\r\n"
	  		+ "from Direccion_Negocio dn\r\n"
	  		+ "inner join Negocio n\r\n"
	  		+ "on dn.IdNeg_DirNeg = n.IdNeg\r\n"
	  		+ "inner join Negocio_Usuario_Rol nur\r\n"
	  		+ "on n.IdNeg = nur.IdNeg_NegUsuRol\r\n"
	  		+ "inner join Usuario u\r\n"
	  		+ "on nur.IdUsu_NegUsuRol = u.IdUsu\r\n"
	  		+ "inner join Maestros m\r\n"
	  		+ "on n.TipNeg = m.CodMaestro\r\n"
	  		+ "inner join Distrito d\r\n"
	  		+ "on dn.IdDis_DirNeg = d.IdDis\r\n"
	  		+ "inner join Provincia p\r\n"
	  		+ "on d.IdProvDis = p.IdProv\r\n"
	  		+ "inner join Departamento de\r\n"
	  		+ "on p.IdDepProv = de.IdDep\r\n"
	  		+ "inner join Maestros dn2\r\n"
	  		+ "on u.PerUsu = dn2.CodMaestro\r\n"
	  		+ "inner join Maestros dn3\r\n"
	  		+ "on n.TipDocNeg = dn3.CodMaestro\r\n"
	  		+ "inner join Maestros dn4\r\n"
	  		+ "on u.TipDocUsu = dn4.CodMaestro\r\n"
	  		+ "where  m.NombreTabla = 'Tipo Negocio'\r\n"
	  		+ "and dn2.NombreTabla='Tipo Usuario'\r\n"
	  		+ "and dn3.NombreTabla='Tipo Documento Identidad'\r\n"
	  		+ "and dn4.NombreTabla='Tipo Documento Identidad' "
	  		;

	   
	   if(lambdaRequest.getParametros().get("idnegocio")!=null) {
			sql=sql+ "and 	  	n.IdNeg= "+lambdaRequest.getParametros().get("idnegocio")+" ";
		 }

	  List<Map<String, Object>> lstNegocios = jdbcTemplate.queryForList(sql);
	  m.put("negocios", lstNegocios);

	  return LambdaResponse.builder().mensaje("OK").metodo(lambdaRequest.getMetodo()).entidad(lambdaRequest.getEntidad()).data(m).build();
	 
	 }

}
