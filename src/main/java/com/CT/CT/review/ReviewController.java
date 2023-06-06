package com.CT.CT.review;

import com.CT.CT.Client;
import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.product.ProductPagedQueryResponse;
import com.commercetools.api.models.product.ProductResourceIdentifier;
import com.commercetools.api.models.review.Review;
import com.commercetools.api.models.review.ReviewDraft;
import com.commercetools.api.models.review.ReviewPagedQueryResponse;
import com.commercetools.api.models.review.ReviewUpdate;
import com.commercetools.api.models.state.StateResourceIdentifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewController {

//    ProjectApiRoot apiRoot = Client.createApiClient();
    @Autowired
    ProjectApiRoot apiRoot;
    @Autowired
    ReviewService reviewService;

    @PostMapping("/review")
    public Review review() {
        Review review = apiRoot
                .reviews()
                .post(
                        ReviewDraft
                                .builder()
                                .authorName("Aditya")
                                .title("Incredible")
                                .text("Best product ever")
                                .key("review-1")
                                .rating(4)
                                .state(StateResourceIdentifier.builder().key("to-approve").build())
                                .target(ProductResourceIdentifier.builder().id("9da8f033-a849-425c-ae3b-9dbdbe1e45c9").build())
                                .build()
                )
                .executeBlocking()
                .getBody();
    return review;
    }
    @GetMapping("/review")
    public ReviewPagedQueryResponse reviewPagedQueryResponse() {
     ReviewPagedQueryResponse reviewPagedQueryResponse = apiRoot
            .reviews()
            .get()
            .withWhere("state(id in (\"864764ca-b9c3-4c72-a65a-1df0867716b3\"))")
            .executeBlocking()
            .getBody();
    return reviewPagedQueryResponse;
    }

    @GetMapping("/review/all")
    public ReviewPagedQueryResponse reviewPagedQueryResponseall(){
       ReviewPagedQueryResponse s= apiRoot.reviews().get().executeBlocking().getBody();
       return s;
    }

    @GetMapping("/review/{key}")
    public Review review(@PathVariable String key){

        return reviewService.ratingByKey(key);
    }

    @PostMapping("/review/update/{key}")
    public Review reviewUpdate(@PathVariable String key){
       return reviewService.reviewpublish(key);
    }




}
