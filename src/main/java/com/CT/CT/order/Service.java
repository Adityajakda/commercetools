package com.CT.CT.order;

import com.commercetools.api.models.cart.CartReference;
import com.commercetools.api.models.cart.CartResourceIdentifier;
import com.commercetools.api.models.order.*;
import org.springframework.beans.factory.annotation.Autowired;


@org.springframework.stereotype.Service
public class Service {
    @Autowired
    OrderDTO dto;

    OrderDataProvider odp=new OrderDataProvider();
    Order updateOrderLineItem(){
        OrderUpdate update=OrderUpdate.builder()
                .version(4L)
                .plusActions(actionBuilder -> actionBuilder
                        .setCustomTypeBuilder()
                        .type(builder -> builder.id(""))
                        .fields(fieldContainerBuilder -> fieldContainerBuilder
                                .addValue(" "," ")
                                .addValue(" "," ")
                                .addValue(" "," ")
                        ))
                .build();
        return null;
    }

    public OrderFromCartDraft createOrder(OrderDTO dto) {
        OrderFromCartDraft orderImportDraft = OrderFromCartDraft.builder()
                .cart(CartResourceIdentifier.builder().id(dto.cart).build())
                .version(2L)
                .orderNumber(dto.orderNumber)

                .build();
      odp.createOrder(orderImportDraft);
      return null;
    }

    public Order orderByNumber(String number) {
       return odp.orderByNumber(number);
    }

    public Order orderUpdate(String number, OrderDTO dto) {
      Order order=  orderByNumber(number);
        OrderUpdate orderUpdate=OrderUpdate
                .builder()
                .version(order.getVersion())
                .actions(OrderUpdateActionBuilder.of().addDeliveryBuilder().build())
                .build();
        return odp.orderUpdate(number,orderUpdate);
    }
}
