package com.anonymousCustomer.me;

import com.anonymousCustomer.Exception.ApiException;
import com.anonymousCustomer.Exception.ApiRequestException;
import com.commercetools.api.models.customer.Customer;
import com.commercetools.api.models.customer.CustomerEmailVerify;
import com.commercetools.api.models.customer.CustomerSignInResult;
import com.commercetools.api.models.customer.CustomerToken;
import io.vrap.rmf.base.client.ApiHttpException;
import io.vrap.rmf.base.client.AuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/me")
@CrossOrigin
public class MyController {
    @Autowired
    LoginResponce responce;
    @Autowired
    MyService myService;
//    @PostMapping("/login")   // sign in
//    public ResponseEntity<LoginResponce> login(@RequestBody MyData myData, @RequestHeader("Authorization") String authHeader){
////        this endpoint is use for add cart to system user
////        it's not for login in ct system
//
//    LoginResponce res=myService.login(myData,authHeader);
//    return ResponseEntity.ok(res);
//    }

    @PostMapping("/email")
    public CustomerToken emailChek(@RequestBody MyData myData,@RequestHeader("Authorization") String authHeader){
        CustomerToken token=myService.emailChek(myData,authHeader);
        return token;
    }


    @PostMapping("/login")   // sign in
    public LoginResponce login(@RequestBody MyData myData, @RequestHeader("Authorization") String authHeader){
//        this endpoint is use for add cart to system user
//        it's not for login in ct system

//         myService.login(myData,authHeader);
         try {
             myService.login(myData,authHeader);
         }
         catch (ApiHttpException e){
            String input = e.getBody();
             String trimmedInput = input.substring(1, input.length() - 1);
             String[] substrings = trimmedInput.split(",");
             String lastSubstring = substrings[substrings.length - 1].trim();

             System.out.println(lastSubstring);
             throw new ApiRequestException(lastSubstring);
         }
         return responce;
    }

    @PostMapping("/signup")   // sign up
    public LoginResponce signUp(@RequestBody MyData myData,@RequestHeader("Authorization") String authHeader){
//        for creating new user in ct system

//        myService.signUp(myData,authHeader);
        try {
            CustomerSignInResult ad= myService.signUp(myData,authHeader);
//            if(ad)
        }
        catch (ApiHttpException e){
            String input = e.getBody();
            String trimmedInput = input.substring(1, input.length() - 1);
            String[] substrings = trimmedInput.split(",");
                String valueAtIndex2 = substrings[1].trim();
            throw new ApiRequestException(valueAtIndex2);
//            throw new ApiRequestException(input);
        }
        return responce;
    }

    @GetMapping
    public Customer myCustomer(@RequestHeader("Authorization") String authHeader){
//        this endpoint is for logedin user
//        to process this request requird a token form logedin user process          < client3 >
        return myService.myCustomer(authHeader);
    }

    @DeleteMapping
    public Customer deleteCustomer(@RequestHeader("Authorization") String authHeader){
        return myService.deleteCustomer(authHeader);
    }

//                              email verify token ??

//    @PostMapping
//    public String token(){
//        return myService.token();
//    }

//    @PostMapping("/email")
//    public Customer emailVerify(@RequestHeader("Authorization") String authHeader,@RequestBody MyData myData){
//        return myService.emailVerify(authHeader,myData);
//    }




}
