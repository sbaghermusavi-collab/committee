package com.nicico.committee.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserInfoOpaqueTokenIntrospector
        implements OpaqueTokenIntrospector {

    private final UserInfoClient userInfoClient;

    @Override
    public OAuth2AuthenticatedPrincipal introspect(String token) {

        UserInfo user = userInfoClient.loadUser(token);

        Collection<GrantedAuthority> authorities =
                user.getAuthorities()
                        .stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toSet());

        return new DefaultOAuth2AuthenticatedPrincipal(
                Map.of(
                        "username", user.getUsername()
                ),
                authorities
        );
    }
}