package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AuthPage extends BasicPage {

	public AuthPage(WebDriver driver, JavascriptExecutor js) {
		super(driver, js);
	}

	public WebElement getDropDownBtn() {
		return driver.findElement(By.xpath("//*[@class=\"filled \"]/a"));
	}
	
	public WebElement getMyAccount() {
		 return driver.findElement(By.xpath("//*[@class=\"my-account-dropdown\"]/ul/li[1]"));
	}
	
	public WebElement getLogout() {
		return driver.findElement(By.xpath("//*[@class=\"my-account-dropdown\"]/ul/li[2]"));
	}
	
	public void userLogout() {
		getDropDownBtn().click();
		
		getLogout().click();
	}

}
