package com.function.app.functionmicroservice.handler;

import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;


import com.function.app.functionmicroservice.model.UsuarioRq;
import com.function.app.functionmicroservice.model.UsuarioRs;



public class CompanyFunctionAWSHandler extends SpringBootRequestHandler<UsuarioRq, UsuarioRs> {

}