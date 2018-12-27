package com.bootcamp.dap.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.bootcamp.dao.AccountDao;
import com.bootcamp.dao.CustomerDao;
import com.bootcamp.dao.TransactionDao;
import com.bootcamp.dao.impl.AccountDaoImpl;
import com.bootcamp.dao.impl.CustomerDaoImpl;
import com.bootcamp.dao.impl.TransactionDaoImpl;

@Configuration
public class DaoSpringConfig {

	@Bean
	public CustomerDao customerdao() {
		return new CustomerDaoImpl();
	}

	@Bean
	public AccountDao accountdao() {
		return new AccountDaoImpl();
	}

	@Bean
	public TransactionDao transactiondao() {
		return new TransactionDaoImpl();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins("*")
						.allowedHeaders("*");
			}
		};
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
