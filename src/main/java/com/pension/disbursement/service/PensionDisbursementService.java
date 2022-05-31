package com.pension.disbursement.service;

import com.pension.disbursement.exception.AadharNumberNotFound;
import com.pension.disbursement.exception.AuthorizationException;
import com.pension.disbursement.model.ProcessPensionInput;
import com.pension.disbursement.model.ProcessPensionResponse;

public interface PensionDisbursementService {
	public ProcessPensionResponse getResponce(String token,ProcessPensionInput processPensionInput) throws AuthorizationException, AadharNumberNotFound;
	

}
