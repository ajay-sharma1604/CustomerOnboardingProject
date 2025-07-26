package com.oracle.account.util;

import com.oracle.account.dto.AccountDTO;
import com.oracle.account.model.Account;

public class AccountMapper {

    public static AccountDTO toDTO(Account account) {
        AccountDTO dto = new AccountDTO();
        dto.setAccountId(account.getAccountId());
        dto.setCustomerId(account.getCustomerId());
        dto.setAccountType(account.getAccountType());
        dto.setAccountStatus(account.getAccountStatus());
        return dto;
    }

    public static Account toEntity(AccountDTO dto) {
        Account account = new Account();
        account.setAccountId(dto.getAccountId());
        account.setCustomerId(dto.getCustomerId());
        account.setAccountType(dto.getAccountType());
        account.setAccountStatus(dto.getAccountStatus());
        return account;
    }
}
