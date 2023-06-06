package com.anonymousCustomer.mainController;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.cart.CartPagedQueryResponse;
import com.commercetools.api.models.customer.Customer;
import com.commercetools.api.models.customer.CustomerPagedQueryResponse;
import com.commercetools.api.models.product.ProductPagedQueryResponse;
import io.vrap.rmf.base.client.AuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    ProjectApiRoot apiRoot;
    @Autowired
    AuthenticationToken token;


    @GetMapping("/api")
    Customer asd(){
        return apiRoot.me().get().executeBlocking().getBody();
    }
    @GetMapping("/apii")
    CartPagedQueryResponse cart(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.replace("Bearer ", "");
        return apiRoot.me().carts().get().addHeader("Authorization", "Bearer " + token).executeBlocking().getBody();
    }

    @GetMapping("/apiii")
    ProductPagedQueryResponse pagedQueryResponse(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.replace("Bearer ", "");
        return apiRoot.products().get().addHeader("Authorization", "Bearer " + token).executeBlocking().getBody();
    }
    @GetMapping("/appiii")
    CustomerPagedQueryResponse customerPagedQueryResponse(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.replace("Bearer ", "");
        return apiRoot.customers().get().addHeader("Authorization", "Bearer " + token).executeBlocking().getBody();
    }
    @GetMapping("/token")
    public AuthenticationToken s(){
        return token;
    }
}
