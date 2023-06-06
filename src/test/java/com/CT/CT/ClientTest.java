package com.CT.CT;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.project.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    @BeforeEach
    void createApiClient() {
        ProjectApiRoot projectApiRoot = Client.createApiClient();
        Project project = projectApiRoot.get().executeBlocking().getBody();
        assertEquals("aditya",project.getKey());
    }
}