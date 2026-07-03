package com.nicico.committee.dto;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class AssignPersonToPostRequest {

    @NotBlank(message = "Person's national code is required")
    private String nationalCode;

    @NotBlank(message = "Post code is required")
    private String postCode;

    @NotNull(message = "Company ID is required")
    private Long companyId;
}
