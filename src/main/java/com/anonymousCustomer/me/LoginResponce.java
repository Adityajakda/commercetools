package com.anonymousCustomer.me;

import com.commercetools.api.models.customer.CustomerSignInResult;
import io.vrap.rmf.base.client.AuthenticationToken;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class LoginResponce {

    CustomerSignInResult customerSignInResult;
    AuthenticationToken authenticationToken;
}
