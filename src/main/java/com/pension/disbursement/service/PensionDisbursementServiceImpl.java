package com.pension.disbursement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pension.disbursement.exception.AadharNumberNotFound;
import com.pension.disbursement.exception.AuthorizationException;
import com.pension.disbursement.feignclient.PensionDisbursementFeignClient;
import com.pension.disbursement.model.PensionerDetail;
import com.pension.disbursement.model.ProcessPensionInput;
import com.pension.disbursement.model.ProcessPensionResponse;

@Service
public class PensionDisbursementServiceImpl implements PensionDisbursementService {
	
	@Autowired
	private PensionDisbursementFeignClient pensionDisbursementFeignClient;
	
	
	@Override
	public ProcessPensionResponse getResponce(String token,ProcessPensionInput processPensionInput) throws AuthorizationException, AadharNumberNotFound
	{
		PensionerDetail pensionerDetail = null;
		try
		{
		pensionerDetail = pensionDisbursementFeignClient.getPensionerDetailByAadhaar(token, processPensionInput.getAadharNumber());
		}
		catch(AadharNumberNotFound e)
		{
			throw new AadharNumberNotFound("Aadhar Number Not found");
			
		}
		ProcessPensionResponse processPensionResponse = new ProcessPensionResponse();
		double serviceCharge = processPensionInput.getBankCharge();
		
		double checkAmount=0;
		if(pensionerDetail.getBankType().equalsIgnoreCase("public"))
		{
			checkAmount = 500;
		}
		else if(pensionerDetail.getBankType().equalsIgnoreCase("private"))
		{
			checkAmount = 550;
		}
		
	
		
		if(checkAmount == serviceCharge)
		{
			processPensionResponse.setProcessPensionStatusCode(10);
		}
		else
		{
			processPensionResponse.setProcessPensionStatusCode(21);
		}
		
		
		return processPensionResponse;
	}

}
