package com.oracle.account.exception;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(String customerId) {
        super("Account not found for customer ID: " + customerId);
    }
}
