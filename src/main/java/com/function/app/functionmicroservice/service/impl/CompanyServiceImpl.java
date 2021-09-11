package com.function.app.functionmicroservice.service.impl;


import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.function.app.functionmicroservice.model.CompanyRq;
import com.function.app.functionmicroservice.model.CompanyRs;
import com.function.app.functionmicroservice.service.CompanyService;



@Service
public class CompanyServiceImpl implements CompanyService {

	
	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
    @Override
    public CompanyRs createCompany(CompanyRq companyRq) {
        final String companyId = generateId();
        final String companyName = getCompanyName(companyRq.getName());
        /*CompanyRs companyRs=new CompanyRs();
        companyRs.setId(companyId);
        companyRs.setName(companyName);
        companyRs.setDate(LocalDate.now());
        return companyRs;*/
        
        String sql = "INSERT INTO usuario (idusu, nomusu) VALUES ("
                + "998, '"+companyName+"')";
        
        int rows = jdbcTemplate.update(sql);
        if (rows > 0) {
            System.out.println("A new row has been inserted.");
        }
        return CompanyRs.builder().id(companyId).name(companyName).date(LocalDate.now()).build();
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    private String getCompanyName(final String companyName) {
        return companyName.toUpperCase();
    }

}
