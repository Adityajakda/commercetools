package com.CT.CT.customer;

import com.CT.CT.Client;
import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.cart_discount.CartDiscountResourceIdentifier;
import com.commercetools.api.models.common.LocalizedString;
import com.commercetools.api.models.customer.*;
import com.commercetools.api.models.discount_code.DiscountCodeDraft;
import com.commercetools.api.models.type.TypeDraft;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
@Repository
public class CustomerDataProvider {
    @Autowired
    ProjectApiRoot apiRoot;


    public Customer createCustomer(CustomerDraft CustomerDetails) {
        return apiRoot.customers().post(CustomerDetails).executeBlocking().getBody().getCustomer();
    }

    public Type createCostomType(TypeDraft typeDraft) {
        return (Type) apiRoot.types().post(typeDraft).executeBlocking().getBody();
    }

    public CustomerToken customeToken(CustomerCreatePasswordResetToken createPasswordResetToken) {
        return apiRoot.customers().passwordToken().post(createPasswordResetToken).executeBlocking().getBody();
    }

    public Customer resetPassword(CustomerResetPassword customerResetPassword) {
        return apiRoot.customers().passwordReset().post(customerResetPassword).executeBlocking().getBody();
    }

    public Customer changePassword(CustomerChangePassword changePassword) {
        return apiRoot.customers().password().post(changePassword).executeBlocking().getBody();
    }

    public CustomerSignInResult signin(CustomerSignin customerSignin) {
        return apiRoot.login().post(customerSignin).executeBlocking().getBody();
    }


    public Customer updateCustomer(CustomerData customerData,CustomerUpdate customerUpdate) {
        return apiRoot.customers().withId(customerData.getId())
                .post(customerUpdate).executeBlocking().getBody();
    }

    public Customer updateCustomerByIdArvind(CustomerUpdate customerUpdate, Customer customerToUpdate) {
        return apiRoot.customers().withId(customerToUpdate.getId())
                .post(customerUpdate).executeBlocking().getBody();
    }

    public Customer customerById(String id) {
       return apiRoot.customers().withId(id).get().executeBlocking().getBody();

    }

    String dsccode(TestData testData) {
        DiscountCodeDraft discountCodeDraft =DiscountCodeDraft.builder()
                .name(LocalizedString.ofEnglish(testData.getName()))
                .description(LocalizedString.ofEnglish(testData.getDesc()))
                .code(testData.getCode())
                .isActive(testData.getIsActive())
                .cartDiscounts(CartDiscountResourceIdentifier.builder().key(testData.getDescKey()).build())
//                .maxApplicationsPerCustomer()
//                .maxApplications()
                .build();
        apiRoot.discountCodes().post(discountCodeDraft).executeBlocking().getBody();
        return "this is service";
    }







  /*  public Customer getCustomerById(String id){
        return apiRoot.customers().withId(id).get().executeBlocking().getBody();
    }*/
}
