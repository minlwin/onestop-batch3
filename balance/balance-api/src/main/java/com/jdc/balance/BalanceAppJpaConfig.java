package com.jdc.balance;

import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.jdc.balance.model.repo")
@ComponentScan(basePackages = {
		"com.jdc.balance.model.service",
		"com.jdc.balance.model.utils"
})
@PropertySource("classpath:/database.properties")
public class BalanceAppJpaConfig {

	@Value("${app.db.url}") private String url;
	@Value("${app.db.user}") private String user;
	@Value("${app.db.password}") private String password;

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	DataSource dataSource() {
		var bean = new BasicDataSource();
		bean.setUrl(url);
		bean.setUsername(user);
		bean.setPassword(password);
		return bean;
	}
	
	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		var bean = new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(dataSource);
		bean.setPackagesToScan("com.jdc.balance.model.entity");
		bean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		bean.setJpaPropertyMap(Map.of(
			"hibernate.hbm2ddl.auto", "create",
			"hibernate.show_sql", "true",
			"hibernate.format_sql", "true"
		));
		return bean;
	}
	
	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
	
	@Bean
	LocalValidatorFactoryBean localValidatorFactoryBean() {
		return new LocalValidatorFactoryBean();
	}
	
	
}
