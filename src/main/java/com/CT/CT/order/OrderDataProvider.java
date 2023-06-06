package com.CT.CT.order;

import com.CT.CT.Client;
import com.CT.CT.product.ProductService;
import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.order.Order;
import com.commercetools.api.models.order.OrderFromCartDraft;
import com.commercetools.api.models.order.OrderUpdate;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderDataProvider {
    ProjectApiRoot apiRoot = Client.createApiClient();
//    @Autowired
//    Service service;

    public Order createOrder(OrderFromCartDraft orderImportDraft) {
        return apiRoot.orders().post(orderImportDraft).executeBlocking().getBody();
    }

    public Order orderByNumber(String number) {
        return apiRoot.orders().withOrderNumber(number).get().executeBlocking().getBody();
    }


    public Order orderUpdate(String number, OrderUpdate orderUpdate) {
        return apiRoot.orders().withOrderNumber(number).post(orderUpdate).executeBlocking().getBody();
    }
}
