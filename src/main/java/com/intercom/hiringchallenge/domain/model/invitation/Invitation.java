package com.intercom.hiringchallenge.domain.model.invitation;

/**
 * Created by somasundar.sekar on 4/7/2018.
 */
public enum Invitation {

    BEER("We would like to invite you to our office for a Beer!!");

    private String message;

    Invitation(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
