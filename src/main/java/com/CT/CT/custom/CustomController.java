package com.CT.CT.custom;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.type.TypePagedQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class CustomController {
    @Autowired
    ProjectApiRoot apiRoot;
    @GetMapping("/custom")
    public TypePagedQueryResponse type(){
        return apiRoot.types().get().executeBlocking().getBody();
    }
}
