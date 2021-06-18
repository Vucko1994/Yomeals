package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasicPage {

	public LoginPage(WebDriver driver, JavascriptExecutor js) {
		super(driver, js);
	}

	public WebElement getLoginBtn() {
		return driver.findElement(By.xpath("//*[@class=\"filled\"]/a"));
	}

	public WebElement getUsername() {
		return driver.findElement(By.name("username"));
	}

	public WebElement getPassword() {
		return driver.findElement(By.name("password"));
	}

	public WebElement getRememberMeCheckbox() {
		return driver.findElement(By.name("remember_me"));
	}

	public WebElement getSubmitBtn() {
		return driver.findElement(By.name("btn_submit"));
	}

	public void userLogin(String username, String password) {

		getUsername().clear();
		getPassword().clear();

		getUsername().sendKeys(username);
		getPassword().sendKeys(password);

		getSubmitBtn().click();
	}

}
