package com.anonymousCustomer.me;

import com.anonymousCustomer.config.Client;
import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.customer.*;
import com.commercetools.api.models.me.MyCustomerDraft;
import org.springframework.stereotype.Component;


@Component
public class MyDataProvider {

    ProjectApiRoot apiRoot=Client.createApiClient();
    public CustomerSignInResult login(MyCustomerSignin customerSignin,String authHeader) {
        return apiRoot.me().login().post(customerSignin).addHeader("Authorization", authHeader).executeBlocking().getBody();
    }

    public CustomerSignInResult signUp(MyCustomerDraft draft,String authHeader) {
      CustomerSignInResult sdfg= apiRoot.me().signup().post(draft).addHeader("Authorization", authHeader).executeBlocking().getBody();

      return sdfg;
    }


//public CustomerSignInResult signUp(MyCustomerDraft draft, String authHeader) {
//    try {
//        return apiRoot.me().signup().post(draft).addHeader("Authorization", authHeader).executeBlocking().getBody();
//    } catch (Exception e) {
//        // Handle the exception
//        String errorMessage = e.getMessage();
////        int statusCode = e.g();
//        // Implement your error handling logic here
//        // For example, log the error or throw a custom exception
//        try {
//            throw new Exception("An error occurred during customer signup: " + errorMessage, e);
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
//    }
//}


    public Customer myCustomer(String authHeader) {
        return apiRoot.me().get().addHeader("Authorization", authHeader).executeBlocking().getBody();
    }

    public Customer deleteCustomer(Long version,String authHeader) {
        return apiRoot.me().delete().addVersion(version).addHeader("Authorization", authHeader).executeBlocking().getBody();
    }


    public Customer emailVerify(MyCustomerEmailVerify verify, String authHeader) {

//        CustomerCreateEmailToken emailToken=CustomerCreateEmailToken.builder()
//                .id("id")
//                .ttlMinutes(3200L)
//                .build();
//        CustomerToken tolen=apiRoot.customers().emailToken().post(emailToken).executeBlocking().getBody();

        return apiRoot.me().emailConfirm().post(verify).addHeader("Authorization",authHeader).executeBlocking().getBody();
    }

    public CustomerToken emailChek(MyData myData, String authHeader) {
        return apiRoot.customers().emailToken().post(myData.getEmail()).addHeader("Authorization",authHeader).executeBlocking().getBody();
    }
}
