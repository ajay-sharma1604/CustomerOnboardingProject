package com.oracle.account.dao;

import com.oracle.account.model.Account;

public interface AccountDAO {
    Account saveAccount(Account account);
    Account findByCustomerId(String customerId);
}
