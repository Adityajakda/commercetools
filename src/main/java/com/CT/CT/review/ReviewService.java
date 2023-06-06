package com.CT.CT.review;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.review.Review;
import com.commercetools.api.models.review.ReviewUpdate;
import com.commercetools.api.models.state.StateResourceIdentifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    RevewDataProvider rdp =new RevewDataProvider();
    @Autowired
    ProjectApiRoot apiRoot;
    public Review ratingByKey(String key) {
      return rdp.ratingByKey(key);
    }

    public Review reviewpublish(String key) {
        Review review=ratingByKey(key);
      ReviewUpdate update= ReviewUpdate.builder()
                        .version(review.getVersion())
                        .plusActions(actionbuilder -> actionbuilder
                                .transitionStateBuilder().state(StateResourceIdentifier.builder()
                                        .key("approved").build())
                        ).build();
          return  rdp.reviewUpdate(key,update);

//      Review update=  apiRoot
//                .reviews()
//                .withKey(review.getKey())
//                .post(ReviewUpdate.builder()
//                        .version(review.getVersion())
//                        .plusActions(actionbuilder -> actionbuilder
//                                .transitionStateBuilder().state(StateResourceIdentifier.builder()
//                                        .key("approved").build())
//                        ).build())
//                .executeBlocking().getBody();

    }
}
