package com.intercom.hiringchallenge.domain.model.customer;


import com.intercom.hiringchallenge.domain.model.location.Coordinates;

import java.util.Objects;

/**
 * Created by somasundar.sekar on 4/6/2018.
 */
public class Customer {

    public final long customerId;
    public final String customerName;
    public final Coordinates customerCoordinates;


    public Customer(long customerId, String customerName, Coordinates customerCoordinates) {

        if(customerId < 0) {
            throw new IllegalArgumentException("Provide valid customer id");
        }

        if(customerName == null || "".equals(customerName.trim())) {
            throw new IllegalArgumentException("Provide valid customer name");
        }

        if(customerCoordinates == null) {
            throw new IllegalArgumentException("Provide valid customer coordinates");
        }

        this.customerId = customerId;
        this.customerName = customerName;
        this.customerCoordinates = customerCoordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerId == customer.customerId &&
                Objects.equals(customerName, customer.customerName) &&
                Objects.equals(customerCoordinates, customer.customerCoordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, customerName, customerCoordinates);
    }
}
