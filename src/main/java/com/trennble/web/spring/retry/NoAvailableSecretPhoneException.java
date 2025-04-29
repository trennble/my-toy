package com.trennble.web.spring.retry;

public class NoAvailableSecretPhoneException extends RuntimeException{
    public NoAvailableSecretPhoneException(String msg) {
        super(msg);
    }
}
