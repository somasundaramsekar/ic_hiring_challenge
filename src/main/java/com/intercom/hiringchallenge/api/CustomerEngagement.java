package com.intercom.hiringchallenge.api;


import com.intercom.hiringchallenge.domain.model.invitation.InvitationSender;
import com.intercom.hiringchallenge.infrastructure.ConsoleInvitationSender;
import com.intercom.hiringchallenge.domain.model.customer.Customer;
import com.intercom.hiringchallenge.domain.model.customer.CustomerDatasource;
import com.intercom.hiringchallenge.domain.model.invitation.Invitation;
import com.intercom.hiringchallenge.domain.model.location.Coordinates;
import com.intercom.hiringchallenge.domain.model.location.DistanceCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * Created by somasundar.sekar on 4/7/2018.
 */
@Component
public class CustomerEngagement {

    private static final Coordinates INTERCOM_COORDINATES = new Coordinates(53.339428,  -6.257664);
    private static final int BOUNDING_RADIUS = 100;


    private final DistanceCalculator distanceCalculator;
    private final CustomerDatasource customerDatasource;
    private final InvitationSender invitationSender;

    private final Function<Customer, Boolean> nearestCustomer;

    @Autowired
    public CustomerEngagement(DistanceCalculator distanceCalculator,
                              CustomerDatasource customerDatasource,
                              InvitationSender invitationSender) {

        if(distanceCalculator == null || customerDatasource == null || invitationSender == null){
            throw new IllegalArgumentException("distanceCalculator, customerDatasource & invitationSender must be specified");
        }

        this.distanceCalculator = distanceCalculator;
        this.customerDatasource = customerDatasource;
        this.invitationSender = invitationSender;

        nearestCustomer = customer ->
                this.distanceCalculator.calcualteDistance(INTERCOM_COORDINATES, customer.customerCoordinates) <= BOUNDING_RADIUS;

    }

    public void inviteNearestCustomersForABeer() {

        this.customerDatasource.getAllCustomerRecords().forEach(
                customer -> invitationSender.sendInvitation(customer, Invitation.BEER, nearestCustomer)
        );

    }
}
