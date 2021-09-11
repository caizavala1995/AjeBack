package com.function.app.functionmicroservice.model;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyRs { 

    private String id;
    private String name;
    private LocalDate date;

	
}
