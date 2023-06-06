package com.CT.CT.shoppingList;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.shopping_list.ShoppingList;
import com.commercetools.api.models.shopping_list.ShoppingListPagedQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shoppingList")
public class ShoppingListController {
    @Autowired
    ProjectApiRoot projectApiRoot;
//    @Autowired
//    ShoppingData shoppingData;
    @Autowired
    ShoppingService service;
    private  ShoppingDataProvider sdp=new ShoppingDataProvider();

    @GetMapping
    public ShoppingListPagedQueryResponse shoppingListPagedQueryResponse(){
        return projectApiRoot.shoppingLists().get().executeBlocking().getBody();
    }

    @GetMapping("/{id}")
    public ShoppingList shoppingList(@PathVariable String id){
        return projectApiRoot.shoppingLists().withId(id).get().executeBlocking().getBody();
    }


}
