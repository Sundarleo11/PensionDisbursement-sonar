package com.pension.disbursement.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.pension.disbursement.exception.AadharNumberNotFound;
import com.pension.disbursement.exception.AuthorizationException;
import com.pension.disbursement.model.PensionerDetail;

import io.swagger.annotations.ApiParam;

@FeignClient(name ="PensionerDetail-Microservice",url = "http://localhost:6001/pensioner/api")
public interface PensionDisbursementFeignClient {
	
	@GetMapping("/PensionerDetailByAadhaar/{aadharNumber}")
	public PensionerDetail getPensionerDetailByAadhaar(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader,
			@ApiParam(name = "aadharNumber", value = "Aadhar Card Number") 
			@PathVariable long aadharNumber) throws AuthorizationException, AadharNumberNotFound;
}
