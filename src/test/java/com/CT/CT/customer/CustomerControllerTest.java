package com.CT.CT.customer;

import com.commercetools.api.client.ByProjectKeyCustomersGet;
import com.commercetools.api.client.ByProjectKeyCustomersRequestBuilder;
import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.cart.CartPagedQueryResponse;
import com.commercetools.api.models.customer.Customer;
import com.commercetools.api.models.customer.CustomerBuilder;
import com.commercetools.api.models.customer.CustomerPagedQueryResponse;
import com.commercetools.api.models.customer.CustomerPagedQueryResponseBuilder;
import io.vrap.rmf.base.client.ApiHttpResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.webservices.server.AutoConfigureMockWebServiceClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

//@ContextConfiguration(classes = {CustomerController.class})
@ExtendWith(SpringExtension.class)
class CustomerControllerTest {
    @InjectMocks
    CustomerController ccor;
    @Mock
    CustomerService customerService;
    @Mock
    ProjectApiRoot apiRoot;

    //    @Mock
//    Customer customer;
    @Test
    void customer() {
        Customer customer = CustomerBuilder.of()
                .email("aditya.234@gmail.com")
                .firstName("aditya")
                .password("1234")
                        .buildUnchecked();
        Mockito.when(customerService.createCustomer(any())).thenReturn(customer);



        CustomerData data = new CustomerData();

//        data.setFirstName(customer.getFirstName());
//        data.setEmail(customer.getEmail());
//        data.setPassword(customer.getPassword());

        System.out.println(data.getEmail());
        System.out.println(data.getFirstName());

        Customer customer1=  ccor.customer(data);
        System.out.println(customer1.getFirstName());
        System.out.println(customer1.getEmail());
        System.out.println(customer1.getLastName());
        System.out.println(customer1.getPassword());

        assertEquals(customer1.getEmail(), customer.getEmail());
    }

    @Test
    void customerPagedQuerywithlimit() {

        CustomerPagedQueryResponse customer = CustomerPagedQueryResponseBuilder.of().limit(21L).buildUnchecked();


        ByProjectKeyCustomersRequestBuilder requestBuilder =Mockito.mock(ByProjectKeyCustomersRequestBuilder.class);
        Mockito.when(apiRoot.customers()).thenReturn(requestBuilder);

        ByProjectKeyCustomersGet customersGet=Mockito.mock(ByProjectKeyCustomersGet.class);
        Mockito.when(requestBuilder.get()).thenReturn(customersGet);
        Mockito.when(customersGet.withLimit(21)).thenReturn(customersGet);

        ApiHttpResponse<CustomerPagedQueryResponse> apiHttpResponse =Mockito.mock(ApiHttpResponse.class);
        Mockito.when(customersGet.executeBlocking()).thenReturn(apiHttpResponse);

        Mockito.when(apiHttpResponse.getBody()).thenReturn(customer);

        CustomerPagedQueryResponse c = ccor.customerPagedQuerywithlimit(21);
        assertEquals(21, c.getLimit());
    }

    @Test
    void customerPage() {

        CustomerPagedQueryResponse customer = CustomerPagedQueryResponse.builder()
                .results(
                        Customer.builder().id("23456").firstName("aditya").buildUnchecked()
                )
                .buildUnchecked();

        ByProjectKeyCustomersRequestBuilder requestBuilder =Mockito.mock(ByProjectKeyCustomersRequestBuilder.class);
        Mockito.when(apiRoot.customers()).thenReturn(requestBuilder);

        ByProjectKeyCustomersGet customersGet=Mockito.mock(ByProjectKeyCustomersGet.class);
        Mockito.when(requestBuilder.get()).thenReturn(customersGet);

        ApiHttpResponse<CustomerPagedQueryResponse> apiHttpResponse =Mockito.mock(ApiHttpResponse.class);
        Mockito.when(customersGet.executeBlocking()).thenReturn(apiHttpResponse);

//        CustomerPagedQueryResponse customerPagedQueryResponse = Mockito.mock(CustomerPagedQueryResponse.class);
        Mockito.when(apiHttpResponse.getBody()).thenReturn(customer);

        CustomerPagedQueryResponse c = ccor.customerPage();
        assertEquals(customer,c);
    }

    @Test
    void customerById() {
        Customer customer=CustomerBuilder.of().id("12345678").buildUnchecked();
        Mockito.when(customerService.customerById(any())).thenReturn(customer);

        Customer acuCustomer = ccor.customerById("12345678");
        assertEquals(acuCustomer.getId(), customer.getId());

    }


//    @Test
//    void customerByIdstatus() {
//        HttpResponse<Customer> response = ccor.customerById("77fa708c-dfe8-4385-ba31-4a988d11dc19");
//        assertEquals(200, response.statusCode());
//        Customer customer = response.body();
//        assertEquals("77fa708c-dfe8-4385-ba31-4a988d11dc19", customer.getId());
//    }

    @Test
    void customerdeleteById() {
    }

    @Test
    void testCustomer() {
    }
}