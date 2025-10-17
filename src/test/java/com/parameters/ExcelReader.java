package com.parameters;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public String[] getCredentialsFromMyExcel(int sheetNo, int rowNo) throws IOException {

		FileInputStream fis = new FileInputStream("src/test/resources/ExcelData/CityCredentials.xlsx");

		XSSFWorkbook wb = new XSSFWorkbook(fis); // excel workbook

		XSSFSheet sheet = wb.getSheetAt(sheetNo);// sheet

		XSSFRow row = sheet.getRow(rowNo);

		XSSFCell cell1 = row.getCell(0);

		XSSFCell cell2 = row.getCell(1);

		String[] data = new String[2];

		data[0] = (cell1 != null) ? cell1.getStringCellValue() : "";
		data[1] = (cell2 != null) ? cell2.getStringCellValue() : "";

		wb.close();
		return data;

	}

}
