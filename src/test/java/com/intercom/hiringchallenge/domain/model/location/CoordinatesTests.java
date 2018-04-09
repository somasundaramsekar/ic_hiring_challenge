package com.intercom.hiringchallenge.domain.model.location;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by somasundar.sekar on 4/8/2018.
 */
@RunWith(JUnit4.class)
public class CoordinatesTests {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void throws_IllegalArgumentException_Invalid_Latitude() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Given Latitude is incorrect");
        new Coordinates(-91, 180);
    }

    @Test
    public void throws_IllegalArgumentException_Invalid_Longitude() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Given Longitude is incorrect");
        new Coordinates(-90, -181);
    }

    @Test
    public void creates_Coordinates() {
        new Coordinates(-90, -180);
    }
}
