package com.oracle.account.proxy;

import com.oracle.account.dto.KycVerificationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "kyc-service", path = "/kycservice/api")
public interface KycServiceProxy {

    @GetMapping("/kyc/{customerId}")
    KycVerificationResponse getKycDetails(@PathVariable("customerId") String customerId);
}
