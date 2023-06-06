package com.CT.CT.customer;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.cart_discount.CartDiscountDraft;
import com.commercetools.api.models.cart_discount.CartDiscountTargetBuilder;
import com.commercetools.api.models.cart_discount.CartDiscountValueDraft;
import com.commercetools.api.models.common.LocalizedString;
import com.commercetools.api.models.customer.Customer;
import com.commercetools.api.models.customer.CustomerPagedQueryResponse;
import com.commercetools.api.models.customer.CustomerSignInResult;
import com.commercetools.api.models.customer.CustomerToken;
import com.commercetools.api.models.graph_ql.GraphQLRequest;
import com.commercetools.api.models.graph_ql.GraphQLResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.ZonedDateTime;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    ProjectApiRoot projectApiRoot;
    @Autowired
    CustomerDataProvider cdp;
    @Autowired
    CustomerService customerService;

    CustomerData customerData=new CustomerData();

 /*   @PostMapping("/create")
        public Customer createCustomer(@RequestBody CustomerData customerData) {
            CustomerDraft newCustomerDetails = CustomerDraft
                    .builder()
                    .firstName("Amit")
                    .lastName("kumar")
                    .email(customerData.Email)
                    .password(customerData.password)
    //                .companyName("hybs")
    //                .externalId("88")
    //                .customerNumber("7")
                    .build();

            return cdp.createCustomer(newCustomerDetails);
        }*/



    @PostMapping
    public Customer customer(@RequestBody CustomerData customerData){
//        return projectApiRoot.customers().post(customerDraft).executeBlocking().getBody().getCustomer();
        return customerService.createCustomer(customerData);
    }
    @PostMapping("/type")
    public Type types(@RequestBody CustomerData customerData)
    {
        return customerService.createType(customerData);
    }
/*
    @DeleteMapping("/deletetype/{id}")
    public Type deleteType(@PathVariable String id){
        return customerService.deleteType(id);
    }
*/
    @GetMapping("/limit")
    CustomerPagedQueryResponse customerPagedQuerywithlimit(@RequestParam(required = true, defaultValue = "25") int limits){
        return projectApiRoot.customers().get().withLimit(limits).executeBlocking().getBody();
    }
    @GetMapping
    CustomerPagedQueryResponse customerPage(){
        return projectApiRoot.customers().get().executeBlocking().getBody();
    }

    @GetMapping("/{id}")
    Customer customerById(@PathVariable String id){
        return customerService.customerById(id);
//        return projectApiRoot.customers().withId(id).get().executeBlocking().getBody();
    }
    @DeleteMapping("/{id}")
    Customer customerdeleteById(@PathVariable String id)
    {
        return projectApiRoot.customers().withId(id).delete().addVersion(1).executeBlocking().getBody();
    }

    @PostMapping("/update")
    Customer customers(@RequestBody CustomerData customerData)  // for id and version
    {
        return customerService.updateCustomer(customerData);
    }
    @PostMapping("/updates")
    Customer customerss(@RequestParam String id,@RequestBody CustomerData customerData)  // for id and version
    {
        return customerService.updateCustomerByIdByArvind(id,customerData);
    }


    @PostMapping("/update/{id}")
    Customer customer(@PathVariable String id,@RequestBody CustomerData customerData)
    {
        return customerService.updateCustomerById(customerData,projectApiRoot);

//        return projectApiRoot.customers().withId(id).post(customerUpdate).executeBlocking().getBody();
    }


    @PostMapping("/tok")
        CustomerToken token(@RequestBody CustomerData customerData){

        return customerService.customerToken(customerData);
    }

@PostMapping("/res")
    Customer resetPassword(@RequestBody CustomerData customerData){
  return customerService.resetPassword(customerData);
    }

    @PostMapping("/change")
    Customer changePassword(@RequestBody CustomerData customerData){
        return customerService.changePassword(customerData);
    }


    @PostMapping("/login")
     CustomerSignInResult customerSignin(@RequestBody CustomerData customerData){
        CustomerSignInResult customer =customerService.customerLogin(customerData);
        return customer;
    }
