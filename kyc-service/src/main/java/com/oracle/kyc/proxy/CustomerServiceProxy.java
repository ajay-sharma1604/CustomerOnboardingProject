package com.oracle.kyc.proxy;

import com.oracle.kyc.dto.CustomerResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", path = "/customerservice/api")
public interface CustomerServiceProxy {

    // Fix: use /customers/{id} instead of /customer/{id}
    @GetMapping("/customers/{id}")
    CustomerResponseDTO getCustomerById(@PathVariable("id") String id);
}
