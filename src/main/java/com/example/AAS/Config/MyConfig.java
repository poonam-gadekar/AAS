package com.example.AAS.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();

	}

}
