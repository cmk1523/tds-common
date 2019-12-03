package com.techdevsolutions.common.service.core;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

public class SystemUtilsTests {
    @Ignore
    @Test
    public void test() {
        Map<String, Object> map = SystemUtils.GetAllSystemPropertiesOrganized(true);
        Assert.assertTrue(true);
    }
}
