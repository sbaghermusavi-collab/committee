package com.nicico.committee.dto;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

@Data
public class CreateHrmCompanyRequest {

    @NotBlank(message = "Company name is required")
    private String name;

    @NotBlank(message = "Company economic code is required")
    private String economicCode;

    // Add any other essential fields for creating an HRM company
    private String address;
    private String postalCode;
}
