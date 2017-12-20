package com.portal.auto;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class AccountApplication {

	protected Logger logger = Logger.getLogger(AccountApplication.class.getName());
	
	public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

	@Value("${apigateway.path}")
	private String apiGatewayUrl;
	
	@Autowired
    RestTemplate restTemplate;
 
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
		
	@RequestMapping("/greeting")
    public String greeting() {
		
		/* Calling the product micro servivce */
		String response = (String) restTemplate.exchange(apiGatewayUrl+"/api/product/getproductdetails",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}).getBody();
		
		logger.info("Product API Reponse ::"+response);

		return "Hello from Account Service!"+System.getProperty("server.port");
    }
}
