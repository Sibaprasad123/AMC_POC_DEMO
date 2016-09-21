package com.ifocus.automation.Pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ifocus.automation.GlobalRepository.GlobalVariables;
import com.ifocus.automation.Utilities.FrameworkUtilities;

public class WebCareConsolePage {

	// Login
	@FindBy(xpath = "//input[contains(@placeholder,'Username')]")
	public WebElement user;

	@FindBy(xpath = "//input[contains(@placeholder,'Password')]")
	public WebElement pass;

	@FindBy(xpath = "//button[contains(.,'Login')]")
	public WebElement lbutton;

	// Enroll Patient
	@FindBy(xpath = "//span[contains(.,'Enroll Patient')]")
	public WebElement enroll;

	@FindBy(xpath = ".//*[@id='customer']/div/span")
	public WebElement customerTb;

	@FindBy(xpath = ".//*[@id='ui-select-choices-row-0-1']/a/span")
	public WebElement customer;

	@FindBy(xpath = ".//*[@id='division']/div/span")
	public WebElement divisionTb;

	@FindBy(xpath = ".//*[@id='ui-select-choices-row-1-0']/a")
	public WebElement division;

	@FindBy(xpath = ".//*[@id='program']/div/span")
	public WebElement programTb;

	@FindBy(xpath = "//span[contains(.,'Nosebleed Trial')]")
	public WebElement program;

	@FindBy(xpath = "//button[@ng-show='showNext' and contains(.,'Next')]")
	public WebElement nextBtn1;

	@FindBy(xpath = ".//*[@id='patientId']")
	public WebElement patientId;

	@FindBy(xpath = "//button[@ng-show='showNext' and contains(.,'Next')]")
	public WebElement nextBtn2;

	@FindBy(xpath = "(.//*[@class='patientTabs ng-scope'])/div[3]")
	public WebElement settings;

	@FindBy(xpath = "//button[@ng-click='openDeviceRegisterModal()' and contains(.,'Register Device')]")
	public WebElement registerDevice;

	@FindBy(xpath = "//input[@class='form-control ng-pristine ng-untouched ng-valid']")
	public WebElement regCodeTb;

	// Search Patient
	@FindBy(xpath = "//input[contains(@placeholder,'Search')]")
	public WebElement searchPatientBtn;

	@FindBy(xpath = ".//div[@class='ng-isolate-scope']/div/div")
	public List<WebElement> patientList;

	@FindBy(xpath = ".//*[@id='main-tab']")
	public WebElement patientTab;

	@FindBy(xpath = "(.//*[@class='patientTabs ng-scope'])/div[1]")
	public WebElement dashboard;

	// Measure BloodPressure Web
	@FindBy(xpath = ".//*[@class='panel-body dashboardWidget']//button[contains(.,'Deregister')]")
	public WebElement button;

	@FindBy(xpath = "(.//*[@class='panel-body dashboardWidget'])/div[2]/li[1]/ul/li[1]")
	public WebElement platform;

	@FindBy(xpath = "(.//*[@class='panel-body dashboardWidget'])/div[2]/li[1]/ul/li[2]")
	public WebElement model;

	// Validate BP Readings
	@FindBy(xpath = "//div[@class='ui-grid-contents-wrapper']/div/div[2]")
	public List<WebElement> bpreadings;

	// Nosebleed Entry

	@FindBy(xpath = "//span[contains(.,'NoseBleed Assessment')]")
	public WebElement nosebleedSection;

	@FindBy(xpath = "//div[@class='ng-binding']")
	public List<WebElement> dates;

	@FindBy(xpath = "//div[@class='btn btn-default ng-binding ng-scope']")
	public List<WebElement> views;

	@FindBy(xpath = "html/body/div[3]/div/div/div/div[2]/div/div[2]/div[2]/div[1]/div/div")
	public WebElement diarydate;

	@FindBy(xpath = ".//label")
	public List<WebElement> actualInputslist;
	
	@FindBy(xpath = ".//*[@id='sitesDropdown']")
	public WebElement sitesDD;
	
	@FindBy(xpath = "(//div[@class='ng-scope']/li[1]/div[1])[1]")
	public WebElement mayoNoseBleed;
	
	@FindBy(xpath = "//div[@class='clearfix']//button[contains(.,'Continue')]")
	public WebElement continueBtn;
	
	
	
	
	public WebCareConsolePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void login(String username, String password) throws InterruptedException {
		Thread.sleep(2000);
		user.sendKeys(username);
		pass.sendKeys(password);
		lbutton.click();
		Thread.sleep(2000);
		System.out.println("\nLogged into application successfully\n");
	}

