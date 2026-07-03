package com.nicico.committee.client.hrm;//package com.nicico.committee.client.hrm;
//
//import lombok.extern.slf4j.Slf4j;
//import okhttp3.*;
//import org.springframework.lang.Nullable;
//import org.springframework.security.oauth2.client.OAuth2RestTemplate;
//import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//
//import java.io.IOException;
//
//@Slf4j
//public class PasswordGrantAuthenticator implements Authenticator {
//
//    private final OAuth2RestTemplate oAuth2RestTemplate;
//    private volatile OAuth2AccessToken token;
//
//    public PasswordGrantAuthenticator(
//            String accessTokenUri,
//            String clientId,
//            String clientSecret,
//            String username,
//            String password
//    ) {
//        // Create the resource details for the password grant
//        ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
//        resourceDetails.setGrantType("password");
//        resourceDetails.setAccessTokenUri(accessTokenUri);
//        resourceDetails.setClientId(clientId);
//        resourceDetails.setClientSecret(clientSecret);
//        resourceDetails.setUsername(username);
//        resourceDetails.setPassword(password);
//
//        this.oAuth2RestTemplate = new OAuth2RestTemplate(resourceDetails);
//        // Eagerly fetch the first token on startup
//        this.token = oAuth2RestTemplate.getAccessToken();
//        log.info("## PasswordGrantAuthenticator: Initial system token acquired.");
//    }
//
//    /**
//     * Returns the current valid token, fetching a new one if necessary.
//     * This method is thread-safe.
//     */
//    public synchronized String getValidToken() {
//
//        try {
//            this.token = oAuth2RestTemplate.getAccessToken();
//            return this.token.getValue();
//        } catch (Exception e) {
//            log.error("Could not get a valid token from the authentication server.", e);
//            throw new RuntimeException("Failed to acquire system token", e);
//        }
//    }
//
//    @Nullable
//    @Override
//    public Request authenticate(Route route, Response response) throws IOException {
//        log.warn("API request to {} failed with 401. Attempting to re-authenticate.", response.request().url());
//
//        String currentTokenValue = (this.token != null) ? this.token.getValue() : "";
//        String failedRequestToken = response.request().header("Authorization");
//
//        synchronized (this) {
//            if (failedRequestToken != null && !failedRequestToken.equals("Bearer " + currentTokenValue)) {
//                 log.info("Token was already refreshed by another thread. Retrying request.");
//                 return response.request().newBuilder()
//                        .header("Authorization", "Bearer " + this.token.getValue())
//                        .build();
//            }
//
//            log.info("Forcing re-authentication for system user.");
//            oAuth2RestTemplate.getOAuth2ClientContext().setAccessToken(null);
//            try {
//                String newToken = getValidToken(); // This will trigger a new password grant request
//                log.info("Successfully re-authenticated. Retrying the failed API request.");
//                return response.request().newBuilder()
//                        .header("Authorization", "Bearer " + newToken)
//                        .build();
//            } catch (Exception e) {
//                log.error("!!! Critical: Failed to re-authenticate system user after 401. Cannot retry request.", e);
//                return null;
//            }
//        }
//    }
//}