package com.apolo.exception;

import java.util.Date;

public class ExceptionResponse {
    private Date timestamp;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessagep() {
        return messagep;
    }

    public void setMessagep(String messagep) {
        this.messagep = messagep;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    private String messagep;
    private String details;

    public ExceptionResponse(Date timestamp, String messagep, String details) {
        super();
        this.timestamp = timestamp;
        this.messagep = messagep;
        this.details = details;
    }
}
