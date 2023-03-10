package com.vup.tess.config.datasource;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.zaxxer.hikari.HikariDataSource;


@Configuration
public class DataSourceConfig {
	
	public static final String MASTER_DATASOURCE = "masterDataSource";
    public static final String SLAVE_DATASOURCE = "slaveDataSource";	
	
	
    @Primary
    @Bean(MASTER_DATASOURCE)
    @ConfigurationProperties(prefix="spring.datasource.master.hikari")
    public DataSource masterDataSource() {
    	
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(SLAVE_DATASOURCE)
    @ConfigurationProperties(prefix="spring.datasource.slave.hikari")
    public DataSource slaveDataSource() {
    	
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }
}
