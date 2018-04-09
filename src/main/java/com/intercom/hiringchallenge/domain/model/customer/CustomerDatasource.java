package com.intercom.hiringchallenge.domain.model.customer;

import java.util.stream.Stream;

/**
 * Created by somasundar.sekar on 4/6/2018.
 */
public interface CustomerDatasource {

    Stream<Customer> getAllCustomerRecords();

}
