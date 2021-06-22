package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotificationSistemPage extends BasicPage {

	public NotificationSistemPage(WebDriver driver, JavascriptExecutor js) {
		super(driver, js);
	}

	public WebElement getMessage() {
		return driver.findElement(By.xpath(
				"//*[contains(@class, 'alert--success') or contains(@class, 'alert--danger')][contains(@style,'display: block')]"));
	}

	public String messageText() {
		return getMessage().getText();
	}

	public void waitMessageToDisappear() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement Message = driver.findElement(By.xpath("//*[contains(@class, 'system_message')]"));

		wait.until(ExpectedConditions.attributeToBe(Message, "style", "display: none;"));
	}

}
