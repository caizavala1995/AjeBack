package com.function.app.functionmicroservice.service.consumer;

import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import com.function.app.functionmicroservice.model.CompanyRq;
import com.function.app.functionmicroservice.service.CompanyService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class CompanyConsumer implements Consumer<CompanyRq> {

	private CompanyService companyService;

	@Override
	public void accept(CompanyRq companyRq) {
		log.info("Company Information: {}", companyService.createCompany(companyRq).toString());
	}

}