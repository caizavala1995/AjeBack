package com.function.app.functionmicroservice.service.supplier;

import java.util.function.Supplier;

import org.springframework.stereotype.Component;

import com.function.app.functionmicroservice.model.CompanyRq;
import com.function.app.functionmicroservice.model.CompanyRs;
import com.function.app.functionmicroservice.service.CompanyService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CompanySupplier implements Supplier<CompanyRs> {

	private CompanyService companyService;

	@Override
	public CompanyRs get() {
		CompanyRq companyRq = new CompanyRq();
		companyRq.setName("Coca Cola");
		return companyService.createCompany(companyRq);
	}

}
