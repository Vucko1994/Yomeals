package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MealItemTest extends BasicTest {

	@Test(priority = 1)
	public void addMealToCartTest() throws InterruptedException {
		driver.get(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		locationPopupPage.closeSelectLocation();

		mealPage.addMealToCart("3");
		Assert.assertTrue(notificationSistemPage.messageText().contains("The Following Errors Occurred:"));
		Assert.assertTrue(notificationSistemPage.messageText().contains("Please Select Location"));

		notificationSistemPage.waitMessageToDisappear();

		locationPopupPage.getSelectLocation().click();
		locationPopupPage.setLocation("City Center - Albany");
		Thread.sleep(1000);
		mealPage.addMealToCart("3");
		Assert.assertTrue(notificationSistemPage.messageText().contains("Meal Added To Cart"));
	}

	@Test(priority = 2)
	public void addMealToFavoriteTest() throws InterruptedException {
		driver.get(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		locationPopupPage.closeSelectLocation();

		mealPage.addMealToFavorite();
		Assert.assertTrue(notificationSistemPage.messageText().contains("Please login first!"));

		driver.get(baseUrl + "guest-user/login-form");
		loginPage.userLogin(userEmail, password);
		driver.get(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");

		mealPage.addMealToFavorite();
		Thread.sleep(700);
		Assert.assertTrue(notificationSistemPage.messageText().contains("Product has been added to your favorites."));
	}

	@Test(priority = 3)
	public void clearCartTest() throws IOException {
		driver.get(baseUrl + "meals");
		locationPopupPage.setLocation("City Center - Albany");

		File file = new File("data/data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Meals");

		for (int i = 1; i <= 5; i++) {
			DataFormatter formatter = new DataFormatter();

			String mealUrl = sheet.getRow(i).getCell(0).getStringCellValue();
			String qty = formatter.formatCellValue(sheet.getRow(i).getCell(1));

			driver.get(mealUrl);
			mealPage.addMealToCart(qty);

			SoftAssert sa = new SoftAssert();

			sa.assertTrue(notificationSistemPage.messageText().contains("Meal Added To Cart"));
			sa.assertAll();
		}

		cartSummaryPage.clearAll();
		Assert.assertTrue(notificationSistemPage.messageText().contains("All meals removed from Cart successfully"));

	}

}