//    public void customerSignin(@RequestBody CustomerData customerData){
//        CustomerSignInResult customer=customerService.customerLogin(customerData);
//        System.out.println(customer.getCustomer().getEmail());
//        System.out.println(customer.getCustomer().getFirstName());

    @GetMapping("/filter")
    CustomerPagedQueryResponse customerPagedQueryResponse(@RequestParam String where)
    {
        return projectApiRoot.customers().get().withWhere(where).executeBlocking().getBody();
    }

    @GetMapping("/me")
    public Customer me(){
        return projectApiRoot.me().get().executeBlocking().getBody();
    }
//    @GetMapping("/customers-graph")
//    GraphQLResponse CustomerGraph(){
//        return projectApiRoot.graphql().post(GraphQLRequest.builder().query("query customers {\n" +
//                " customers{\n" +
//                "  results{\n" +
//                "    email\n" +
//                "  }\n" +
//                "}\n" +
//                "}").build()).executeBlocking().getBody();
//    }
@GetMapping("/customers-graph")
GraphQLResponse CustomerGraph(){
    return projectApiRoot.graphql().post(GraphQLRequest.builder().query("query customers {\n" +
            " customers{\n" +
            "  results{\n" +
            "    email\n" +
            "  }\n" +
            "}\n" +
            "}").build()).executeBlocking().getBody();
}
@PostMapping("/carttest")
    String discountCode(){
        CartDiscountDraft cartDiscount= CartDiscountDraft.builder()
//                .name(LocalizedString.ofEnglish("name of discount"))
//                .description(LocalizedString.ofEnglish("desc of discount"))
//                .key("dic-key")
//                .sortOrder("o.432")
//                .value(CartDiscountValue.relativeBuilder().permyriad(1l).build())
//                .isActive(true)
//                .requiresDiscountCode(false)
//                .cartPredicate("1=1")



//                .name(LocalizedString.builder().addValue("DE-DE","cartdisc01").build())
                .name(LocalizedString.ofEnglish("cartdisco1"))
                .key("cartdisc01")

                .description(LocalizedString.ofEnglish("cartdesc of disc 01"))
//                .description(LocalizedString.builder().addValue("DE-DE","cartdesc of disc 01").build())
//                .value(CartDiscountValueDraftBuilder.of().relativeBuilder().permyriad(1500L).build())
                .value(CartDiscountValueDraft.relativeBuilder().permyriad(200L).build())
                .cartPredicate("1=1")
                .target(CartDiscountTargetBuilder.of().lineItemsBuilder().predicate("1=1").build())
                .sortOrder("0.087")
                .isActive(true)
                .validFrom(ZonedDateTime.now())
                .validUntil(ZonedDateTime.now().plusDays(10))
                .requiresDiscountCode(true)


                .build();
        projectApiRoot.cartDiscounts().post(cartDiscount).executeBlocking().getBody();
        return "cart created";
    }

    @PostMapping("/dsccode")
    String dsccode(@RequestBody TestData testData){
        cdp.dsccode(testData);
        return "hello aditya";
    }


    @GetMapping("/keyupdate")
    GraphQLResponse customerkey(){
//        projectApiRoot.customers().get().executeBlocking().getBody();

//return projectApiRoot.products().get().withLimit(1).executeBlocking().getBody();
       return projectApiRoot.graphql().post(GraphQLRequest.builder().query("query customers {\n" +
                " products{\n" +
                "  results{\n" +
                "    id\n" +
               " version\n"+
               "key\n"+
               "productType{\n"+
                                 "id\n"+
                                 "key\n"+
                            "  }\n" +
               "masterData{\n"+
                         "current{\n"+
                               "categories{\n"+
                                             "id\n"+
                                             "key\n"+
                                        "  }\n" +
                                "masterVariant{\n"+
                                            "key\n"+
                                    "prices{\n"+
                                        "id\n"+
                                        "key\n"+
                                    "  }\n" +
                                "  }\n" +
               "variants{\n"+
               "id\n"+
               "key\n"+
               "  }\n" +
                            "  }\n" +
                      "  }\n" +
               "  }\n" +
                "}\n" +
                "}").build()).executeBlocking().getBody();

//        return   projectApiRoot.graphql().post(GraphQLRequest.builder().query("query customers {\n" +
//                " customers{\n" +
//                "  results{\n" +
//                "    id\n" +" version\n"+"key\n"+"firstName\n"+
//                "  }\n" +
//                "}\n" +
//                "}").build()).executeBlocking().getBody();
    }
}
