package com.techdevsolutions.common.service.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.management.OperatingSystemMXBean;
import org.apache.commons.lang3.StringUtils;

import java.lang.management.ManagementFactory;
import java.util.*;
import java.util.stream.Collectors;

public class SystemUtils {
    public static Map<String, Object> GetAllSystemProperties() {
        Properties properties = System.getProperties();
        Map<String, Object> map = new ObjectMapper().convertValue(properties, Map.class);
        map.put("datetime", DateUtils.DateToISO(new Date()));

        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        Map<String, Object> osBeanMap = new ObjectMapper().convertValue(osBean, Map.class);
        osBeanMap.remove("objectName");

        osBeanMap.entrySet().forEach((i)->{
            map.put("system." + i.getKey(), i.getValue());
        });

        Runtime runtime = Runtime.getRuntime();
        map.put("runtime.availableProcessors", runtime.availableProcessors());
        map.put("runtime.freeMemory", runtime.freeMemory());
        map.put("runtime.maxMemory", runtime.maxMemory());
        map.put("runtime.totalMemory", runtime.totalMemory());

        try {
            Map<String, Object> network = NetworkUtils.GetAllSystemNetworkProperties();
            map.put("network.external.ipAddress", network.get("network.external.ipAddress"));
            map.put("network.local.hostname", network.get("network.local.hostname"));
            map.put("network.networks", network.get("network.networks"));

            try {
                String externalIpAddress = (String) map.get("network.external.ipAddress");

                if (StringUtils.isNotEmpty(externalIpAddress)) {
                    map.put("geoip", NetworkUtils.GeoIpLookup(externalIpAddress));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    public static Map<String, Object> GetAllSystemPropertiesOrganized() {
        Map<String, Object> map = SystemUtils.GetAllSystemProperties();
        Map<String, Object> organized = new HashMap<>();
        organized.put("datetime", map.get("datetime"));
        organized.put("os", SystemUtils.GetOperatingSystemProperties());
        organized.put("network", SystemUtils.GetNetworkSystemProperties());
        organized.put("java", SystemUtils.GetJavaSystemProperties());
        organized.put("user", SystemUtils.GetUserSystemProperties());
        organized.put("sun", map.entrySet().stream().filter((i)->i.getKey().contains("sun."))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        organized.put("system", map.entrySet().stream()
                .filter((i)->i.getKey().startsWith("system."))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        organized.put("geoip", map.get("geoip"));
        return organized;
    }

    public static Map<String, Object> GetGeoIpProperties() {
        Map<String, Object> map = SystemUtils.GetAllSystemProperties();
        return map.entrySet().stream()
                .filter((i)->i.getKey().startsWith("user."))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<String, Object> GetUserSystemProperties() {
        Map<String, Object> map = SystemUtils.GetAllSystemProperties();
        return map.entrySet().stream()
                .filter((i)->i.getKey().startsWith("user."))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<String, Object> GetJavaSystemProperties() {
        Map<String, Object> map = SystemUtils.GetAllSystemProperties();
        return map.entrySet().stream()
                .filter((i)->i.getKey().startsWith("java."))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<String, Object> GetOperatingSystemProperties() {
        Map<String, Object> map = SystemUtils.GetAllSystemProperties();
        return map.entrySet().stream()
                .filter((i)->i.getKey().startsWith("os."))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<String, Object> GetNetworkSystemProperties() {
        Map<String, Object> map = SystemUtils.GetAllSystemProperties();
        return map.entrySet().stream()
                .filter((i)->i.getKey().startsWith("network."))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
