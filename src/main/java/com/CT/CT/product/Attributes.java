package com.CT.CT.product;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Getter
@Setter
public class Attributes {

    public String lang;
    public String lable;
    public String LableName;
    public Boolean searchable;
    public Boolean Required;


}
