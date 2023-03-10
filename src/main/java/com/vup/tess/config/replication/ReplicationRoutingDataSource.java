package com.vup.tess.config.replication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.vup.tess.config.GlobalConfig.DataSourceType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Override
    protected Object determineCurrentLookupKey() {
    	
    	System.out.println("@@ TransactionSynchronizationManager.isCurrentTransactionReadOnly()" + TransactionSynchronizationManager.isCurrentTransactionReadOnly());
    	
        DataSourceType dataSourceType = TransactionSynchronizationManager.isCurrentTransactionReadOnly()
                ? DataSourceType.Slave : DataSourceType.Master;

        logger.info("@ current dataSourceType : {}", dataSourceType);
        return dataSourceType;
    }
}
