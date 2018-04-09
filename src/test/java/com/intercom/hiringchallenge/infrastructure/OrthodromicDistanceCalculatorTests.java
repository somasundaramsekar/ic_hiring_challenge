package com.intercom.hiringchallenge.infrastructure;

import com.intercom.hiringchallenge.domain.model.location.Coordinates;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by somasundar.sekar on 4/8/2018.
 */
@RunWith(JUnit4.class)
public class OrthodromicDistanceCalculatorTests {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void throws_IllegalArgumentException_calcualteDistance_without_Coordinates() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Coordinates A & B must be given");
        new OrthodromicDistanceCalculator().calcualteDistance(null, null);
    }

    @Test
    public void calcualteDistance_PointA_PointB_Accurate_To_Few_Hundread_Meters() {
        Coordinates pointA = new Coordinates(52.833502, -8.522366);
        Coordinates pointB = new Coordinates(53.339428, -6.257664);
        final double actual = new OrthodromicDistanceCalculator().calcualteDistance(pointA, pointB);
        double expected = 161.36;

        assertThat(expected).isEqualTo(round(actual, 2));
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
