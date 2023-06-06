package com.CT.CT.product;

import com.commercetools.api.models.common.MoneyBuilder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
public class ProductData {

    public String Name;
    public String lang;
    public String slug;
    public String description;
    public  String key;
    public Boolean publish;
    public String type;
    public String ProductTypeName;
    public  String productTypeDescription;
    public List<Attributes> Attributes;
    public String productTypeId;
    public String sku;
    public String mKey;
    public String imgLable;
    public String url;
    public Integer h;
    public Integer w;
    public String priceKey;
    public String currencyCode;
    public String country;
    public Long Amount;


}
