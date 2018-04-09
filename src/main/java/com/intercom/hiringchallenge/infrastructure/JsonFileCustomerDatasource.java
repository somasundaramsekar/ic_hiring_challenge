package com.intercom.hiringchallenge.infrastructure;


import com.intercom.hiringchallenge.domain.model.customer.Customer;
import com.intercom.hiringchallenge.domain.model.customer.CustomerDatasource;
import com.intercom.hiringchallenge.domain.model.location.Coordinates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by somasundar.sekar on 4/6/2018.
 */
@Component
public class JsonFileCustomerDatasource implements CustomerDatasource {

    private static final Logger logger = LoggerFactory.getLogger(JsonFileCustomerDatasource.class);

    private final String filePath;
    private Stream<String> stream;

    public JsonFileCustomerDatasource(@Value("${file_path}") String filePath) {
        if(filePath == null || "".equals(filePath.trim()))
            throw new IllegalArgumentException("Provide valid filePath");

        this.filePath = filePath;
    }


    @Override
    public Stream<Customer> getAllCustomerRecords() {

        try {
            stream = Files.lines(Paths.get(filePath));
            return stream.map(json -> fromJsonString(json)).filter(customer -> customer != null);
        }catch (IOException e) {
            throw new RuntimeException("Error while trying to retrieve the files of the customer");
        }
    }

    private Customer fromJsonString(String json) {
        final JsonPathUtil jUtil = new JsonPathUtil(json);
        try {
            return new Customer(
                    Long.parseLong(jUtil.singleValueToken("$.user_id")),
                    jUtil.singleValueToken("$.name"),
                    new Coordinates(
                            Double.parseDouble(jUtil.singleValueToken("$.latitude")),
                            Double.parseDouble(jUtil.singleValueToken("$.longitude"))
                    )

            );
        }catch (Exception e) {
            logger.error("Skipping erroneous record "+json+", reason: "+e.getMessage());
            return null;
        }
    }

    @PreDestroy
    private void closeFileStream() {
        try {
        if(stream != null)
            stream.close();
        }catch (Exception e) {
            logger.info("Error closing the file, ignored");
        }

    }

}
