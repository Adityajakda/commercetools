package com.anonymousCustomer.product;

import com.anonymousCustomer.config.Client;
import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.product.ProductPagedQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDataProvider {

    ProjectApiRoot apiRoot= Client.createApiClient();

    public ProductPagedQueryResponse response() {
      return apiRoot.products().get().addHeader("Authorization", "Bearer xvvDo6i7pjMFV7x5YyYSTYszBmqa7P10").executeBlocking().getBody();
    }
}
