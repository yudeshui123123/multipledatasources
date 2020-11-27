package com.example.common;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * TODO
 *
 * @author yds
 * @version 1.0
 * @date 2020/11/27 20:23
 * @description:
 */
public class DataSourceConfig extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return CustomerContextHolder.getCustomerType();
    }
}
