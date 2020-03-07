package com.techdevsolutions.common.dao.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseMySqlDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String host = "localhost";

    public BaseMySqlDao() {
    }

    public BaseMySqlDao(String host) {
        this.setHost(host);
    }

    public String getHost() {
        return host;
    }

    public BaseMySqlDao setHost(String host) {
        this.host = host;
        return this;
    }
}
