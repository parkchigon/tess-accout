package com.vup.tess.config.replication;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import com.vup.tess.config.GlobalConfig.DataSourceType;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class RoutingDataSourceConfig {

	public static final String MASTER_DATASOURCE = "masterDataSource";
	public static final String SLAVE_DATASOURCE = "slaveDataSource";

	@Bean(name = "routingDataSource")
	@DependsOn({ MASTER_DATASOURCE, SLAVE_DATASOURCE })
	public DataSource routingDataSource(
			@Qualifier(MASTER_DATASOURCE) final DataSource masterDataSource,
			@Qualifier(SLAVE_DATASOURCE) final DataSource slaveDataSource) {

		ReplicationRoutingDataSource routingDataSource = new ReplicationRoutingDataSource();
		
		Map<Object, Object> dataSourceMap = new HashMap<>();

		dataSourceMap.put(DataSourceType.Master, masterDataSource);
		dataSourceMap.put(DataSourceType.Slave, slaveDataSource);

		routingDataSource.setTargetDataSources(dataSourceMap);
		routingDataSource.setDefaultTargetDataSource(masterDataSource);

		return routingDataSource;
	}

	@Bean(name = "dataSource")
	public DataSource dataSource(@Qualifier("routingDataSource") DataSource routingDataSource) {
		return new LazyConnectionDataSourceProxy(routingDataSource);
	}
}
