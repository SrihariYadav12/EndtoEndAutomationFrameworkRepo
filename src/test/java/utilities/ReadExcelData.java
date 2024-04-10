package utilities;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ReadExcelData {
	public static FileInputStream fis;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;

	public static int getRowCount(String excelPath, String sheetName) throws Throwable {

		fis = new FileInputStream(excelPath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		int noofRows = sheet.getPhysicalNumberOfRows();
		workbook.close();
		fis.close();

		return noofRows;

	}

	public static int getColCount(String excelPath, String sheetName) throws Throwable {
		fis = new FileInputStream(excelPath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		int noOfCol = sheet.getRow(0).getLastCellNum();
		workbook.close();
		fis.close();

		return noOfCol;
	}

	@DataProvider(name = "LoginData")
	public static String[][] getData() throws Throwable {
		String excelPath = "./src/test/resources/TestData/Excel Worksheet.xlsx";
		int rowCount = getRowCount(excelPath, "Sheet1");
		int colCount = getColCount(excelPath, "Sheet1");

		String data[][] = new String[rowCount - 1][colCount];

		for (int i = 0; i < rowCount - 1; i++) {
			for (int j = 0; j < colCount; j++) {
				DataFormatter dt = new DataFormatter();
				data[i][j] = dt.formatCellValue(sheet.getRow(i + 1).getCell(j));

			}
		}

		return data;
	}
}
