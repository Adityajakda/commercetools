package com.CT.CT;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.defaultconfig.ApiRootBuilder;
import com.commercetools.api.defaultconfig.ServiceRegion;
import com.commercetools.api.models.customer.Customer;
import io.vrap.rmf.base.client.oauth2.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Client {
    private static String projectKey = "project-key";
    private static String clientId = "clientID";
    private static String clientSecret = "clientSecret";
    @Bean
        public static ProjectApiRoot createApiClient() {
            final ProjectApiRoot apiRoot = ApiRootBuilder.of()
                    .defaultClient(ClientCredentials.of()
                                    .withClientId(clientId)
                                    .withClientSecret(clientSecret)
                                    .build(),
                            ServiceRegion.GCP_AUSTRALIA_SOUTHEAST1
                                   .build(projectKey);
            return apiRoot;
        }
    }

