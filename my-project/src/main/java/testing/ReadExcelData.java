package testing;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData {
	public static String name, email, password, month1, actCountry;
	public static int day1, year1;
	
	public static void excelDataRead() throws IOException {
		
		//reading excel file from the system.
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\dataset.xlsx";
		FileInputStream file = new FileInputStream(filePath);
		
		XSSFWorkbook workbook = new XSSFWorkbook(file);		// creating a workbook instance for the file.
		XSSFSheet sheet = workbook.getSheet("Sheet1");		// accessing the sheet.
		XSSFRow row = sheet.getRow(1);						// reading data from first row.
		
		// storing all values from excel sheet to read on webpage.
		name = row.getCell(0).getStringCellValue();
		email = row.getCell(1).getStringCellValue();
		password = row.getCell(2).getStringCellValue();
		day1 = (int) row.getCell(3).getNumericCellValue();
		month1 = row.getCell(4).getStringCellValue();
		year1 = (int) row.getCell(5).getNumericCellValue();
		actCountry = row.getCell(6).getStringCellValue();
		
		workbook.close(); 	// closing workbook.
	}
}
