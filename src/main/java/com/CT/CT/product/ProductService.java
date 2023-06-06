package com.CT.CT.product;

import com.commercetools.api.models.channel.Channel;
import com.commercetools.api.models.channel.ChannelDraft;
import com.commercetools.api.models.channel.ChannelRoleEnum;
import com.commercetools.api.models.common.*;
import com.commercetools.api.models.product.*;
import com.commercetools.api.models.product_type.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductService {

    ProductDataProvider pdp = new ProductDataProvider();
    public Product createProduct(ProductData productData) {

//        ProductType type = createType(productData);
        ProductDraft productDraft = ProductDraft.builder()
                .productType(ProductTypeResourceIdentifier.builder().id(productData.getProductTypeId()).build())
                .name(LocalizedString.builder().addValue(productData.getLang(),productData.getName()).build())
                .slug(LocalizedString.builder().addValue(productData.getLang(),productData.getSlug()).build())
                .key(productData.getKey())
                .description(LocalizedString.builder().addValue(productData.getLang(),productData.getDescription()).build())
                .publish(productData.getPublish())
                .masterVariant(ProductVariantDraftBuilder.of()
                        .prices(PriceDraft
                                .builder()
                                .key(productData.getPriceKey())
                                .value(MoneyBuilder.of()
                                        .currencyCode(productData.getCurrencyCode())
                                        .centAmount(productData.getAmount())

                                        .build())
                                .country(productData.getCountry())
                                .build())

                        .sku(productData.getSku())
                        .key(productData.getMKey())
                        .images(Image.builder()
                                .url(productData.getUrl())
                                .dimensions(ImageDimensions.builder().h(productData.getH()).w(productData.getW()).build())
                                .label(productData.getImgLable())
                                .build()
                                )
//                        .attributes(Attribute.builder()
//                                .name("yjgyg")
//                                .value("gjyg")
//                                .build()
//                        )
                        .build()
                )
//                .variants()
                .build();
        return pdp.createProduct(productDraft);
    }
    public ProductType createType(ProductData productData)
    {
// add list of attributes at one time.
        List<AttributeDefinitionDraft> AtList =productData.getAttributes().stream().map(attributes -> {
            return AttributeDefinitionDraft.builder()
                    .label(LocalizedString.builder().addValue(attributes.getLang(), attributes.getLable()).build())
                    .name(attributes.getLableName())
                    .type(AttributeType.textBuilder().build())
                    .isSearchable(attributes.getSearchable())
                    .isRequired(attributes.getRequired())
                    .build();
        }).collect(Collectors.toList());

        ProductTypeDraft productTypeDraft = ProductTypeDraft
                .builder()
                .name(productData.getProductTypeName())
                .description(productData.getProductTypeDescription())
                .key("kuhkj")
//                .attributes(AttributeDefinitionDraft.builder()
//                        .label(LocalizedString.builder().addValue(productData.getAttributes().getLang(),productData.getAttributes().getLable()).build())
//                        .name(productData.getAttributes().getLableName())
//                        .type(AttributeType.textBuilder().build())
//                        .isSearchable(productData.getAttributes().getSearchable())
//                        .isRequired(productData.getAttributes().getRequired())
//                        .build())
                .attributes(AtList)
                .build();
        System.out.println(productTypeDraft.getName());
        return pdp.createType(productTypeDraft);

    }

    public Product deleteProduct(String id ,Long version) {

//        ProductDraft productDraft = ProductDraft.builder()
//                .publish(false)
//                .build();
        
        return pdp.deleteProduct(id,version);
    }
//VariantValues


    public List getProductById(String id) {
        return pdp.getProductById(id);
    }

/*
    public Product productshow(Product product) {
        Product product1 =getProductById(product.getId());
        System.out.println(product1.getMasterData());
        return product1;
    }
*/
    public Channel channel(ProductData productData){
        ChannelDraft channelDraft = ChannelDraft
                .builder()
                .name(LocalizedString.builder().addValue(productData.getAttributes().get(0).getLang(),productData.getAttributes().get(0).getLable()).build())
                .address(BaseAddress.builder().build())
                .key("erf")
                .description(LocalizedString.builder().build())
                .roles(ChannelRoleEnum.findEnum("demo"))
                .build();
        return  pdp.createChannel(channelDraft);
    }

}
