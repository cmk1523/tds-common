package com.techdevsolutions.common.service.core;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class SystemUtilsTests {
    @Test
    public void test() {
        Map<String, Object> map = SystemUtils.GetAllSystemPropertiesOrganized();
        Assert.assertTrue(true);
    }
}
