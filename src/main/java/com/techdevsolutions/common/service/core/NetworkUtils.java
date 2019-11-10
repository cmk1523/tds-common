package com.techdevsolutions.common.service.core;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.AbstractNamedRecord;
import com.maxmind.geoip2.record.Subdivision;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.stream.Collectors;

public class NetworkUtils {

    public static Map<String, Object> GetAllSystemNetworkProperties() throws SocketException, UnknownHostException {
        List<Map<String, Object>> list = new ArrayList<>();

        Enumeration<NetworkInterface> networkInterfaceEnumeration = NetworkInterface.getNetworkInterfaces();
        while(networkInterfaceEnumeration.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaceEnumeration.nextElement();
            Enumeration<InetAddress> addressEnumeration = networkInterface.getInetAddresses();

            Map<String, Object> networkMap = new HashMap<>();
            String macAddress = NetworkUtils.hardwareAddressBytesToString(networkInterface.getHardwareAddress());

            if (StringUtils.isNotEmpty(macAddress)) {
                networkMap.put("network.macAddress", macAddress);
                networkMap.put("network.isUp", networkInterface.isUp());
                networkMap.put("network.isVirtual", networkInterface.isVirtual());
                networkMap.put("network.name", networkInterface.getName());
                networkMap.put("network.displayName", networkInterface.getDisplayName());

                List<Map<String, Object>> interfaces = new ArrayList<>();

                while (addressEnumeration.hasMoreElements()) {
                    InetAddress inetAddress1 = addressEnumeration.nextElement();
                    Map<String, Object> address = new HashMap<>();
                    address.put("hostname", inetAddress1.getHostName());
                    address.put("address", inetAddress1.getHostAddress());
                    interfaces.add(address);
                }

                networkMap.put("network.interfaces", interfaces);
                list.add(networkMap);
            }
        }

        Map<String, Object> map = new HashMap<>();
        map.put("network.external.ipAddress", NetworkUtils.getExternalIpAddress());
        map.put("network.local.hostname", InetAddress.getLocalHost().getHostName());
        map.put("network.networks", list);
        return map;
    }

    public static String getExternalIpAddress() {
        try {
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            InputStreamReader inputStreamReader = new InputStreamReader(whatismyip.openStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String ip = bufferedReader.readLine();
            bufferedReader.close();
            inputStreamReader.close();
            return ip;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String hardwareAddressBytesToString(byte[] mac) {
        if (mac != null) {
            StringBuilder sb = new StringBuilder(18);
            for (byte b : mac) {
                if (sb.length() > 0)
                    sb.append(':');
                sb.append(String.format("%02x", b));
            }

            return sb.toString().toUpperCase();
        } else {
            return "";
        }
    }

    public static Map<String, Object> GeoIpLookup(String ipAddress) throws IOException, GeoIp2Exception {
//        File cities = new File("/src/main/resources/GeoLite2-City.mmdb");
//        File cities = new File(pathToGeoIpDbFile);
        ClassLoader classLoader = NetworkUtils.class.getClassLoader();
        File cities = new File(Objects.requireNonNull(classLoader.getResource("GeoLite2-City.mmdb")).getFile());
        DatabaseReader dbReader = new DatabaseReader.Builder(cities).build();

        InetAddress inetAddress = InetAddress.getByName(ipAddress);
        CityResponse response = dbReader.city(inetAddress);

        Map<String, Object> map = new HashMap<>();
        map.put("city", response.getCity().getName());
        map.put("continent", response.getContinent().getName());
        map.put("country.name", response.getCountry().getName());
        map.put("country.code", response.getCountry().getIsoCode());
        map.put("country.confidence", response.getCountry().getConfidence());
        map.put("country.registered.name", response.getRegisteredCountry().getName());
        map.put("country.registered.code", response.getRegisteredCountry().getIsoCode());
        map.put("country.registered.confidence", response.getRegisteredCountry().getConfidence());
        map.put("location.lat", response.getLocation().getLatitude());
        map.put("location.lon", response.getLocation().getLongitude());
        map.put("location.accuracy", response.getLocation().getAccuracyRadius());
        map.put("location.metroCode", response.getLocation().getMetroCode());
        map.put("location.timeZone", response.getLocation().getTimeZone());
        map.put("location.populationDensity", response.getLocation().getPopulationDensity());
        map.put("location.averageIncome", response.getLocation().getAverageIncome());
        map.put("postal.code", response.getPostal().getCode());
        map.put("postal.confidence", response.getPostal().getConfidence());
        map.put("subdivisions.name", response.getSubdivisions().stream().map(AbstractNamedRecord::getName).collect(Collectors.toList()));
        map.put("subdivisions.code", response.getSubdivisions().stream().map(Subdivision::getIsoCode).collect(Collectors.toList()));
        map.put("ipAddress", response.getTraits().getIpAddress());
        map.put("isp", response.getTraits().getIsp());

//        System.out.println(JsonUtils.toPrettyJson(response.toJson()));

        return map;
    }
}
