package com.intercom.hiringchallenge.domain.model.customer;

import com.intercom.hiringchallenge.domain.model.customer.Customer;
import com.intercom.hiringchallenge.domain.model.location.Coordinates;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by somasundar.sekar on 4/8/2018.
 */

@RunWith(JUnit4.class)
public class CustomerTests {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void throws_IllegalArgumentException_Invalid_Id() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Provide valid customer id");
        new Customer(-1, "test", new Coordinates(90, 180));
    }

    @Test
    public void throws_IllegalArgumentException_Invalid_Name() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Provide valid customer name");
        new Customer(1, null, new Coordinates(90, 180));
    }

    @Test
    public void throws_IllegalArgumentException_Invalid_Coordinates() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Provide valid customer coordinates");
        new Customer(1, "test",null);
    }

    @Test
    public void creates_customer() {
        new Customer(1, "test",new Coordinates(90, 180));
    }

}
