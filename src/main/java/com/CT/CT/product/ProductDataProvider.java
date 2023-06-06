package com.CT.CT.product;

import com.CT.CT.Client;
import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.channel.Channel;
import com.commercetools.api.models.channel.ChannelDraft;
import com.commercetools.api.models.product.Product;
import com.commercetools.api.models.product.ProductCatalogData;
import com.commercetools.api.models.product.ProductData;
import com.commercetools.api.models.product.ProductDraft;
import com.commercetools.api.models.product_type.ProductType;
import com.commercetools.api.models.product_type.ProductTypeDraft;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductDataProvider {
    ProjectApiRoot apiRoot = Client.createApiClient();
    @Autowired
    ProductService productService;

    public Product createProduct(ProductDraft productDraft) {
        return apiRoot.products().post(productDraft).executeBlocking().getBody();
    }

    public ProductType createType(ProductTypeDraft productTypeDraft) {
        return apiRoot.productTypes().post(productTypeDraft).executeBlocking().getBody();
    }

    public Channel createChannel(ChannelDraft channelDraft) {
        return apiRoot.channels().post(channelDraft).executeBlocking().getBody();
    }

    public Product deleteProduct(String id, Long version) {
        return apiRoot.products().withId(id).delete().addVersion(version).executeBlocking().getBody();
    }

//    public ProductData getProductById(String id) {
//        return apiRoot.products().withId(id).get().executeBlocking().getBody().getMasterData().getCurrent().getMasterVariant();
//    }
public List getProductById(String id) {
    return apiRoot.products().withId(id).get().executeBlocking().getBody().getMasterData().getCurrent().getMasterVariant().getAttributes();
}

//    public Channel createChannel(ChannelDraft channelDraft) {
//        return apiRoot.channels().post(channelDraft).executeBlocking().getBody();
//    }
}
