package com.anonymousCustomer.cart;

import com.commercetools.api.models.cart.Cart;
import com.commercetools.api.models.cart.CartPagedQueryResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController{
    @Autowired
    CartService cartService;
    @GetMapping
    public CartPagedQueryResponse response(@RequestHeader("Authorization") String authHeader){
        return cartService.getAllCart(authHeader);
    }

    @PostMapping
    public Cart cartCreate(@RequestHeader("Authorization") String authHeader/*,@RequestBody CartData cartData*/){
    return cartService.cartCreate(authHeader/*,cartData*/);
    }
}
