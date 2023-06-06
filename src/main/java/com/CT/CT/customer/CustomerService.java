package com.CT.CT.customer;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.common.LocalizedString;
import com.commercetools.api.models.customer.*;
import com.commercetools.api.models.type.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class CustomerService {
//    @Autowired
//    CustomerData customerData;
    @Autowired
    CustomerDataProvider cdp;

    public CustomerDataProvider getCdp() {
        return cdp;
    }

    public void setCdp(CustomerDataProvider cdp) {
        this.cdp = cdp;
    }
/*public Customer createCustomer(CustomerData customerData){
    CustomerDraft customerDraft =new CustomerDraftImpl();

    customerDraft.setEmail(customerData.getEmail());
    customerDraft.setPassword(customerData.getPassword());

    return cdp.createCustomer(customerDraft);

  }*/

//    public Customer customer(CustomerData customerData){
//        CustomerDraft customerDraft = CustomerDraft.builder().
//    }
    public  Customer createCustomer(CustomerData customerData) {
        CustomerDraft customerDraft = CustomerDraft
                .builder()
                .email(customerData.getEmail())
                .password(customerData.getPassword())
                .firstName(customerData.getFirstName())
//                .custom(customFieldsDraftBuilder -> customFieldsDraftBuilder
//                        .type(typeResourceIdentifierBuilder -> typeResourceIdentifierBuilder.key("adcustomer-choice"))
//                        .fields(fieldContainerBuilder -> fieldContainerBuilder.addValue("choice",customerData.getValue())))
                .build();
        return getCdp().createCustomer(customerDraft);
//        return cdp.createCustomer(customerDraft);
    }


    // vasishali cosde not work (key)

//    CustomerDraft customerDraft = CustomerDraft.builder().email(info.getEmail()).firstName(info.getFirstName())
//    .lastName(info.getLastName()).password(info.getPassword())
//    .custom(customFieldsDraftBuilder -> customFieldsDraftBuilder
//    .type(typeResourceIdentifierBuilder -> typeResourceIdentifierBuilder.key("customer-ext"))
//    .fields(fieldContainerBuilder -> fieldContainerBuilder.addValue("emailP",info.isEmailP()))).build();
//


    public Type createType(CustomerData customerData){
        FieldDefinition fieldDefinition = FieldDefinition
                .builder()
                .type(FieldType.stringBuilder().build())
                .name("newType03")
                .inputHint(TypeTextInputHint.SINGLE_LINE)
                .label(
//                        LocalizedString.builder().addValue("en", "").build()
                        LocalizedString.ofEnglish("this is label text"))
                .required(false)
                .build();

        TypeDraft typeDraft =TypeDraft.builder()
                .key("new_Type_01")
                .name(
//                   LocalizedString.builder()
//                     .addValue("en", "Additional field to store preferred shoe size").build()   //when language is LocalizedString json ex. ("en-us":"name")
                        LocalizedString.ofEnglish("custom Type name")
                )

//                .description()
                .resourceTypeIds(List.of(ResourceTypeId.CUSTOMER))
                .fieldDefinitions(fieldDefinition)
                .build();
        return cdp.createCostomType(typeDraft);
    }
    public Customer updateCustomer(CustomerData customerData)
    {
        CustomerUpdate customerUpdate1 = CustomerUpdate
                .builder()
                .version(customerData.getVersion())
                .plusActions(customerUpdateActionBuilder ->
                        customerUpdateActionBuilder.changeEmailBuilder().email(customerData.getEmail()))
//                .plusActions(actionBuilder ->
//                        actionBuilder.setFirstNameBuilder().firstName(customerData.getFirstName())
//                )
//                .plusActions(actionBuilder ->
//                        actionBuilder.setLastNameBuilder().lastName(customerData.getLastName())
//                )
                .build();
        return cdp.updateCustomer(customerData,customerUpdate1);
    }
    public Customer  updateCustomerByIdByArvind(String id, CustomerData customerData) {
        Customer customerToUpdate = customerById(id);
        Long version = customerToUpdate.getVersion();
        CustomerUpdate customerUpdate = CustomerUpdateBuilder.of()
                .version(version).plusActions(customerUpdateActionBuilder -> customerUpdateActionBuilder
                        .setFirstNameBuilder().firstName(customerToUpdate.getFirstName() == null ? customerData.getFirstName() : customerToUpdate.getFirstName())).build();
        return cdp.updateCustomerByIdArvind(customerUpdate, customerToUpdate);
    }
    public Customer updateCustomerById(CustomerData customerData, ProjectApiRoot apiRoot) {
        CustomerUpdate updateActions = CustomerUpdate
                .builder()
                .version(customerData.getVersion())
                .plusActions(actionBuilder ->
                        actionBuilder
                                .setCustomTypeBuilder()
                                .type(builder -> builder.id("cc0afa8d-ce4d-4c35-b74b-828bbe28f4be"))
                                .fields(fieldContainerBuilder -> fieldContainerBuilder
                                        .addValue("email-preference" , customerData.getEmailPref())))

//                .plusActions(customerUpdateActionBuilder ->
//                        customerUpdateActionBuilder
//                                .changeEmailBuilder().email(customerData.getEmail()))

                .build();

// Update the Customer
        Customer customerWithCustomType = apiRoot
                .customers()
                .withId("3b310f52-9477-42e1-a466-61cd992ecbab")
                .post(updateActions)
                .executeBlocking()
                .getBody();
        return customerWithCustomType;
    }

    public CustomerToken customerToken(CustomerData customerData) {
        CustomerCreatePasswordResetToken createPasswordResetToken= CustomerCreatePasswordResetToken
                .builder().email(customerData.getEmail()).build();
        return cdp.customeToken(createPasswordResetToken);
    }

    public Customer resetPassword(CustomerData customerData) {
        CustomerResetPassword customerResetPassword= CustomerResetPassword.builder()
                .tokenValue(customerData.getTokenValue())
                .newPassword(customerData.getNewPassword())
                .build();
        return cdp.resetPassword(customerResetPassword);
    }

    public Customer changePassword(CustomerData customerData) {
        CustomerChangePassword changePassword= CustomerChangePassword.builder()
                .newPassword(customerData.getNewPassword())
                .currentPassword(customerData.getCurrentPassword())
                .version(customerData.getVersion())
                .id(customerData.getId())
                .build();
        return cdp.changePassword(changePassword);
    }

    public CustomerSignInResult customerLogin(CustomerData customerData) {
        CustomerSignin customerSignin = CustomerSignin
                .builder()
                .email(customerData.getEmail())
                .password(customerData.getPassword())
                .anonymousId("12345")
                .anonymousCartSignInMode(AnonymousCartSignInMode.MERGE_WITH_EXISTING_CUSTOMER_CART)
                .build();
        return cdp.signin(customerSignin);

    }

    public Customer customerById(String id) {
        return cdp.customerById(id);
    }


   /* public Customer getCustomer(CustomerData customerData) {
        return cdp.getCustoemrWithEmailPass(customerData);
    }*/

 /*   public Customer getCustomerById(String id) {
        return cdp.getCustomerById(id);
    }*/

}




