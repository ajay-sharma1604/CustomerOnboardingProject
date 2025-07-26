package com.oracle.account.service;

import com.oracle.account.dao.AccountDAO;
import com.oracle.account.dto.AccountDTO;
import com.oracle.account.dto.KycVerificationResponse;
import com.oracle.account.exception.AccountNotFoundException;
import com.oracle.account.exception.IdentityMismatchException;
import com.oracle.account.exception.KycNotVerifiedException;
import com.oracle.account.model.Account;
import com.oracle.account.proxy.KycServiceProxy;
import com.oracle.account.util.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO accountDAO;
    
    @Autowired
    private KycServiceProxy kycServiceProxy;

    @Override
    public AccountDTO createAccount(AccountDTO dto) {

    	KycVerificationResponse kyc = kycServiceProxy.getKycDetails(dto.getCustomerId());

    	if (!"VERIFIED".equalsIgnoreCase(kyc.getKycStatus())) {
    	    throw new KycNotVerifiedException(dto.getCustomerId());
    	}

    	if (!dto.getPanNumber().equalsIgnoreCase(kyc.getPanNumber()) ||
    	    !dto.getAadhaarNumber().equalsIgnoreCase(kyc.getAadhaarNumber())) {
    	    throw new IdentityMismatchException("PAN or Aadhaar does not match the verified KYC data.");
    	}


        //Proceed with account creation
        Account account = AccountMapper.toEntity(dto);
        account.setAccountId(generateMockAccountNumber());
        account.setAccountStatus("ACTIVE");
        Account saved = accountDAO.saveAccount(account);
        
        return AccountMapper.toDTO(saved);
    }


    @Override
    public AccountDTO getAccountByCustomerId(String customerId) {
        Account account = accountDAO.findByCustomerId(customerId);
        if (account == null) {
            throw new AccountNotFoundException(customerId);
        }
        return AccountMapper.toDTO(account);
    }

    private String generateMockAccountNumber() {
        return "AC" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10).toUpperCase();
    }
}
