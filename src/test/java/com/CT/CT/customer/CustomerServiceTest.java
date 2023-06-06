package com.CT.CT.customer;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.customer.Customer;
import com.commercetools.api.models.customer.CustomerBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {CustomerService.class})
@ExtendWith(SpringExtension.class)
class CustomerServiceTest {


        //@Autowired
        @InjectMocks
        CustomerService customerService;
        @Mock
        CustomerDataProvider cdp;
//        @Mock
//        CustomerData data;


        @Test
        void testCreateCustomer() {

            Customer customer = CustomerBuilder.of()
                    .email("aditya.234@gmail.com")
                    .firstName("aditya")
                    .lastName("kumar")
                    .buildUnchecked();
            Mockito.when(cdp.createCustomer(any())).thenReturn(customer);


            CustomerData data = new CustomerData();
            data.setFirstName(customer.getFirstName());
            data.setEmail(customer.getEmail());


            Customer actualCustomer = customerService.createCustomer(data);
         //   Assertions.assertEquals("aditya.234@gmail.com",serviceCustomer.getEmail());
         //   Assertions.assertEquals("aditya",serviceCustomer.getFirstName());

           Assertions.assertEquals( customer.getFirstName(),actualCustomer.getFirstName());


        }
}