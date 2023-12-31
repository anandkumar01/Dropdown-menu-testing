package testing;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcelData {
	
	public static void excelDataWrite(String selCountry) throws IOException {
		
		//reading excel file from the system.
		String filePath = System.getProperty("user.dir") + "\\testdata\\dataset.xlsx";
		FileInputStream file = new FileInputStream(filePath);
		
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row = sheet.getRow(1);
		row.getCell(7).setCellValue(selCountry);		// writing selected country in excel sheet.
		
		if(selCountry.equals(ReadExcelData.actCountry)) {	// checking actual and expected country to write validation in excel sheet.
			row.getCell(8).setCellValue("Valid");
		} else {
			row.getCell(8).setCellValue("Invalid");
		}
		
		FileOutputStream file1 = new FileOutputStream(filePath);
		workbook.write(file1);		// writing all values in excel sheet.
		file1.close();				// closing file.
		workbook.close();			// closing workbook.
	}
}
