package com.pension.disbursement.controller;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pension.disbursement.exception.AadharNumberNotFound;
import com.pension.disbursement.exception.AuthorizationException;
import com.pension.disbursement.feignclient.AuthorisingClient;
import com.pension.disbursement.model.ProcessPensionInput;
import com.pension.disbursement.model.ProcessPensionResponse;
import com.pension.disbursement.service.PensionDisbursementServiceImpl;

import io.swagger.annotations.ApiOperation;


@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class PensionDisbursementController {

	@Autowired
	private PensionDisbursementServiceImpl pensionDisbursementServiceImpl;
	
	@Autowired
	private AuthorisingClient authorisingClient;
	
	@PostMapping("/disbursePension")
	@ApiOperation(notes = "Returns the Process Pension Responce code", value = "Find Process Responce Code")
	public ProcessPensionResponse getResponse(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader,
			@RequestBody ProcessPensionInput processpensionInput) throws AuthorizationException, AadharNumberNotFound
	{
	
		if (authorisingClient.authorizeTheRequest(requestTokenHeader)) 
		{
			return pensionDisbursementServiceImpl.getResponce(requestTokenHeader,processpensionInput);
		}
		else
		{
			throw new AuthorizationException("Not allowed AuthorizationException");
		}
		
			
	}
	
	
}
