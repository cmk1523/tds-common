package com.techdevsolutions.common.beans.geo;

import java.io.Serializable;
import java.util.Objects;

public class GeoLocation implements Serializable, Comparable<GeoLocation> {
    private double latitude;
    private double longitude;

    public GeoLocation() {
    }

    public GeoLocation(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "GeoLocation{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeoLocation that = (GeoLocation) o;
        return Objects.equals(latitude, that.latitude) &&
                Objects.equals(longitude, that.longitude);
    }

    @Override
    public int hashCode() {

        return Objects.hash(latitude, longitude);
    }

    public double getLatitude() {
        return latitude;
    }

    public GeoLocation setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public double getLongitude() {
        return longitude;
    }

    public GeoLocation setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    @Override
    public int compareTo(GeoLocation o) {
        return this.toString().compareTo(o.toString());
    }
}
