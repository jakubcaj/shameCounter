package com.idc.sterba.demo.dto;

import java.io.Serializable;

public class JSONResponse implements Serializable {
    private Object object;
    private boolean success;
    private String errorMessage;

    public JSONResponse() {}

    public JSONResponse(Object object) {
        this.object = object;
        this.success = true;
    }

    public JSONResponse(Object object, boolean success) {
        this.object = object;
        this.success = success;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
