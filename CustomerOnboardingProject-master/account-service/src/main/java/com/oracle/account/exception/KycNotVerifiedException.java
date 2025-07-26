package com.oracle.account.exception;

public class KycNotVerifiedException extends RuntimeException {
    public KycNotVerifiedException(String customerId) {
        super("KYC is not VERIFIED for customer ID: " + customerId);
    }
}
