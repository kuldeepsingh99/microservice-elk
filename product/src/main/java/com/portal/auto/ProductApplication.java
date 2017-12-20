package com.portal.auto;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class ProductApplication 
{
protected Logger logger = Logger.getLogger(ProductApplication.class.getName());
	
	public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

		
	@RequestMapping("/getproductdetails")
    public String getproductdetails() {
		logger.info(" inside getproductdetails  Micro service");
		return "Hello product";
    }
}
