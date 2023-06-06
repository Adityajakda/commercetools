package com.CT.CT;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.project.Project;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableDiscoveryClient
public class CtApplication {

	public static void main(String[] args) {
		SpringApplication.run(CtApplication.class, args);
	}

}
