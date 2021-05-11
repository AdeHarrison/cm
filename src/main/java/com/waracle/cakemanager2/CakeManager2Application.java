package com.waracle.cakemanager2;

import com.google.common.collect.Lists;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.swagger.web.ApiKeyVehicle.HEADER;

@SpringBootApplication
@EnableSwagger2
public class CakeManager2Application extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(CakeManager2Application.class, args);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .oauth2Login()
                .and()
                .oauth2ResourceServer().jwt();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).securitySchemes(Lists.newArrayList(apiKey()));
    }

    @Bean
    public SecurityConfiguration securityInfo() {
        return new SecurityConfiguration(null, null, null, null, "", HEADER, "Authorization", "");
    }

    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }
}
