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

//        static String clientId = "ChgZLBveUGkFHnDrsEj37VPS";
//        static String clientSecret ="kll4jlus28A4Sryd4rAwwxiAn_RmP9tP";
//        static String projectKey = "adityas-project";

//         static String clientId = "cCBLBxrvVGvUU3xAINXdpbNp";
//         static String clientSecret ="iCpSImhEFw99bF5XDRK8tpoSQVbfaS7O";
//         static String projectKey = "hybris-test";

//    private static String projectKey = "sunrisedata";
//    private static String client_id = "dR66htzM25s-XmF_Nz7oGzou";
//    private static String client_secret = "GgLB-pLF6I8zpTD-xeNqWCD7XGGOm7V9";

//    private static String projectKey = "sunrisedata-new";
//    private static String client_id = "nMZgwIFnfZS55i-LZkVpK3Qf";
//    private static String client_secret = "D_FLhGlyZGazmBP_C2cFHqp_GM7_H5lt";


//    private static String projectKey = "aditya";
//    private static String client_id = "zQujxGZ_hbxIx6CvQCxYNvcB";
//    private static String client_secret = "hxTUfX3118B3XC4PTlez_Y3DilTX4PIc";

    private static String projectKey = "sunrisedata-new";
    private static String clientId = "nMZgwIFnfZS55i-LZkVpK3Qf";
    private static String clientSecret = "D_FLhGlyZGazmBP_C2cFHqp_GM7_H5lt";


    @Bean
        public static ProjectApiRoot createApiClient() {
            final ProjectApiRoot apiRoot = ApiRootBuilder.of()
                    .defaultClient(ClientCredentials.of()
                                    .withClientId(clientId)
                                    .withClientSecret(clientSecret)
                                    .build(),
                            ServiceRegion.GCP_AUSTRALIA_SOUTHEAST1)
                    .build(projectKey);



//                    .defaultClient(ClientCredentials.of()
//                                    .withClientId("1hxnBn7OrDEtSiHs8cXGp164")
//                                    .withClientSecret("AnvooCnqKLjmMiMfnwJw2Qyf5j0TNm1r")
//
//                                    .build(),
//                            ServiceRegion.GCP_AUSTRALIA_SOUTHEAST1)
//                    .build("project-new");

            return apiRoot;
        }

    }

