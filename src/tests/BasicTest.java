package tests;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import pages.AuthPage;
import pages.BasicPage;
import pages.CartSummaryPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSistemPage;
import pages.ProfilePage;
import pages.SearchResultPage;

public abstract class BasicTest {

	protected String baseUrl = "http://demo.yo-meals.com/";
	protected String userEmail = "customer@dummyid.com";
	protected String password = "12345678a";
	protected WebDriver driver;
	protected LocationPopupPage locationPopupPage;
	protected BasicPage basicPage;
	protected LoginPage loginPage;
	protected NotificationSistemPage notificationSistemPage;
	protected ProfilePage profilePage;
	protected AuthPage authPage;
	protected MealPage mealPage;
	protected CartSummaryPage cartSummaryPage;
	protected SearchResultPage searchResultPage;
	protected JavascriptExecutor js = (JavascriptExecutor) driver;

	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		js = (JavascriptExecutor) driver;


		locationPopupPage = new LocationPopupPage(driver, js);
		loginPage = new LoginPage(driver, js);
		notificationSistemPage = new NotificationSistemPage(driver, js);
		profilePage = new ProfilePage(driver, js);
		authPage = new AuthPage(driver, js);
		mealPage = new MealPage(driver, js);
		cartSummaryPage = new CartSummaryPage(driver, js);
		searchResultPage = new SearchResultPage(driver, js);

	}

	@AfterMethod
	public void screenshotAndCleanUp(ITestResult testResult) throws IOException, InterruptedException {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
			LocalDateTime now = LocalDateTime.now();
			String dt = dtf.format(now);
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("./Screenshots/" + dt + ".jpg"));
		}
		Thread.sleep(700);
		driver.quit();
	}

}
