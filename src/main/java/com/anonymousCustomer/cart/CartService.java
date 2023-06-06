package com.anonymousCustomer.cart;

import com.commercetools.api.models.cart.Cart;
import com.commercetools.api.models.cart.CartDraft;
import com.commercetools.api.models.cart.CartPagedQueryResponse;
import com.commercetools.api.models.me.MyCartDraft;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    CartDAO cartDAO;
    public CartPagedQueryResponse getAllCart(String authheder) {
        return cartDAO.getAllCart(authheder);
    }

    public Cart cartCreate(String authHeader/*, CartData cartData*/) {
        MyCartDraft cartDraft=MyCartDraft.builder()
                .currency("INR")
                .build();
        return cartDAO.cartCreate(cartDraft,authHeader);
    }
}
