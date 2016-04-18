package com.frontback.crl.app;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.frontback.crl.webfilter.RESTFilter;

@Configuration
@EnableJpaRepositories(basePackages = "br.com.bry.fw.servidor.repositorios")
@EntityScan(basePackages = { "br.com.bry.fw.servidor", "com.university.model" })
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

}