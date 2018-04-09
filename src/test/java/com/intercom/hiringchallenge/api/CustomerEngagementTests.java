package com.intercom.hiringchallenge.api;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by somasundar.sekar on 4/8/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = "file_path=customer_test.txt")
public class CustomerEngagementTests {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Autowired
    private CustomerEngagement customerEngagement;

    @Test
    public void throws_IllegalArgumentException_When_distanceCalculator_customerDatasource_not_passed() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("distanceCalculator, customerDatasource & invitationSender must be specified");
        new CustomerEngagement(null, null, null);
    }

    @Test
    public void console_invite_sent_to_the_default_customers_txt() {
        customerEngagement.inviteNearestCustomersForABeer();
    }
}
