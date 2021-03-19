package dev.lnt.capacitymanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan("dev.lnt")
public class CmGatewayServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CmGatewayServiceApplication.class, args);
	}

}
