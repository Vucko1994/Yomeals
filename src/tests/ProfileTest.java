package tests;

import org.testng.annotations.Test;

public class ProfileTest extends BasicTest {

	@Test
	public void profileTest() {
		driver.get(baseUrl + "/guest-user/login-form");
	}
	
}
