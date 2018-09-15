package com.test.basesetup;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public  class TestNgRealTime implements ITestListener, ISuiteListener, IInvokedMethodListener {
    static  int passCounter = 0;
	static  int failCounter = 0;
	
	@Override
	public void onStart(ISuite suite){
		System.out.println("Test Suite Started With Overall Scripts Count : "+suite.getAllMethods().size());
	}

	@Override
	public void onFinish(ISuite suite) {
		
		System.out.println("Test Suite Finished");
	}

	@Override
	public void onTestStart(ITestResult result) {
		
		System.out.println("TestName is :"+result.getMethod().getGroups()[0]);
		System.out.println("Execution started for method:"+result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		passCounter++;
	System.out.println("At This Moment Passed Count Is : "+passCounter);
	
	
	}

	@Override
	public void onTestFailure(ITestResult result) {
		failCounter++;
		System.out.println("At This Moment Failed Count Is : "+failCounter);
	}

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onFinish(ITestContext context) {
        // TODO Auto-generated method stub
        
    }


}
