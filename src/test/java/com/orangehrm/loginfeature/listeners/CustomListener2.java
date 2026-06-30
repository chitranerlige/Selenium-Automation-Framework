package com.orangehrm.loginfeature.listeners;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

public class CustomListener2 implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("onTestStart -> Test name: " + result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("onTestSuccess -> Test name: " + result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("onTestFailure -> Test name: " + result.getName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		//System.out.println("onTestSkipped -> Test name: " + result.getName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		//System.out.println("onTestFailedButWithinSuccessPercentage -> Test name: " + result.getName());
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		//System.out.println("onTestFailedWithTimeout -> Test name: " + result.getName());
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("onStart -> Test name: " + context.getName());
		ITestNGMethod methods[] = context.getAllTestMethods();
		System.out.println("These methods executed in this test tag:");
		for(ITestNGMethod method: methods) {
			System.out.println(method.getMethodName());
		}
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("onFinish -> Test name: " + context.getName());
	}



	
}
