package com.CT.CT.order;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.order.Order;
import com.commercetools.api.models.order.OrderFromCartDraft;
import com.commercetools.api.models.order.OrderPagedQueryResponse;
import com.commercetools.api.models.order_edit.OrderEditPagedQueryResponse;
import com.commercetools.api.models.project.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
    @Autowired
    ProjectApiRoot project;

    @Autowired
    Service service;

    @GetMapping("/order")
    public OrderPagedQueryResponse order(){
        return project.orders().get().executeBlocking().getBody();
    }

//    get all order edit
    @GetMapping("/order/edit")
    public OrderEditPagedQueryResponse orderEditPagedQueryResponse(){
        return project.orders().edits().get().executeBlocking().getBody();
    }

    @PostMapping("/order")
    public OrderFromCartDraft createOrder(@RequestBody OrderDTO dto){
       return service.createOrder(dto);
    }

    @GetMapping("/order/{number}")
    public Order orderById(@PathVariable String number){
        return service.orderByNumber(number);
    }

    @PostMapping("/order/{number}")
    public Order orderUpdate(@PathVariable String number, @RequestBody OrderDTO dto){
        return service.orderUpdate(number,dto);
    }
}
