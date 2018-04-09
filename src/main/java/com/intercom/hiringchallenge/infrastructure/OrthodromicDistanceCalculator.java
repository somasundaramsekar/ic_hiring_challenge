package com.intercom.hiringchallenge.infrastructure;


import com.intercom.hiringchallenge.domain.model.location.Coordinates;
import com.intercom.hiringchallenge.domain.model.location.DistanceCalculator;
import org.springframework.stereotype.Service;

/**
 * Created by somasundar.sekar on 4/6/2018.
 */
@Service
public class OrthodromicDistanceCalculator implements DistanceCalculator {


    private static final double EARTH_RADIUS = 6371;

    @Override
    public double calcualteDistance(Coordinates pointA, Coordinates pointB) {

        if(pointA == null || pointB == null) {
            throw new IllegalArgumentException("Coordinates A & B must be given");
        }

        double φ1 = Math.toRadians(pointA.latitude);
        double φ2 = Math.toRadians(pointB.latitude);

        double Δφ =  (φ1 - φ2);
        double Δλ =  (Math.toRadians(pointA.longitude) - Math.toRadians(pointB.longitude));

        double a = Math.sin(Δφ/2) * Math.sin(Δφ/2) +
                Math.cos(φ1) * Math.cos(φ2) *
                        Math.sin(Δλ/2) * Math.sin(Δλ/2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return EARTH_RADIUS * c;
    }
}
