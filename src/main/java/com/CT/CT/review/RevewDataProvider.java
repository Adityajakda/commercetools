package com.CT.CT.review;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.review.Review;
import com.commercetools.api.models.review.ReviewUpdate;
import org.springframework.beans.factory.annotation.Autowired;

public class RevewDataProvider {

    @Autowired
    ProjectApiRoot apiRoot;

    public Review ratingByKey(String key) {
        return apiRoot.reviews().withKey(key).get().executeBlocking().getBody();
    }

    public Review reviewUpdate(String key, ReviewUpdate update) {
        return apiRoot.reviews().withKey(key).post(update).executeBlocking().getBody();
    }
}
