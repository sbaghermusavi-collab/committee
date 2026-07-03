// In your Spring Boot project, e.g., in a 'security' package
package com.nicico.committee.client.hrm;

import java.util.Optional;

/**
 * Provides the authentication token for the current user.
 */
public interface AuthTokenProvider {
    Optional<String> getToken();
}