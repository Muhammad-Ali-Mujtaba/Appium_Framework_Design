package testing_journey.pageObjects.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import testing_journey.utils.AndroidActions;
import io.appium.java_client.pagefactory.*;

public class FormPage extends AndroidActions {

	AndroidDriver driver;

	public FormPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	private WebElement nameField;

	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text=\"Female\"]")
	private WebElement genderFemale;

	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text=\"Male\"]")
	private WebElement genderMale;

	@AndroidFindBy(id = "android:id/text1")
	private WebElement countryDropdown;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	private WebElement btnLetsShop;
	
	@AndroidFindBy(xpath = "(//android.widget.Toast)[1]")
	private WebElement errorToast;

	public void setNameField(String name) {

		nameField.sendKeys(name);
		driver.hideKeyboard();

	}

	public void setGender(String gender) {

		if (gender.contains("female")) {
			genderFemale.click();
		} else {
			genderMale.click();

		}
	}

	public void countrySelection(String countryName) {

		countryDropdown.click();
		scrollTillElementName(countryName);
		driver.findElement(By.xpath("//android.widget.TextView[@text=\""+countryName+"\"]")).click();

	}
	
	public ProductsPage letsShop() {
		btnLetsShop.click();
		return new ProductsPage(driver);
	}
	
	public String toastErrorMessage(String attribute) {
		return errorToast.getAttribute(attribute);
	}
	
	public void setActivity() {
		Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
		driver.startActivity(activity);
	}

}
