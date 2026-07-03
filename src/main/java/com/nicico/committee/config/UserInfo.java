package com.nicico.committee.config;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class UserInfo {

    private String accessToken;
    private String username;
    private String firstName;
    private String lastName;
    private Long userId;
    private String accessMode;
    private String authenticationMode;
    private String nationalCode;

    private List<String> allowedApps;
    private List<String> adminApps;
    private List<String> authorities;
}