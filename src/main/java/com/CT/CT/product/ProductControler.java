package com.CT.CT.product;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.product.*;
import com.commercetools.api.models.product_discount.ProductDiscountPagedQueryResponse;
import com.commercetools.api.models.product_type.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductControler{

    @Autowired
    ProjectApiRoot projectApiRoot;
    @Autowired
    ProductData productData;
    private ProductDataProvider pdp = new ProductDataProvider();
    @Autowired
    ProductService productService;

//    @GetMapping("/product")
//    ProductPagedQueryResponse productPage(){
//        return projectApiRoot.products().get().executeBlocking().getBody();
//    }
    @PostMapping
    Product createProduct(@RequestBody ProductData productData){
        return productService.createProduct(productData);
    }
    @PostMapping("/type")
    ProductType createProductType(@RequestBody ProductData productData){
        return productService.createType(productData);
    }

//    @PostMapping("/channel")
//    Channel createchannel(@RequestBody ProductData productData){
//        return productService.channel(productData);
//    }
    @GetMapping
    ProductPagedQueryResponse productPagedQuerywithlimit(@RequestParam(required = true, defaultValue = "25") int limits)
    {
        return projectApiRoot.products().get().withLimit(limits).executeBlocking().getBody();
    }
//    @GetMapping
//    ProductPagedQueryResponse productPagedQuesdfrywithlimit()
//    {
//        return projectApiRoot.products().get().executeBlocking().getBody();
//    }

    @DeleteMapping
    Product deleteProduct(@RequestParam String id,Long version)
    {
        return projectApiRoot.products().withId(id).delete().addVersion(version).executeBlocking().getBody();
//        return productService.deleteProduct(id,version);
    }
    @GetMapping("/product")
    ProductPagedQueryResponse productPagedQueryResponse(@RequestParam String condition){
        return projectApiRoot.products().get().withWhere("condition=:sku","sku",condition).executeBlocking().getBody();
    }
    @GetMapping("/productDiscount")
    ProductDiscountPagedQueryResponse productDiscont(){
        return projectApiRoot.productDiscounts().get().executeBlocking().getBody();
    }
    @GetMapping("/product/id/{id}")
    Product productById(@PathVariable String id){
//        return productService.getProductById(id);
        return projectApiRoot.products().withId(id).get().executeBlocking().getBody();
    }

    @GetMapping("/product/key/{key}")
    Product productByKey(@PathVariable String key){
        return projectApiRoot.products().withKey(key).get().executeBlocking().getBody();
    }
    @GetMapping("/productfilter")
    ProductPagedQueryResponse productPage1(@RequestParam List<String> key){
          return projectApiRoot.products().get().withWhere(key).executeBlocking().getBody();
//        return projectApiRoot.products().get().withWhere("key =:productKey","productKey",key).executeBlocking().getBody();
    }

  /*  @GetMapping("/productSec")
    ProductProjectionPagedSearchResponse productProjectionPagedSearchResponse(@RequestParam String condition){
        return projectApiRoot.productProjections().search().get().addFilter(condition).executeBlocking().getBody();
    }*/

    @GetMapping("/product/filter")
    ProductProjectionPagedSearchResponse productProjectionPagedSearchResponse1(@RequestParam String where)
    {
        return projectApiRoot.productProjections().search().get().addFilter(where).executeBlocking().getBody();
    }

    @GetMapping("/productfly")
    ProductProjectionPagedSearchResponse productProjectionPagedSearchResponse(@RequestParam List<String> where)
    {
        return projectApiRoot.productProjections().search().get().addFilter(where).executeBlocking().getBody();
    }

}
