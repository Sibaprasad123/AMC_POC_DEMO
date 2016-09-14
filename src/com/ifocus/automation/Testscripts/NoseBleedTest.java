package com.ifocus.automation.Testscripts;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.ifocus.automation.GlobalRepository.GlobalVariables;
import com.ifocus.automation.Pages.MobileCareConsolePage;
import com.ifocus.automation.Pages.WebCareConsolePage;

public class NoseBleedTest {

	
	@Test(priority = 1, enabled = true)
	public void NoseBleedMobile() throws MalformedURLException, InterruptedException {
		
		MobileCareConsolePage.makeJournalEntry();
	}
	
	
	
	@Test(priority=2, dependsOnMethods="NoseBleedMobile", enabled=true)		
	public void NoseBleedWeb() throws InterruptedException
	{
		WebDriver driver = BaseClass.startSeleniumDriver();
		driver.get(GlobalVariables.url);
		
		WebCareConsolePage webccpage = new WebCareConsolePage(driver);		
		webccpage.login(GlobalVariables.user, GlobalVariables.password);		
		webccpage.validateNoseBleedEntry(driver);
		
	}
}
