package com.anonymousCustomer.product;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.product.ProductPagedQueryResponse;
import com.commercetools.api.models.product.ProductProjection;
import com.commercetools.api.models.product.ProductProjectionPagedQueryResponse;
import com.commercetools.api.models.product.ProductProjectionPagedSearchResponse;
import com.commercetools.api.models.product_selection.ProductSelection;
import com.commercetools.api.models.product_selection.ProductSelectionPagedQueryResponse;
import com.commercetools.api.models.product_type.AttributeType;
import com.commercetools.api.models.product_type.ProductTypePagedQueryResponse;
import com.commercetools.api.models.product_type.ProductTypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProjectApiRoot apiRoot;
    @GetMapping("/p")
    public ProductPagedQueryResponse response(@RequestHeader("Authorization") String authHeader){
        return apiRoot.products().get().addHeader("Authorization",authHeader).executeBlocking().getBody();

//        "statusCode" : 403,
//        "message" : "Insufficient scope. One of the following scopes is missing: view_product_selections.",
    }
    @GetMapping
    public ProductProjectionPagedQueryResponse queryResponse(@RequestHeader("Authorization") String authHeader){
        return apiRoot.productProjections().get().addHeader("Authorization",authHeader).executeBlocking().getBody();
    }

    @GetMapping("/{id}")
    ProductProjection projection(@PathVariable String id, @RequestHeader("Authorization") String authHeader){
        return apiRoot.productProjections().withId(id).get().addHeader("Authorization",authHeader).executeBlocking().getBody();
    }

    @GetMapping("/pr")
    public ProductProjectionPagedSearchResponse searchResponse(@RequestHeader("Authorization") String authHeader){
        return apiRoot.productProjections().search().post().addFormParam("text.DE-DE","ice").addHeader("Authorization",authHeader).executeBlocking().getBody();

//        "statusCode" : 400,
//        "message" : "The endpoint is deactivated for this project. Please enable it via the Project endpoint,
//        via the Merchant Center in the Project settings, or reach out to Support to enable it.",
    }
    @GetMapping("/ps")
    public ProductSelectionPagedQueryResponse pagedQueryResponse(@RequestHeader("Authorization") String authHeader){
        return apiRoot.productSelections().get().addHeader("Authorization",authHeader).executeBlocking().getBody();

//        "statusCode" : 403,
//        "message" : "Insufficient scope. One of the following scopes is missing: view_product_selections.",
    }

    @GetMapping("/type")
    public List<AttributeType> type(@RequestHeader("Authorization") String authHeader){
        ProductTypePagedQueryResponse queryResponse = apiRoot.productTypes().get().addHeader("Authorization",authHeader).executeBlocking().getBody();

        return queryResponse.getResults().stream().map(productType -> productType
                .getAttribute("color").getType()).collect(Collectors.toList());

//        "statusCode" : 403,
//        "message" : "Insufficient scope. One of the following scopes is missing: view_products.",
    }
    @GetMapping("/test")
   public ProductTypeReference response22(@RequestHeader("Authorization") String authHeader){
     return apiRoot.productProjections().get().addExpand("productType").addHeader("Authorization",authHeader).executeBlocking().getBody().getResults().get(0).getProductType();
    }
}
