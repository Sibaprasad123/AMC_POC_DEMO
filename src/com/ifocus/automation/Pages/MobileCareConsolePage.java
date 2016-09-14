package com.ifocus.automation.Pages;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ifocus.automation.GlobalRepository.GlobalVariables;
import com.ifocus.automation.Testscripts.BaseClass;
import com.ifocus.automation.Utilities.ExcelFunctions;
import com.ifocus.automation.Utilities.FrameworkUtilities;


public class MobileCareConsolePage {
	
	@FindBy(xpath = "android.view.View[@content-desc='Continue']")
	public WebElement continueBtn;
	
	
	@FindBy(xpath = "android.view.View[@content-desc='Start Over']")
	public WebElement startOverBtn;
	
	//public WebElement selectDate1;
	
	public MobileCareConsolePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
		
	//NoseBleed Journal Entry function
	public static void makeJournalEntry() throws InterruptedException, MalformedURLException
	{
		WebDriver driver = BaseClass.openMobileCC();
		System.out.println("\n**NOSEBLEED ENTRY EXECUTION STARTED ON MOBILE**\n");
		
		String xlPath = ("C:/AMCAppiumTestData.xlsx");
		String sheet = "Details";
		
		Thread.sleep(1000);
		
		String entry = ExcelFunctions.getCellValue(xlPath, sheet, 1, 0);
		FrameworkUtilities.select(entry, driver);
		
		/*try{		
		String startOver = ExcelFunctions.getCellValue(xlPath, sheet, 1, 6);		
		SelectOption.select(startOver);	
		
		}
		catch(Exception e)
		{
			
			System.out.println("\nStart Over Button is not displayed!\n");
		}
		*/
		String selectedDate = ExcelFunctions.getCellValue(xlPath, sheet, 1, 1);		
		WebElement selectDate = FrameworkUtilities.returnElement(selectedDate, driver);		
		
		if(selectDate.isDisplayed())
		{
			
			GlobalVariables.diaryDate=(FrameworkUtilities.setGlobalVariable(selectedDate, GlobalVariables.diaryDate));			
			//System.out.println("Global Diarydate value in mobile app: "+GlobalVariables.diaryDate);
			FrameworkUtilities.select(selectedDate, driver);				
			
			String selectedTime = ExcelFunctions.getCellValue(xlPath, sheet, 1, 2);
			GlobalVariables.time=(FrameworkUtilities.setGlobalVariable(selectedTime, GlobalVariables.time));			
			//System.out.println("Global Time value in mobile app: "+GlobalVariables.time);		
			FrameworkUtilities.select(selectedTime, driver);			
						
			String selectOption = ExcelFunctions.getCellValue(xlPath, sheet, 1, 3);
			GlobalVariables.yesNo=(FrameworkUtilities.setGlobalVariable(selectOption, GlobalVariables.yesNo));			
			//System.out.println("Global YesNo value in mobile app: "+GlobalVariables.yesNo);
			FrameworkUtilities.select(selectOption, driver);	
		 			
			String selectDuration = ExcelFunctions.getCellValue(xlPath, sheet, 1, 4);
			GlobalVariables.duration=(FrameworkUtilities.setGlobalVariable(selectDuration, GlobalVariables.duration));			
			//System.out.println("Global Duration value in mobile app: "+GlobalVariables.duration);
			FrameworkUtilities.select(selectDuration, driver);	
			
	
			String selectIntensity = ExcelFunctions.getCellValue(xlPath, sheet, 1, 5);
			GlobalVariables.intensity=(FrameworkUtilities.setGlobalVariable(selectIntensity, GlobalVariables.intensity));			
			//System.out.println("Global Intensity value in mobile app: "+GlobalVariables.intensity);
			FrameworkUtilities.select(selectIntensity, driver);	
			
			String continueBtn = ExcelFunctions.getCellValue(xlPath, sheet, 1, 7);
			FrameworkUtilities.select(continueBtn, driver);	
			
			String OkBtn = ExcelFunctions.getCellValue(xlPath, sheet, 1, 8);
			FrameworkUtilities.select(OkBtn, driver);			
			 
			GlobalVariables.subdate  = formatDate();
			
			//System.out.println("Global Subdate value in mobile app: "+GlobalVariables.subdate);
		}		
				
	}
	

	public static String formatDate()
	{

		 SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy");
		 Calendar cal = Calendar.getInstance();
		 String sysdate = dateFormat.format(cal.getTime());
		 
		 String dd = sysdate.substring(4, 6);
		 String ddnew = dd.concat(",");
		 
				 
		 String formatDate = sysdate.replace(dd, ddnew);
		 return(formatDate.substring(0, 12));
	}
	


}
