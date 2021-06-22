package tests;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends BasicTest {

	@Test(priority = 1)
	public void editProfileTest() throws InterruptedException {
		driver.get(baseUrl + "/guest-user/login-form");
		locationPopupPage.closeSelectLocation();

		Thread.sleep(3000);

		loginPage.userLogin(userEmail, password);
		Assert.assertTrue(notificationSistemPage.messageText().contains("Login Successfull"));

		driver.get(baseUrl + "/member/profile");

		profilePage.editAllInfo("RandomName", "RandomLastName", "RandomAddress", "654321987", "18000", "India", "Bihar",
				"Raxaul");
		Assert.assertTrue(notificationSistemPage.messageText().contains("Setup Successful"));

		authPage.userLogout();

		Assert.assertTrue(notificationSistemPage.messageText().contains("Logout Successfull!"));
	}

	@Test(priority = 2)
	public void changeProfileImage() throws InterruptedException, IOException {
		driver.get(baseUrl + "/guest-user/login-form");
		locationPopupPage.closeSelectLocation();

		Thread.sleep(3000);

		loginPage.userLogin(userEmail, password);
		Assert.assertTrue(notificationSistemPage.messageText().contains("Login Successfull"));

		driver.get(baseUrl + "/member/profile");

		String imgPath = new File("images/logo_itbootcamp_400x400.png").getCanonicalPath();
		profilePage.uploadPhoto(imgPath);

		Assert.assertTrue(notificationSistemPage.messageText().contains("Profile Image Uploaded Successfully"));
		notificationSistemPage.waitMessageToDisappear();

		profilePage.removePhoto();
		Assert.assertTrue(notificationSistemPage.messageText().contains("Profile Image Deleted Successfully"));
		notificationSistemPage.waitMessageToDisappear();

		authPage.userLogout();
		Assert.assertTrue(notificationSistemPage.messageText().contains("Logout Successfull!"));
	}

}
