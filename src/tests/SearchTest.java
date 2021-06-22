package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BasicTest {
	
	@Test
	public void searchResultsTest() throws IOException, InterruptedException {
		driver.get(baseUrl + "meals");
		locationPopupPage.setLocation("City Center - Albany");
		
		File file = new File("data/data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Meal Search Results");
		
		for (int i = 1; i <= 6; i++) {
			String url = sheet.getRow(i).getCell(1).getStringCellValue();
			String location = sheet.getRow(i).getCell(0).getStringCellValue();
			int numberOfResultsExp = (int) sheet.getRow(i).getCell(2).getNumericCellValue();
			
			locationPopupPage.getSelectLocation().click();
			locationPopupPage.setLocation(location);
			Thread.sleep(700);
			driver.get(url);
			
			Assert.assertEquals(searchResultPage.getResultsNumber(), numberOfResultsExp, "Number of results is not correct.");
			
			for (int j = 0; j < numberOfResultsExp; j++) {
				int cellNumber = 3 + j;
				String expName = sheet.getRow(i).getCell(cellNumber).getStringCellValue();
				
				String actName = searchResultPage.getAllResultsNames().get(j);
				
				Assert.assertTrue(actName.contains(expName), "Name is not correct.");
			}
		}
		
	}
}
