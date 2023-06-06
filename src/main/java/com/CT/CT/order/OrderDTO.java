package com.CT.CT.order;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
@Component
public class OrderDTO {

    String cart;
    String orderNumber;

}
