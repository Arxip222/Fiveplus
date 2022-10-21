package com.fiveplus.platform.exception;

public class ResourceNotFoundException extends Exception{
    public ResourceNotFoundException(String errMessage) {
        super(errMessage);
    }
}
