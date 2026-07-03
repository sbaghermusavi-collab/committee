package com.nicico.committee.dto;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class CreateHrmCompanyStructureRequest {

    @NotNull(message = "Company ID is required")
    private Long companyId;

    @NotBlank(message = "Version code is required")
    private String code;

    @NotBlank(message = "Version name is required")
    private String name;
}
