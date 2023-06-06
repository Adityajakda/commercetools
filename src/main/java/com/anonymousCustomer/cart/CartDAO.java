package com.anonymousCustomer.cart;

import com.anonymousCustomer.config.Client;
import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.cart.Cart;
import com.commercetools.api.models.cart.CartDraft;
import com.commercetools.api.models.cart.CartPagedQueryResponse;
import com.commercetools.api.models.me.MyCartDraft;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartDAO {

    @Autowired
    ProjectApiRoot apiRoot;

    public CartPagedQueryResponse getAllCart(String authheder) {
        return apiRoot.carts().get().addHeader("Authorization",authheder).executeBlocking().getBody();
    }

    public Cart cartCreate(MyCartDraft cartDraft, String authHeader) {
        return apiRoot.me().carts().post(cartDraft).addHeader("Authorization",authHeader).executeBlocking().getBody();
    }
}
