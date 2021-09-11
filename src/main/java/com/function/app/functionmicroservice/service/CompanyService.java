package com.function.app.functionmicroservice.service;

import com.function.app.functionmicroservice.model.CompanyRq;
import com.function.app.functionmicroservice.model.CompanyRs;

public interface CompanyService {
	
	CompanyRs createCompany(CompanyRq companyRq);

}