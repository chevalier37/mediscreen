package com.mediscreen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.mediscreen")
public class MircoServicePatientApplication {

	public static void main(String[] args) {
		SpringApplication.run(MircoServicePatientApplication.class, args);
	}

	@Bean
	public LocalValidatorFactoryBean localValidatorFactoryBean() {
		return new LocalValidatorFactoryBean();
	}

	@Bean
	public ValidatingMongoEventListener validatingMongoEventListener(LocalValidatorFactoryBean lfb) {
		return new ValidatingMongoEventListener(lfb);
	}

}
