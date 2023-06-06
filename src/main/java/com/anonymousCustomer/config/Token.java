package com.anonymousCustomer.config;

import com.commercetools.api.defaultconfig.ServiceRegion;
import com.commercetools.http.okhttp4.CtOkHttp4Client;
import io.vrap.rmf.base.client.AuthenticationToken;
import io.vrap.rmf.base.client.VrapHttpClient;
import io.vrap.rmf.base.client.oauth2.GlobalCustomerPasswordTokenSupplier;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
@Configuration
public class Token {
    public static AuthenticationToken getLoggedInToken(String email, String password) {

        VrapHttpClient httpClient = new CtOkHttp4Client(builder -> builder.connectTimeout(Duration.ofSeconds(1)));

        GlobalCustomerPasswordTokenSupplier gc = new GlobalCustomerPasswordTokenSupplier(
                "vwagPUIXULDuI40hOMctH-iU",
                "3QNhgLpYE7xX63lVWniL4C4FQ0KkyQjk",
                email,
                password,
                "manage_my_profile:aditya",
                ServiceRegion.GCP_AUSTRALIA_SOUTHEAST1.getPasswordFlowTokenURL("aditya"),
                httpClient
        );
        CompletableFuture<AuthenticationToken> tokenStage = gc.getToken();

        System.out.println(tokenStage.toCompletableFuture().join().getAccessToken());
        return tokenStage.toCompletableFuture().join();
    }
}