	public void enrollPatient() throws InterruptedException {

		enroll.click();
		Thread.sleep(2000);
		customerTb.click();
		Thread.sleep(2000);
		customer.click();
		divisionTb.click();
		Thread.sleep(1000);
		division.click();
		programTb.click();
		Thread.sleep(1000);
		program.click();
		nextBtn1.click();

		Thread.sleep(2000);
		String Id = random();
		patientId.sendKeys(Id);
		Thread.sleep(1000);

		nextBtn2.click();
		Thread.sleep(4000);
		settings.click();
		Thread.sleep(1000);
		registerDevice.click();
		// System.out.println("Registration code: "+regCodeTb.getText());
	}

	public void goToDashboard() throws InterruptedException {
		//Code added for new application
		sitesDD.click();
		mayoNoseBleed.click();
		Thread.sleep(2000);
		continueBtn.click();
		
		searchPatientBtn.sendKeys(GlobalVariables.patient);
		Thread.sleep(2000);
		searchPatientBtn.sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		patientList.get(0).click();
		Thread.sleep(2000);
		patientTab.click();

		/* Thread.sleep(30000); */
		System.out.println(GlobalVariables.patient + " details loaded successfully!\n");
		Thread.sleep(3000);
	}

	public void validateDevice() {
		/*
		 * BasicConfigurator.configure(); logger =
		 * Logger.getLogger(VerifyBluetoothWifi.class);
		 */

		settings.click();
		if (button.isDisplayed()) {

			String devicePlatform = platform.getText();
			String platformArr[] = devicePlatform.split(" ");

			boolean result = FrameworkUtilities.verifyData(platformArr[2], GlobalVariables.platform);

			System.out.println("Verifying Device Details\n");

			System.out.println("Platform actual value : " + platformArr[2]);
			System.out.println("Platform expected value : " + GlobalVariables.platform);
			Assert.assertEquals(result, true);

			String model1 = model.getText();
			String modelArr[] = model1.split(": ");
			boolean result1 = FrameworkUtilities.verifyData(modelArr[1], GlobalVariables.model);

			System.out.println("\nModel actual value : " + modelArr[1]);
			System.out.println("Model expected value : " + GlobalVariables.model);
			Assert.assertEquals(result1, true);

			/*
			 * String serialNum = serialNo.getText(); String serialNumArr[] =
			 * serialNum.split(": "); boolean
			 * result2=verifyData(serialNumArr[1],GlobalVariables.serialNo);
			 * System.out.println("Serial Number actual value : "+serialNumArr[1
			 * ]); System.out.println("Serial Number expected value : "
			 * +GlobalVariables.serialNo); Assert.assertEquals(result2,true);
			 */

			dashboard.click();

		}
	}

	public void validateBPReadings() throws InterruptedException {
		System.out.println("\nFetching Blood Pressure measure readings.. ");
		Thread.sleep(30000);

		String reading = bpreadings.get(0).getText();

		String r[] = reading.split("\\+");
		String actualInput[] = new String[5];
		ArrayList<String> bpactual = new ArrayList<String>();
		for (int k = 0; k < r.length; k++) {
			bpactual.add(r[k].replaceAll("\\n", ";"));
			actualInput = bpactual.get(k).split(";");
		}

		String inputs[] = { "Sep 14, 2016", "11:11:29 AM", "97/58", "66", "75" };
		actualInput = bpactual.get(0).split(";");

		System.out.println("\nExpected Date of Submission: " + inputs[0] + " -->  ");
		System.out.println("Actual Date of Submission: " + actualInput[0]);

		System.out.println("\nExpected Time of Submission: " + inputs[1] + " -->  ");
		System.out.println("Actual Time of Submission: " + actualInput[1]);

		System.out.println("\nExpected value of SYS: " + inputs[2] + " -->  ");
		System.out.println("Actual value of SYS: " + actualInput[2]);

		System.out.println("\nExpected value of MAP: " + inputs[3] + " -->  ");
		System.out.println("Actual value of MAP: " + actualInput[3]);

		System.out.println("\nExpected value of PUL: " + inputs[4] + " -->  ");
		System.out.println("Actual value of PUL: " + actualInput[4]);

		Assert.assertEquals(actualInput, inputs);

		System.out.println("\nBLOOD PRESSURE READINGS MATCH!");

	}

