package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LocationPopupPage extends BasicPage {

	public LocationPopupPage(WebDriver driver, JavascriptExecutor js) {
		super(driver, js);
	}

	public WebElement getSelectLocation() {
		return driver.findElement(By.xpath("//div[@class=\"location-selector\"]/a"));
	}

	public WebElement getSelectLocationClose() {
		return driver.findElement(By.xpath("//div[@class=\"model-box-mid location-search\"]/a"));
	}

	public WebElement getKeyword() {
		return driver.findElement(By.xpath("//*[@id='locality_keyword']"));
	}

	public WebElement getLocationItem(String locationName) {
		return driver.findElement(By.xpath("//li/a[contains(text(), '" + locationName + "')]/.."));
	}

	public WebElement getLocationInput() {
		return driver.findElement(By.xpath("//*[@id='location_id']"));
	}

	public WebElement getSubmit() {
		return driver.findElement(By.xpath("//*[@name='btn_submit']"));
	}

	public void openLocationPopup() {
		getSelectLocation().click();
	}

	public void setLocation(String locationName) {
		getSelectLocation().click();

		getKeyword().click();

		String dataValue = getLocationItem(locationName).getAttribute("data-value");

		js.executeScript("arguments[0].value=arguments[1]", getLocationInput(), dataValue);
		js.executeScript("arguments[0].click();", getSubmit());

	}

	public void closeSelectLocation() {
		getSelectLocationClose().click();
	}

}
