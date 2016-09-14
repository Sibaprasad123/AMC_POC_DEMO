package com.ifocus.automation.Logging;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;



public class ListenerClass extends TestListenerAdapter {
	public Logger logger = Logger.getLogger("devpinoyLogger");
	
	
	@Override
	public void onStart(ITestContext testContext) {		
		//super.onStart(testContext);
		logger.info("***********************************EXECUTION STARTS**********************************");
	}
	
	@Override
	public void onTestStart(ITestResult tr) {		
		//super.onTestStart(result);
		
		logger.info("TEST --> "+tr.getName()+" :STARTED");
		System.out.println("\n-----------------------------------------------------------------------------------");
		System.out.println("TEST --> "+tr.getName()+" : STARTED");
		System.out.println("-----------------------------------------------------------------------------------\n");
	}
	
	
	@Override
	public void onTestSuccess(ITestResult tr) {		
		//super.onTestSuccess(tr);
		logger.info("TEST --> "+tr.getName()+" : PASSED");
		System.out.println("\n-----------------------------------------------------------------------------------");
		System.out.println("TEST --> "+tr.getName()+" : PASSED");
		System.out.println("-----------------------------------------------------------------------------------\n");
		
	}
	
	@Override
	public void onTestFailure(ITestResult tr) {		
		//super.onTestFailure(tr);
		logger.info("TEST --> "+tr.getName()+" :FAILED");
		System.out.println("\n-----------------------------------------------------------------------------------");
		System.out.println("TEST --> "+tr.getName()+" : FAILED");
		System.out.println("-----------------------------------------------------------------------------------\n");
		
	}
	
	
	@Override
	public void onFinish(ITestContext testContext) {		
		//super.onFinish(testContext);
		logger.info("***********************************EXECUTION ENDS**********************************");
	}
	

}
