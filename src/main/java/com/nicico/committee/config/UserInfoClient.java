package com.nicico.committee.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class UserInfoClient {

    private final RestClient restClient;

    @Value("${spring.security.oauth2.client.provider.oserver.user-info-uri}")
    private String userInfoUri;

    public UserInfo loadUser(String token) {

        return restClient.get()
                .uri(userInfoUri)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .body(UserInfo.class);
    }
}