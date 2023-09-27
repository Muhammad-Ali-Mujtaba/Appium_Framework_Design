package testing_journey;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;

public class Ecommerce_Fill_Form extends BaseTest {

	@BeforeMethod
	public void preSetup() {

		Activity activity = new Activity("com.androidsample.generalstore",
				"com.androidsample.generalstore.MainActivity");
		driver.startActivity(activity);
	}

	@Test
	public void fillForm_ErrorValidation() throws InterruptedException {

		formPage.setGender("female");
		formPage.countrySelection("Argentina");
		formPage.letsShop();
		String toastMsg = formPage.toastErrorMessage("name");
		Assert.assertEquals(toastMsg, "Please enter your name");

	}

	@Test
	public void fillForm_PositiveFlow() throws InterruptedException {

		formPage.setNameField("Elena");
		formPage.setGender("female");
		formPage.countrySelection("Argentina");
		formPage.letsShop();

		Assert.assertTrue(driver.findElements(By.xpath("(//android.widget.Toast)[1]")).size() < 1);

	}

}
