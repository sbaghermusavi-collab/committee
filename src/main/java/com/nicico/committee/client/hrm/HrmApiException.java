package com.nicico.committee.client.hrm;

import java.io.IOException;

public class HrmApiException extends IOException {

    private final int code;
    private final String errorBody;

    public HrmApiException(int code, String errorBody) {
        super("HRM API request failed with code " + code + ": " + errorBody);
        this.code = code;
        this.errorBody = errorBody;
    }

    public int getCode() {
        return code;
    }

    public String getErrorBody() {
        return errorBody;
    }
}
