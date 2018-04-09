package com.intercom.hiringchallenge;

import com.intercom.hiringchallenge.api.CustomerEngagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class HiringchallengeApplication implements ApplicationRunner {

	@Autowired
	private CustomerEngagement customerEngagement;

	public static void main(String[] args) {
		SpringApplication.run(HiringchallengeApplication.class, args);
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {
		customerEngagement.inviteNearestCustomersForABeer();
	}
}
