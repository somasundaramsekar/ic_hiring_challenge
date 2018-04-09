package com.intercom.hiringchallenge.domain.model.invitation;


import com.intercom.hiringchallenge.domain.model.customer.Customer;

import java.util.function.Function;

/**
 * Created by somasundar.sekar on 4/7/2018.
 */
public interface InvitationSender {

    void sendInvitation(Customer c, Invitation invitation, Function<Customer, Boolean> filter);
}
