package com.nicico.committee.dto;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class CreateHrmPersonnelRequest {

    // Person fields
    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "National code is required")
    private String nationalCode;

    // Optional Person fields (with defaults)
    private String fatherName;
    private Long genderId;

    // Personnel fields
    @NotBlank(message = "Personnel code is required")
    private String personnelCode;

    @NotNull(message = "Company ID is required")
    private Long companyId;
}
