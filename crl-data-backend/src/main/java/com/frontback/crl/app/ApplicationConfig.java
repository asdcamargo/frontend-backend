package com.frontback.crl.app;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.frontback.crl.webfilter.RESTFilter;
import com.frontback.crl.webfilter.SimpleCORSFilter;

@Configuration
@EnableJpaRepositories(basePackages = "com.frontback.crl.repositories")
@EntityScan(basePackages = { "com.frontback.crl.model" })
@ComponentScan(basePackages = { "com.frontback.crl.service" })
class ApplicationConfig {

	@Bean
	public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		ppc.setLocations(new ClassPathResource("/application.properties"));
		return ppc;
	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(10240);
		factory.setMaxRequestSize(10240);
		return factory.createMultipartConfig();
	}

	@Bean
	public FilterRegistrationBean restFilter() {
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
		filterRegistration.setFilter(new RESTFilter());
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/rest/*");
		filterRegistration.setUrlPatterns(urlPatterns);
		return filterRegistration;
	}

	@Bean
	public FilterRegistrationBean corsFilter() {
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
		filterRegistration.setFilter(new SimpleCORSFilter());
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/rest/*");
		filterRegistration.setUrlPatterns(urlPatterns);
		return filterRegistration;
	}

}