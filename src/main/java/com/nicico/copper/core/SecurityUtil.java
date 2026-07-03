//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.nicico.copper.core;

import com.nicico.copper.oauth.common.domain.CustomUserDetails;
import com.nicico.copper.oauth.common.enumeration.EOAAccessMode;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {
    private static final Logger log = LoggerFactory.getLogger(SecurityUtil.class);
    private static String currentAppId;
    @Value("${spring.application.name}")
    private String appId;

    @PostConstruct
    public void init() {
        log.info("SecurityUtil: currentAppId=[{}]", this.appId);
        currentAppId = this.appId;
    }

    public static Long getUserId() {
        return (Long)getAttr((map) -> ((Number)map.get("userId")).longValue(), CustomUserDetails::getUserId);
    }

    public static String getUsername() {
        return (String)getAttr((map) -> (String)map.get("username"), User::getUsername);
    }

    public static String getFirstName() {
        return (String)getAttr((map) -> (String)map.get("firstName"), CustomUserDetails::getFirstName);
    }

    public static String getLastName() {
        return (String)getAttr((map) -> (String)map.get("lastName"), CustomUserDetails::getLastName);
    }

    public static String getNationalCode() {
        return (String)getAttr((map) -> (String)map.get("nationalCode"), CustomUserDetails::getNationalCode);
    }

    public static String getFullName() {
        return (String)getAttr((map) -> String.format("%s %s", map.get("firstName"), map.get("lastName")), (cud) -> String.format("%s %s", cud.getFirstName(), cud.getLastName()));
    }

    public static EOAAccessMode getAccessMode() {
        return (EOAAccessMode)getAttr((map) -> map.containsKey("accessMode") ? EOAAccessMode.valueOf((String)map.get("accessMode")) : EOAAccessMode.Normal, CustomUserDetails::getAccessMode);
    }

    public static Collection<String> getAdminApps() {
        return (Collection)getAttr((map) -> (Collection)map.get("adminApps"), (cud) -> (Collection)cud.getAdminApps().stream().map(CustomUserDetails.CustomAppDetails::getId).collect(Collectors.toSet()));
    }

    public static Collection<String> getAllowedApps() {
        return (Collection)getAttr((map) -> (Collection)map.get("allowedApps"), (cud) -> (Collection)cud.getAllowedApps().stream().map(CustomUserDetails.CustomAppDetails::getId).collect(Collectors.toSet()));
    }

    public static boolean isAdmin() {
        return EOAAccessMode.Admin.equals(getAccessMode()) || getAdminApps().contains(currentAppId);
    }

    public static Set<String> getAuthorities() {
        return (Set)SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
    }

    public static Boolean hasAuthority(String authority) {
        return isAdmin() ? true : SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch((auth) -> auth.getAuthority().equals(authority));
    }

    private static <R> R getAttr(Function<Map<String, Object>, R> byUserAttrs, Function<CustomUserDetails, R> byCustomUserDetails) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails) {
            CustomUserDetails customUserDetails = (CustomUserDetails)principal;
            return (R)byCustomUserDetails.apply(customUserDetails);
        } else if (principal instanceof DefaultOAuth2User) {
            DefaultOAuth2User oAuth2User = (DefaultOAuth2User)principal;
            Map<String, Object> attributes = oAuth2User.getAttributes();
            return (R)byUserAttrs.apply(attributes);
        } else {
            return null;
        }
    }
}
