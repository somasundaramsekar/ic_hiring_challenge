package com.intercom.hiringchallenge.infrastructure;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

/**
 * Created by somasundar.sekar on 4/7/2018.
 */
public class JsonPathUtil {

    private final String json;
    private final ReadContext context;

    public JsonPathUtil(String json) {
        this.json = json;
        this.context = JsonPath.parse(json);
    }

    public String singleValueToken(String token) {
        Object o =  context.read(token);
        return o != null ? o.toString() : null;
    }
}
