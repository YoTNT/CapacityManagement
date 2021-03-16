package dev.lnt.capacitymanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CMDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CMDiscoveryServiceApplication.class, args);
	}

}
