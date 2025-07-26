package com.oracle.proxy;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.customer.dto.KycDTO;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@FeignClient(name = "kyc-service", path = "/kycservice/api")
public interface KycServiceProxy {

    @GetMapping("/kyc/{customerId}/status")
    Map<String, String> getKycStatus(@PathVariable String customerId);

    @PostMapping("/kyc")
    KycDTO createEmptyKyc(@RequestBody KycDTO kycDTO);
}
