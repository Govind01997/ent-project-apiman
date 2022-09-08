package com.entando.apiman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.entando.apiman.k8sserviceimpl.EntServiceDetailImpl;

@EnableScheduling
@EnableWebMvc
@SpringBootApplication
public class EntapimanApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntapimanApplication.class, args);
		
		
		
	}

}
