package com.ifocus.automation.Utilities;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class FrameworkUtilities {
	
	public static WebDriver driver;	
	
	//WEB 
	public static void scrollTo(WebElement element)
	{
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
		
	}
	public static boolean verifyData(String actual,String expected)
	{
		boolean data=false;
		if(actual.equalsIgnoreCase(expected))
		{
			data=true;
		}
		return data;
	}
	public static boolean verifyData(WebElement actual,WebElement expected)
	{
		boolean data=false;
		if(actual.equals(expected))
		{
			data=true;
		}
		return data;
	}
	
	//MOBILE
	public static void select(String elementdesc, WebDriver driver) throws InterruptedException, MalformedURLException {
		
		
		System.out.println("\nWaiting for element: " + elementdesc);
		Thread.sleep(1000);
		WebElement element = driver.findElement(By.name(elementdesc));
		try {
			if (element.isDisplayed()) {				
				element.click();

				//logger.info("Selected: " + elementdesc);
				System.out.println("Selected: " + elementdesc);

			}
		} catch (Exception e) {
		//	logger.info("EXCEPTION CAUGHT");
			System.out.println("EXCEPTION CAUGHT");
			e.printStackTrace();
		}

	}

	public static WebElement returnElement(String elementDesc, WebDriver driver) {
		
		WebElement element = driver.findElement(By.name(elementDesc));
		return element;
	}

	public static String setGlobalVariable(String elementDesc, String globalVariable) {

		globalVariable = elementDesc;
		return globalVariable;

	}

	
	

}
