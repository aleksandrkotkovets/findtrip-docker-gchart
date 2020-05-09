package com.sam_solutions.findtrip.exception;

import com.sam_solutions.findtrip.controller.dto.PlaneDTO;

public class EditPlaneParametersExistException extends RuntimeException {

    PlaneDTO planeDTO;
    String companyName;
    Long companyId;

    public EditPlaneParametersExistException(String message, PlaneDTO planeDTO, String companyName, Long companyId) {
        super(message);
        this.planeDTO = planeDTO;
        this.companyName = companyName;
        this.companyId = companyId;
    }
}
