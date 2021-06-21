package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultPage extends BasicPage {

	public SearchResultPage(WebDriver driver, JavascriptExecutor js) {
		super(driver, js);
	}

	public List<WebElement> getAllResults() {
		return driver.findElements(By.xpath("//*[@class='product-name']/a"));
	}

	public ArrayList<String> getAllResultsNames() {
		ArrayList<String> names = new ArrayList<>();
		for (int i = 0; i < getAllResults().size(); i++) {
			String name = getAllResults().get(i).getText();
			names.add(name);
		}
		return names;
	}

	public int getResultsNumber() {
		return getAllResults().size();
	}

}
