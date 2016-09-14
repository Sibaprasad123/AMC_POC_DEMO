package com.ifocus.automation.Pages;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ifocus.automation.GlobalRepository.GlobalVariables;
import io.appium.java_client.android.AndroidDriver;

public class DeviceSettingsPage {

	// Bluetooth
	@FindBy(xpath = "//android.widget.TextView[@text='Bluetooth']")
	public WebElement BTBtn;

	@FindBy(xpath = "//android.widget.Switch")
	public WebElement BTText;

	@FindBy(xpath = "//android.widget.TextView[contains(@resource-id,'android:id/title')]")
	public List<WebElement> BTName;

	@FindBy(name = "Bluetooth, Navigate up")
	public WebElement navigateBack;

	@FindBy(xpath = "//android.widget.Switch[@text='OFF']")
	public WebElement BTOff;

	@FindBy(id = "android:id/checkbox")
	public WebElement BT;

	@FindBy(xpath = "//android.widget.LinearLayout[@index='3']")
	public WebElement scanBtn;

	@FindBy(xpath = "//android.widget.LinearLayout[@index='3']")
	public WebElement scannedDevice;

	// Wi-Fi
	@FindBy(xpath = "//android.widget.TextView[@text='Wi-Fi']")
	public WebElement wifiBtn;

	@FindBy(xpath = "//android.widget.Switch")
	public WebElement wifiText;

	@FindBy(name = "Wi-Fi, Navigate up")
	public WebElement navigateUp;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'Connected')]")
	public WebElement wifiConnection;

	@FindBy(xpath = "//android.widget.Switch[@text='OFF']")
	public WebElement wifiOff;

	// Device Details

	@FindBy(xpath = "//android.widget.TextView[@text='About device']")
	public WebElement aboutDeviceBtn;

	@FindBy(name = "SCH-I605")
	public WebElement model;

	@FindBy(name = "4.4.2")
	public WebElement platform;

	public DeviceSettingsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// BT function
	public void verifyBluetooth(AndroidDriver driver) throws InterruptedException, MalformedURLException {
		BTBtn.click();
		Thread.sleep(2000);

		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		String bluetooth = BTText.getText();
		if (bluetooth.equalsIgnoreCase("ON")) {

			System.out.println("\nBluetooth is ON!");

			for (int i = 0; i < 4; i++) {
				WebElement element = BTName.get(i);
				System.out.println(element.getText());
			}

			Thread.sleep(2000);
			navigateBack.click();
		} else if (bluetooth.equalsIgnoreCase("OFF")) {
			BTOff.click();
			BT.click();
			Thread.sleep(2000);
			scanBtn.click();

			Thread.sleep(2000);
			scannedDevice.click();
			Thread.sleep(2000);

			System.out.println("Bluetooth is turned ON!");
			Thread.sleep(2000);
			navigateBack.click();

		}

	}

	// Wi-Fi Function

	public void verifyWiFi() throws InterruptedException {
		wifiBtn.click();
		Thread.sleep(2000);

		String wifi = wifiText.getText();
		if (wifi.equalsIgnoreCase("ON")) {
			Thread.sleep(2000);

			navigateUp.click();

			String connection = wifiConnection.getText();
			if (connection.contains("Connected")) {
				System.out.println("\nWi-Fi is ON and Connected!");
			}
		}

		else if (wifi.equalsIgnoreCase("OFF")) {
			wifiOff.click();
			navigateUp.click();
			String connection1 = wifiConnection.getText();

			if (connection1.contains("Connected")) {

				System.out.println("\nWi-Fi is turned ON and Connected!");
			} else {

				System.out.println("\nWI-FI Connectivity Failed!");
			}
		}

	}

	// Device details
	public void captureDeviceDetails(AndroidDriver driver) throws InterruptedException {
		driver.scrollToExact("About device");
		aboutDeviceBtn.click();
		GlobalVariables.model = model.getText();
		GlobalVariables.platform = platform.getText();

		System.out.println("\nDevice Model: " + GlobalVariables.model);
		System.out.println("\nDevice Android Version: " + GlobalVariables.platform);

		driver.quit();
		Thread.sleep(4000);

	}

}
