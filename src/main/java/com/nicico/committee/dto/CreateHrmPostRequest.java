package com.nicico.committee.dto;

import lombok.Data;

import jakarta.validation.constraints.NotNull;

@Data
public class CreateHrmPostRequest {

    @NotNull(message = "Post role code is required")
    private PostRoleCode postRoleCode;

    private String title;
    private Long companyId;
}
