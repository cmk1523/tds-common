package com.techdevsolutions.common.service.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class BaseService {
    protected Environment environment;

    @Autowired
    public BaseService(Environment environment) {
        this.environment = environment;
    }

}
