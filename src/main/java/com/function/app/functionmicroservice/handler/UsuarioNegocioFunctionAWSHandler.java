package com.function.app.functionmicroservice.handler;

import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

import com.function.app.functionmicroservice.common.LambdaRequest;
import com.function.app.functionmicroservice.common.LambdaResponse;

public class UsuarioNegocioFunctionAWSHandler extends SpringBootRequestHandler<LambdaRequest, LambdaResponse> {

}
