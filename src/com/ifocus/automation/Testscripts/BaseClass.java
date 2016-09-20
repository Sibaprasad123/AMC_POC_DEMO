package com.ifocus.automation.Testscripts;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.android.AndroidDriver;

public class BaseClass {
	
	public static WebDriver startSeleniumDriver()
	{
		System.setProperty("webdriver.chrome.driver",
				" AMC_POC_DEMO/common%20files/chromedriver.exe ");
		WebDriver driver = new ChromeDriver();
		return driver;
	}

	public static AndroidDriver openAndroidSettings() throws MalformedURLException
	{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("BROWSER_NAME", "Android");
		capabilities.setCapability("VERSION", "4.4.2");
		capabilities.setCapability("deviceName", "Emulator");
		capabilities.setCapability("platformName", "Android");

		capabilities.setCapability("appPackage", "com.android.settings");
		capabilities.setCapability("appActivity", "com.android.settings.Settings");

		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		System.out.println("\nDevice connected successfully!\n");
		return driver;
	}
	
	
	public static WebDriver openMobileCC() throws MalformedURLException
	{

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("BROWSER_NAME", "Android");
		capabilities.setCapability("VERSION", "4.4.2");
		capabilities.setCapability("deviceName", "Emulator");
		capabilities.setCapability("platformName", "Android");

		capabilities.setCapability("appPackage", "com.amchealth.careconsole.mobile");
		capabilities.setCapability("appActivity", "com.amchealth.careconsole.mobile.CareConsole");

		WebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		System.out.println("\nCare console app launched successfully on device!\n");
		return driver;

	}
	
}
