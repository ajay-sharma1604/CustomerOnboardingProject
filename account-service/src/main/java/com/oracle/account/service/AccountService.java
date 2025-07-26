package com.oracle.account.service;

import com.oracle.account.dto.AccountDTO;

public interface AccountService {
    AccountDTO createAccount(AccountDTO dto);
    AccountDTO getAccountByCustomerId(String customerId);
}
