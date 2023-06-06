package com.anonymousCustomer.me;

import com.anonymousCustomer.Exception.EmptyInputException;
import com.anonymousCustomer.config.Token;
import com.commercetools.api.models.customer.*;
import com.commercetools.api.models.me.MyCustomerDraft;
import io.vrap.rmf.base.client.AuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {
    @Autowired
    MyDataProvider myDataProvider;
    @Autowired
    Token token;
    @Autowired
    LoginResponce responce;

//    public CustomerSignInResult login(MyData myData,String authHeader) {
//
//      token.getLoggedInToken(myData.getEmail(),myData.getPassword());
//
//        MyCustomerSignin myCustomerSignin = MyCustomerSignin
//                .builder()
//                .email(myData.email)
//                .password(myData.password)
//                .activeCartSignInMode(AnonymousCartSignInMode.MERGE_WITH_EXISTING_CUSTOMER_CART)
//                .build();
//        return myDataProvider.login(myCustomerSignin,authHeader);
//    }

    public LoginResponce login(MyData myData,String authHeader) {
        if (myData.getEmail().isEmpty()||myData.getEmail().length()==0){
            throw new EmptyInputException("input feild empty");
        }
        MyCustomerSignin myCustomerSignin = MyCustomerSignin
                .builder()
                .email(myData.getEmail())
                .password(myData.getPassword())
                .activeCartSignInMode(AnonymousCartSignInMode.MERGE_WITH_EXISTING_CUSTOMER_CART)
                .build();
        responce.setCustomerSignInResult(myDataProvider.login(myCustomerSignin,authHeader));
        responce.setAuthenticationToken(token.getLoggedInToken(myData.getEmail(),myData.getPassword()));
         return responce;
    }


    public CustomerSignInResult signUp(MyData myData,String authHeader) {
        MyCustomerDraft draft =MyCustomerDraft.builder()
                .email(myData.email)
                .firstName(myData.firstName)
                .lastName(myData.lastName)
                .password(myData.password)
                .build();
        return myDataProvider.signUp(draft,authHeader);

    }

    public Customer myCustomer(String authHeader) {
        return myDataProvider.myCustomer(authHeader);
    }

    public Customer deleteCustomer(String authHeader) {
       Customer customer= myCustomer(authHeader);
       Long version= customer.getVersion();
        return myDataProvider.deleteCustomer(version,authHeader);
    }

    public Customer emailVerify(String authHeader, MyData myData) {
        MyCustomerEmailVerify verify=MyCustomerEmailVerify.builder()
                .tokenValue(myData.getToken())
                .build();
        return myDataProvider.emailVerify(verify,authHeader);
    }

    public CustomerToken emailChek(MyData myData, String authHeader) {
        return myDataProvider.emailChek(myData,authHeader);
    }
}
