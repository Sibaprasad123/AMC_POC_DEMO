package com.ifocus.automation.Testscripts;

import java.net.MalformedURLException;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.ifocus.automation.GlobalRepository.GlobalVariables;
import com.ifocus.automation.Pages.DeviceSettingsPage;
import com.ifocus.automation.Pages.WebCareConsolePage;

import io.appium.java_client.android.AndroidDriver;

public class BloodPressureTest {

	@Test(priority=1, enabled=true)
	public void deviceSettings() throws MalformedURLException, InterruptedException {
		AndroidDriver driver = BaseClass.openAndroidSettings();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		DeviceSettingsPage deviceSettings = new DeviceSettingsPage(driver);
		deviceSettings.verifyBluetooth(driver);
		deviceSettings.verifyWiFi();
		deviceSettings.captureDeviceDetails(driver);
		System.out.println("first  test");

	}
	
	@Test(priority=2, dependsOnMethods="deviceSettings", enabled=false)
	public void BPMeasureMobile() throws MalformedURLException
	{
		BaseClass.openMobileCC();
		
	}
	
	//Adding dependsOnMethods="deviceSettings" for now, as "BPMeasureMobile" is enabled "false" for now.
	@Test(priority=3, dependsOnMethods="deviceSettings")
	public void BPMeasureWeb() throws InterruptedException
	{
		WebDriver driver = BaseClass.startSeleniumDriver();
		WebCareConsolePage webccpage = new WebCareConsolePage(driver);
		driver.get(GlobalVariables.url);
		webccpage.login(GlobalVariables.user, GlobalVariables.password);
		
		webccpage.goToDashboard();
		webccpage.validateDevice();
		webccpage.validateBPReadings();
		driver.close();
	}
	


//Screenshot code to be added.
}
