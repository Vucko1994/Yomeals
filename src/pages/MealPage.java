package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MealPage extends BasicPage {

	public MealPage(WebDriver driver, JavascriptExecutor js) {
		super(driver, js);
	}

	public WebElement getQuantity() {
		return driver.findElement(By.name("product_qty"));
	}

	public WebElement getAddToCartBtn() {
		return driver.findElement(By.xpath("//a[@class=\"btn btn--primary btn--large js-proceedtoAddInCart \"]"));
	}

	public void addMealToCart(String quantity) {
		getQuantity().sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		getQuantity().sendKeys(quantity);

		getAddToCartBtn().click();
	}

}
