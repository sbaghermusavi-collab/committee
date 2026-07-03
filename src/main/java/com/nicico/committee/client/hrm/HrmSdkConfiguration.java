package com.nicico.committee.client.hrm;//package com.nicico.committee.client.hrm;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fgostar.hrm.sdk.client.*;
//import com.fgostar.hrm.sdk.factory.HrmApiClientFactory;
//import lombok.RequiredArgsConstructor;
//import okhttp3.OkHttpClient;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import retrofit2.Retrofit;
//import retrofit2.converter.jackson.JacksonConverterFactory;
//
//import java.util.concurrent.TimeUnit;
//
//@Configuration
//@RequiredArgsConstructor
//public class HrmSdkConfiguration {
//
//    // --- Inject properties from application.properties ---
//    @Value("${hrm.api.base-url}")
//    private String hrmApiBaseUrl;
//    @Value("${spring.security.oauth2.client.provider.oserver.token-uri}")
//    private String accessTokenUri;
//    @Value("${spring.security.oauth2.client.registration.oserver.client-id}")
//    private String clientId;
//    @Value("${spring.security.oauth2.client.registration.oserver.client-secret}")
//    private String clientSecret;
//    @Value("${spring.application.name}")
//    private String appName;
////    @Value("${nicico.security.sysPassword}")
////    private String sysPassword;
//
//    private final ObjectMapper objectMapper;
//    /**
//     * Creates the Authenticator bean, which holds the authentication state.
//     * It's configured directly from your application properties.
//     */
//    @Bean
//    public PasswordGrantAuthenticator passwordGrantAuthenticator() {
//        return new PasswordGrantAuthenticator(
//                accessTokenUri,
//                clientId,
//                clientSecret,
////                String.format("sys_%s", appName),
//                String.format("sys_%s", "training"),
////                sysPassword
//                "password"
//        );
//    }
//
//    /**
//     * Creates a single, shared OkHttpClient configured with our custom
//     * interceptor and authenticator.
//     */
//    @Bean
//    public OkHttpClient hrmOkHttpClient(PasswordGrantAuthenticator authenticator) {
//        return new OkHttpClient.Builder()
//                // The interceptor adds the token to every request
//                .addInterceptor(new PasswordGrantInterceptor(authenticator))
//                .connectTimeout(30, TimeUnit.SECONDS)   // Time to establish connection
//                .readTimeout(60, TimeUnit.SECONDS)      // Time to wait for data
//                .writeTimeout(30, TimeUnit.SECONDS)     // Time to send data
//                .retryOnConnectionFailure(true)
//                // Add the custom error interceptor
//                .addInterceptor(new HrmErrorInterceptor())
//                // The authenticator handles 401s by re-authenticating
//                .authenticator(authenticator)
//                .build();
//    }
//
//
//    /**
//     * Creates the central HrmApiClientFactory bean.
//     * This requires a small change to your HrmApiClientFactory:
//     * add a constructor that accepts a pre-built Retrofit instance.
//     */
//    @Bean
//    public HrmApiClientFactory hrmApiClientFactory(OkHttpClient hrmOkHttpClient) {
//
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(hrmApiBaseUrl)
//                .client(hrmOkHttpClient)
//                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
//                .build();
//
//        return new HrmApiClientFactory(retrofit);
//    }
//
//    // --- Define a bean for each SDK client ---
//    @Bean
//    public PersonnelClient personnelClient(HrmApiClientFactory factory) {
//        return factory.createPersonnelClient();
//    }
//
//    @Bean
//    public CatalogClient catalogClient(HrmApiClientFactory factory) {
//        return factory.createCatalogClient();
//    }
//    @Bean
//    public PostPersonClient postPersonClient(HrmApiClientFactory factory) {
//        return factory.createPostPersonClient();
//    }
//    @Bean
//    public PersonClient personClient(HrmApiClientFactory factory) {
//        return factory.createPersonClient();
//    }
//    @Bean
//    public CompanyClient companyClient(HrmApiClientFactory factory) {
//        return factory.createCompanyClient();
//    }
//    @Bean
//    public CompanyStructureVersionClient companyStructureVersionClient(HrmApiClientFactory factory) {
//        return factory.createCompanyStructureVersionClient();
//    }
//
//    @Bean
//    public PostClient postClient(HrmApiClientFactory factory) {
//        return factory.createPostClient();
//    }
//}
