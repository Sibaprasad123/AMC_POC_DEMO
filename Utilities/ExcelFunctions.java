package com.ifocus.automation.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelFunctions {

	// Generic method to return the number of rows in the sheet.
	public static int getRowCount(String xlPath, String sheet) {
		int rc = 0;
		try {
			FileInputStream fis = new FileInputStream(xlPath);
			Workbook wb = WorkbookFactory.create(fis);

			Sheet s = wb.getSheet(sheet);
			rc = s.getLastRowNum();
		} catch (Exception e) {
			
			System.out.println(e);
		}

		return rc;
	}

	// Generic method to return the column values in the sheet.
	public static String getCellValue(String xlPath, String sheet, int row, int col) {
		String data = "";
		try {
			FileInputStream fis = new FileInputStream(xlPath);
			Workbook wb = WorkbookFactory.create(fis);
			data = wb.getSheet(sheet).getRow(row).getCell(col).toString();

		}

		catch (Exception e) {
			System.out.println(e);
		}
		return data;

	}

	public static void writeData(String xlpath, int row, int col, String data) {

		try {

			File src = new File(xlpath);
			FileInputStream fis = new FileInputStream(src);
			XSSFWorkbook wb = new XSSFWorkbook(fis);

			XSSFSheet sh = wb.createSheet();
			sh.createRow(row).createCell(col).setCellValue(data);

			FileOutputStream fos = new FileOutputStream(src);
			wb.write(fos);
			wb.close();

		}

		catch (Exception e) {
			System.out.println(e);
		}

	}

}
