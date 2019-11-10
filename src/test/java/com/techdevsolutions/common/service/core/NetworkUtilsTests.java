package com.techdevsolutions.common.service.core;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

@Ignore
public class NetworkUtilsTests {
    @Test
    public void test() throws IOException, GeoIp2Exception {
        NetworkUtils.GeoIpLookup(NetworkUtils.getExternalIpAddress());
    }
}
