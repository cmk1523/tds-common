package com.techdevsolutions.common.beans.elasticsearchCommonSchema;

import com.techdevsolutions.common.beans.geo.GeoLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Geo {
    private String cityName;
    private String continentName;
    private String countryIsoCode;
    private String countryName;
    private GeoLocation location;
    private String name;
    private String regionIsoCode;
    private String regionName;

    public Map<String, Object> toElasticsearchMap(Geo item) {
        return Geo.ToElasticsearchMap(item);
    }

    public static Map<String, Object> ToElasticsearchMap(Geo item) {
        Map<String, Object> map = new HashMap<>();
        map.put("geo.city_name", item.getCityName());
        map.put("geo.continent_name", item.getContinentName());
        map.put("geo.country_iso_code", item.getCountryIsoCode());
        map.put("geo.country_name", item.getContinentName());
        map.put("geo.location", item.getLocation().getLatitude() + "," + item.getLocation().getLongitude());
        map.put("geo.name", item.getName());
        map.put("geo.region_iso_code", item.getRegionIsoCode());
        map.put("geo.region_name", item.getRegionName());
        return map;
    }

    @Override
    public String toString() {
        return "Geo{" +
                "cityName='" + cityName + '\'' +
                ", continentName='" + continentName + '\'' +
                ", countryIsoCode='" + countryIsoCode + '\'' +
                ", countryName='" + countryName + '\'' +
                ", location='" + location + '\'' +
                ", name='" + name + '\'' +
                ", regionIsoCode='" + regionIsoCode + '\'' +
                ", regionName='" + regionName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Geo geo = (Geo) o;
        return Objects.equals(cityName, geo.cityName) &&
                Objects.equals(continentName, geo.continentName) &&
                Objects.equals(countryIsoCode, geo.countryIsoCode) &&
                Objects.equals(countryName, geo.countryName) &&
                Objects.equals(location, geo.location) &&
                Objects.equals(name, geo.name) &&
                Objects.equals(regionIsoCode, geo.regionIsoCode) &&
                Objects.equals(regionName, geo.regionName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cityName, continentName, countryIsoCode, countryName, location, name, regionIsoCode, regionName);
    }

    public String getCityName() {
        return cityName;
    }

    public Geo setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public String getContinentName() {
        return continentName;
    }

    public Geo setContinentName(String continentName) {
        this.continentName = continentName;
        return this;
    }

    public String getCountryIsoCode() {
        return countryIsoCode;
    }

    public Geo setCountryIsoCode(String countryIsoCode) {
        this.countryIsoCode = countryIsoCode;
        return this;
    }

    public String getCountryName() {
        return countryName;
    }

    public Geo setCountryName(String countryName) {
        this.countryName = countryName;
        return this;
    }

    public GeoLocation getLocation() {
        return location;
    }

    public Geo setLocation(GeoLocation location) {
        this.location = location;
        return this;
    }

    public String getName() {
        return name;
    }

    public Geo setName(String name) {
        this.name = name;
        return this;
    }

    public String getRegionIsoCode() {
        return regionIsoCode;
    }

    public Geo setRegionIsoCode(String regionIsoCode) {
        this.regionIsoCode = regionIsoCode;
        return this;
    }

    public String getRegionName() {
        return regionName;
    }

    public Geo setRegionName(String regionName) {
        this.regionName = regionName;
        return this;
    }
}
