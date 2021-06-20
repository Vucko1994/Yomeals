package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProfilePage extends BasicPage {

	public ProfilePage(WebDriver driver, JavascriptExecutor js) {
		super(driver, js);
	}

	public WebElement getEditProfileBtn() {
		return driver.findElement(By.partialLinkText("Edit Profile"));
	}

	public WebElement getFirstName() {
		return driver.findElement(By.name("user_first_name"));
	}

	public WebElement getLastName() {
		return driver.findElement(By.name("user_last_name"));
	}

	public WebElement getEmail() {
		return driver.findElement(By.name("user_email"));
	}

	public WebElement getAddress() {
		return driver.findElement(By.name("user_address"));
	}

	public WebElement getPhone() {
		return driver.findElement(By.name("user_phone"));
	}

	public WebElement getZipCode() {
		return driver.findElement(By.name("user_zip"));
	}

	public WebElement getSaveBtn() {
		return driver.findElement(By.name("btn_submit"));
	}

	public WebElement getPhotoUploadBtn() {
		return driver.findElement(By.xpath("//a[@class=\"upload uploadFile-Js\"]"));
	}

	public WebElement getPhotoRemoveBtn() {
		return driver.findElement(By.className("remove"));
	}

	public Select getCountry() {
		Select countrySelect = new Select(driver.findElement(By.name("user_country_id")));
		return countrySelect;
	}

	public Select getState() {
		Select stateSelect = new Select(driver.findElement(By.name("user_state_id")));
		return stateSelect;
	}

	public Select getCity() {
		Select citySelect = new Select(driver.findElement(By.name("user_city")));
		return citySelect;
	}

	public void selectCountry(String countryVisibleText) {
		getCountry().selectByVisibleText(countryVisibleText);
	}

	public void selectState(String stateVisibleText) {
		getState().selectByVisibleText(stateVisibleText);
	}

	public void selectCity(String cityVisibleText) {
		getCity().selectByVisibleText(cityVisibleText);
	}

	public void uploadPhoto(String imagePath) {
		js.executeScript("arguments[0].click()", getPhotoUploadBtn());

		driver.findElement(By.xpath("//input[@type=\"file\"]")).sendKeys(imagePath);
	}

	public void removePhoto() {
		js.executeScript("arguments[0].click()", getPhotoRemoveBtn());
	}

	public void editAllInfo(String firstName, String lastName, String address, String phone,
			String zipCode, String country, String state, String city) {
		getFirstName().clear();
		getLastName().clear();
		getAddress().clear();
		getPhone().clear();
		getZipCode().clear();
				
		getFirstName().sendKeys(firstName);
		getLastName().sendKeys(lastName);
		getAddress().sendKeys(address);
		getPhone().sendKeys(phone);
		getZipCode().sendKeys(zipCode);
		
		selectCountry(country);
		selectState(state);
		selectCity(city);
		
		getSaveBtn().click();
	}

}
