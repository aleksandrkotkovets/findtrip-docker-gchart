package com.sam_solutions.findtrip.exception;

import com.sam_solutions.findtrip.controller.dto.CompanyDTO;

public class EditCompanyParameterExistException extends RuntimeException {

    CompanyDTO companyDTO;

    public EditCompanyParameterExistException(String message, CompanyDTO companyDTO) {
        super(message);
        this.companyDTO = companyDTO;
    }
}
