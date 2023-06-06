package com.anonymousCustomer.config;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.defaultconfig.ApiRootBuilder;
import com.commercetools.api.defaultconfig.ServiceRegion;
import com.commercetools.http.okhttp4.CtOkHttp4Client;
import io.vrap.rmf.base.client.AuthenticationToken;
import io.vrap.rmf.base.client.TokenIntrospection;
import io.vrap.rmf.base.client.VrapHttpClient;
import io.vrap.rmf.base.client.oauth2.*;
import org.apache.catalina.util.Introspection;
import org.springframework.aop.IntroductionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

@Configuration
public class Client {
    @Value("${app.clientId}")
    private static String clientId="L_seDFwvzVawJYIyvve6U7Kb";
    @Value("${app.clientSecret}")
    private static String clientSecret ="UDlZW3DriIlqbMyekVHV7Q_vHDQUEils";
    @Value("${app.scope}")
    private static String scope= "view_categories:sunrisedata-new create_anonymous_token:sunrisedata-new manage_my_quotes:sunrisedata-new manage_my_business_units:sunrisedata-new manage_my_shopping_lists:sunrisedata-new manage_my_orders:sunrisedata-new manage_my_payments:sunrisedata-new manage_my_quote_requests:sunrisedata-new manage_my_profile:sunrisedata-new view_published_products:sunrisedata-new";
    @Value("${app.projectKey}")
    private static String projectKey ="sunrisedata-new";

    @Bean
    public static AuthenticationToken getAnonymousToken() {
        VrapHttpClient httpClient = new CtOkHttp4Client(builder -> builder.connectTimeout(Duration.ofSeconds(1)));
        AnonymousSessionTokenSupplier gc = new AnonymousSessionTokenSupplier(
                clientId,
                clientSecret,
                scope,
                ServiceRegion.GCP_AUSTRALIA_SOUTHEAST1.getAnonymousFlowTokenURL(projectKey),
                httpClient
        );
        CompletableFuture<AuthenticationToken> tokenStage = gc.getToken();

//        RefreshFlowTokenSupplier sc=new RefreshFlowTokenSupplier(
//                "asdfgfsasdfg",
//                "sdfghgfdsdfghhgfdsrt",
//                ServiceRegion.GCP_AUSTRALIA_SOUTHEAST1.getAnonymousFlowTokenURL(projectKey),
//                gc.getToken().toCompletableFuture().join(),
//                httpClient
//        );
        return tokenStage.toCompletableFuture().join();
    }

    private static AuthenticationToken refereceToken(){
        VrapHttpClient httpClient = new CtOkHttp4Client(builder -> builder.connectTimeout(Duration.ofSeconds(1)));

//        TokenStorageSupplier.LOGGER_AUTH;
        TokenStorage tokenStorage=new InMemoryTokenStorage();
//        TokenStorage tokenStorage1=new TokenStorageSupplier(tokenStorage,);

        RefreshFlowTokenSupplier sc=new RefreshFlowTokenSupplier(
                "asdfgfsasdfg",
                "sdfghgfdsdfghhgfdsrt",
                ServiceRegion.GCP_AUSTRALIA_SOUTHEAST1.getAnonymousFlowTokenURL(projectKey),
                tokenStorage,
                httpClient
        );
        CompletableFuture<AuthenticationToken> tokenStage = sc.getToken();

        return tokenStage.toCompletableFuture().join();
    }

//    public TokenIntrospection TokenIntrospection(){
////        Introspection
//        IntroductionInterceptor
//    }
    @Bean
    public static ProjectApiRoot createApiClient(){
        ProjectApiRoot apiRoot=ApiRootBuilder.of()
                .defaultClient(ServiceRegion.GCP_AUSTRALIA_SOUTHEAST1.getApiUrl())
                .build(projectKey);

        return apiRoot;
    }

}
