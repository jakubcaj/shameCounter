package com.idc.sterba.demo.config;


import org.eclipse.persistence.config.BatchWriting;
import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class EclipseLinkJpaConfiguration extends JpaBaseConfiguration {

//    @Value("${db.username}")
//    private String dbUserName;

    public EclipseLinkJpaConfiguration(@Qualifier("dataSource") DataSource dataSource,
                                       //JpaProperties properties,
                                       ObjectProvider<JtaTransactionManager> jtaTransactionManager,
                                       ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
        super(dataSource, buildJpaProperties(), jtaTransactionManager, transactionManagerCustomizers);
    }

    private static JpaProperties buildJpaProperties() {
        JpaProperties jpaProperties = new JpaProperties();
        jpaProperties.setProperties(initJpaProperties());
        return jpaProperties;
    }

    private static Map<String, String> initJpaProperties() {
        final Map<String, String> ret = new HashMap<>();
        // Add any JpaProperty you are interested in and is supported by your Database and JPA implementation
        ret.put(PersistenceUnitProperties.BATCH_WRITING, BatchWriting.JDBC);
//        ret.put(PersistenceUnitProperties.LOGGING_LEVEL, SessionLog.FINE_LABEL);
        ret.put(PersistenceUnitProperties.WEAVING, "false");
        ret.put(PersistenceUnitProperties.DDL_GENERATION, "true");
        return ret;
    }

    @Override
    protected Map<String, Object> getVendorProperties() {
        Map<String, Object> map = new HashMap<>();
        //map.put(PersistenceUnitProperties.WEAVING, true);
        map.put(PersistenceUnitProperties.DDL_GENERATION, PersistenceUnitProperties.CREATE_OR_EXTEND);
//        map.put(PersistenceUnitProperties.DDL_GENERATION, PersistenceUnitProperties.DROP_AND_CREATE);
        return map;
    }

    @Override
    protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
        return new EclipseLinkJpaVendorAdapter();
    }

    //...
}