	public void validateNoseBleedEntry(WebDriver driver) throws InterruptedException {
		System.out.println("\nLAUNCHED WEB APPLICATION FOR NOSEBLEED VALIDATION\n");
		Thread.sleep(7000);

		goToDashboard();
		Thread.sleep(60000);

		Actions act = new Actions(driver);
		act.moveToElement(nosebleedSection).perform();
		Thread.sleep(4000);

		System.out.println("Submission Date is: "+GlobalVariables.subdate+"\n");
		if (dates.get(0).getText().equals(GlobalVariables.subdate)) {

			Thread.sleep(2000);
			views.get(0).click();

			Thread.sleep(4000);
			String s = diarydate.getText();
			String splitoffinput[] = s.split("\n");

			/*
			 * logger.info("Expected Submission date:" +
			 * GlobalVariables.diaryDate); logger.info("Actual Submission date:"
			 * + splitoffinput[1]+"\n");
			 */
			
		/*	System.out.println("Expected Submission date:" + GlobalVariables.diaryDate);
			System.out.println("Actual Submission date:" + splitoffinput[1] + "\n");
*/
			/*
			 * logger.info("Diarydate passed:" + GlobalVariables.diaryDate);
			 * logger.info("Diarydate displayed:" + splitoffinput[1]);
			 */

			if ((splitoffinput[1]).equalsIgnoreCase(GlobalVariables.diaryDate)) {

				verifyEntry(driver);

			}

			else {

				Assert.assertFalse(true, "\nNo match: Given NoseBleed Entry is Not submitted to the Web\n");

			}

		} else {
			Assert.assertFalse(true, "\nNo match: Given NoseBleed Entry is Not submitted to the Web\n");

		}

		/* logger.info("\nNoseBleed Test ends here\n"); */
		/*System.out.println("\nNoseBleed Test ends here\n");*/

		// consolepage.logout();
		/* logger.info("Logged out of application\n"); */
		/*System.out.println("Logged out of application\n");*/

		/* logger.info("**NOSEBLEED ENTRY EXECUTION ENDS HERE**\n"); */
		System.out.println("**NOSEBLEED ENTRY EXECUTION ENDS HERE**\n");

	}

	// Function called within validateNoseBleedEntry
	public void verifyEntry(WebDriver driver) {
		Assert.assertTrue(
				driver.findElement(
						By.xpath("//div[contains(.,'Diary date:') and (contains (.,'" + GlobalVariables.diaryDate.trim()
								+ "'))]//following-sibling::*[contains(@checked,'checked')]"))
						.isDisplayed(),
				"Sub date is not matching\n");

		System.out.println("Verified Diary date " + GlobalVariables.diaryDate + " and it is matching\n");
		ArrayList<String> inputs = new ArrayList<String>(Arrays.asList(GlobalVariables.time, GlobalVariables.yesNo,
				GlobalVariables.duration, GlobalVariables.intensity));
		ArrayList<String> actualInputs = new ArrayList<>();

		for (WebElement inputText : actualInputslist) {
			if (inputText.isDisplayed()) {
				actualInputs.add(inputText.getText());
			}
		}

		for (int k = 0; k < actualInputs.size(); k++) {
			boolean selected = VerifyCheckbox("//div[@ng-show='!option.hidden']/label[(.='" + actualInputs.get(k).trim()
					+ "')]//preceding-sibling::input", driver);

			boolean elementPresent = verifyExpectedResults(inputs, actualInputs.get(k).trim());
			if (selected) {

				Assert.assertTrue(elementPresent, actualInputs.get(k));
				System.out.println("Verified  " + actualInputs.get(k) + " and it is matching\n");

			} else {
				Assert.assertFalse(elementPresent);

			}
		}
	}

	// Function called within validateNoseBleedEntry --> verifyEntry
	public boolean verifyExpectedResults(ArrayList<String> inputs, String value) {
		boolean found = false;
		for (int i = 0; i < inputs.size(); i++) {

			if (inputs.get(i).trim().equals(value.trim())) {
				found = true;
			}
		}
		return found;
	}

	// Function called within validateNoseBleedEntry --> verifyEntry
	public boolean VerifyCheckbox(String xpath, WebDriver driver) {
		boolean isChecked = driver.findElement(By.xpath(xpath)).isSelected();
		return isChecked;

	}

	public String random() {
		Random rand = new Random();
		int x = rand.nextInt(10000);
		String patientId = Integer.toString(x);
		System.out.println("random patient id: " + patientId);
		GlobalVariables.patientId = patientId;
		return patientId;
	}

}
