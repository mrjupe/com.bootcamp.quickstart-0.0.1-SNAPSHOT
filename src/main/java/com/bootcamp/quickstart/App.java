package com.bootcamp.quickstart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.bootcamp.dap.config.DaoSpringConfig;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@EntityScan({"com.bootcamp.model"})
@EnableJpaRepositories({"com.bootcamp.dao.repository"})
@Import(DaoSpringConfig.class)
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
