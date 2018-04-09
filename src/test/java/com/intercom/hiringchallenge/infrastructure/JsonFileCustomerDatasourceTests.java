package com.intercom.hiringchallenge.infrastructure;

import com.intercom.hiringchallenge.domain.model.customer.Customer;
import com.intercom.hiringchallenge.domain.model.location.Coordinates;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by somasundar.sekar on 4/8/2018.
 */
@RunWith(JUnit4.class)
public class JsonFileCustomerDatasourceTests {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void throws_Exception_While_Instantiation_Without_FilePath() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Provide valid filePath");
        new JsonFileCustomerDatasource(null);
    }

    @Test
    public void throws_RuntimeException_For_Invalid_File() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Error while trying to retrieve the files of the customer");
        new JsonFileCustomerDatasource("nofile").getAllCustomerRecords();
    }

    @Test
    public void fetches_customer_record_from_json_line_file() {
        Customer expected = new Customer(25, "David Behan", new Coordinates(52.833502, -8.522366));

        final List<Customer> customers = new JsonFileCustomerDatasource("customer_test.txt")
                .getAllCustomerRecords().collect(Collectors.toList());

        final Customer actual = customers.get(0);

        assertThat(expected).isEqualTo(actual);
    }

    @Test
    public void ignores_invalid_json_line() {
        new JsonFileCustomerDatasource("invalid_input_file.txt")
                .getAllCustomerRecords().collect(Collectors.toList());
    }

}
