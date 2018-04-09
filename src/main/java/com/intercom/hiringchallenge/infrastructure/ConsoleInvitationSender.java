package com.intercom.hiringchallenge.infrastructure;


import com.intercom.hiringchallenge.domain.model.customer.Customer;
import com.intercom.hiringchallenge.domain.model.invitation.Invitation;
import com.intercom.hiringchallenge.domain.model.invitation.InvitationSender;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Created by somasundar.sekar on 4/7/2018.
 */
@Service
public class ConsoleInvitationSender implements InvitationSender {

    @Override
    public void sendInvitation(Customer c, Invitation invitation, Function<Customer, Boolean> filter) {
        if(filter.apply(c))
            System.out.println(String.format("Dear %s,` %s. Cheers....", c.customerName, invitation.getMessage()));
    }
}
