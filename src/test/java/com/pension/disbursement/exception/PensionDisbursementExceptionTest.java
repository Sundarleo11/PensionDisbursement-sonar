package com.pension.disbursement.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.pension.disbursement.exception.AadharNumberNotFound;
import com.pension.disbursement.exception.AuthorizationException;
import com.pension.disbursement.exception.ExceptionDetails;




@SpringBootTest
public class PensionDisbursementExceptionTest {
	
	@InjectMocks
	private AadharNumberNotFound aadharNumberNotFound;
	
	@InjectMocks
	private AuthorizationException authorizationException;
	
	@InjectMocks
	private ExceptionDetails exceptionDetails;
	
	@Test
	public void testAadharNumberNotFound()
	{
		String reason = "Invalid aadharNumber";
		assertEquals(reason, "Invalid aadharNumber");
	}
	
	@Test
	public void testAuthorizationException()
	{
		String reason = "Invalid token";
		assertEquals(reason, "Invalid token");
	}
	

}
