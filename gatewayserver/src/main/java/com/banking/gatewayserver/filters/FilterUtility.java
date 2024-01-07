package com.banking.gatewayserver.filters;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class FilterUtility {

    public static final String CORRELATION_ID = "banking-correlation-id";

    public String getCorrelationId(HttpHeaders requestHeaders) {
        if(requestHeaders.get(CORRELATION_ID) != null) {
            List<String> requestHeadersList = requestHeaders.get(CORRELATION_ID);
            return requestHeadersList.stream().findFirst().get();
        } else {
            return null;
        }
    }
}
