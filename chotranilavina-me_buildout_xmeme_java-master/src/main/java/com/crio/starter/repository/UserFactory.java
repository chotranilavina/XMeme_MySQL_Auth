package com.crio.starter.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.jdbc.*;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

@Component
@Configuration
@EnableJpaRepositories
public class UserFactory {

private DataSource dataSource;

public UserFactory() {
        super();
}

@Bean
@ConfigurationProperties(prefix="spring.datasource")
public DataSource dataSource() {
        if(this.dataSource == null)
                return this.dataSource = DataSourceBuilder.create().build();
        else
                return this.dataSource;
}
}
