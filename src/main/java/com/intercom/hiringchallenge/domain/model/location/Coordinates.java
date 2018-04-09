package com.intercom.hiringchallenge.domain.model.location;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Objects;

/**
 * Created by somasundar.sekar on 4/6/2018.
 */
public class Coordinates {

    private final DecimalFormat df = new DecimalFormat("#.#######");

    protected static final String LATITUDE_PATTERN="^(\\+|-)?(?:90(?:(?:\\.0{1,8})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,8})?))$";
    protected static final String LONGITUDE_PATTERN="^(\\+|-)?(?:180(?:(?:\\.0{1,8})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,8})?))$";

    public final double latitude;
    public final double longitude;

    public Coordinates(double latitude, double longitude) {
        df.setRoundingMode(RoundingMode.UP);
        if(!df.format(latitude).matches(LATITUDE_PATTERN)) {
            throw new IllegalArgumentException("Given Latitude is incorrect");
        }
        if(!df.format(longitude).matches(LONGITUDE_PATTERN)) {
            throw new IllegalArgumentException("Given Longitude is incorrect");
        }
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Double.compare(that.latitude, latitude) == 0 &&
                Double.compare(that.longitude, longitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }
}
