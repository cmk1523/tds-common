package com.techdevsolutions.common.service.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

public class NetworkUtilsTests {
    @Ignore
    @Test
    public void test() throws IOException, GeoIp2Exception {
        Map<String, Object> map = NetworkUtils.GeoIpLookup(NetworkUtils.getExternalIpAddress());
        String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(map);
//        System.out.println(json);
        Assert.assertTrue(map != null);
    }
}
