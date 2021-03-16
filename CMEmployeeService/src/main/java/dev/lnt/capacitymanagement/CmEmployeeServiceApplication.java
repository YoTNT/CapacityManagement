package dev.lnt.capacitymanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CmEmployeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmEmployeeServiceApplication.class, args);
	}

}
