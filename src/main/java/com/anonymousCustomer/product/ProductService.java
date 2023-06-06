package com.anonymousCustomer.product;

import com.commercetools.api.models.product.ProductPagedQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductDataProvider provider;
    public ProductPagedQueryResponse response() {
        return provider.response();
    }
}
