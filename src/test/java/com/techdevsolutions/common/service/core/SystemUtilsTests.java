package com.techdevsolutions.common.service.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

public class SystemUtilsTests {
    @Ignore
    @Test
    public void test() throws JsonProcessingException {
        Map<String, Object> map = SystemUtils.GetAllSystemPropertiesOrganized(true);
        String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(map);
        System.out.println(json);
        Assert.assertTrue(true);
    }
}
