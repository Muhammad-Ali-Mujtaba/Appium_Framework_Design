package testing_journey.pageObjects.android;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import testing_journey.utils.AndroidActions;

public class CartPage extends AndroidActions {

	AndroidDriver driver;

	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
	private WebElement cartTitleBar;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productPrices;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalAmountLabel;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	private WebElement termsButton;

	@AndroidFindBy(xpath = "//android.widget.Button[@text=\"CLOSE\"]")
	private WebElement closeTermsButton;

	@AndroidFindBy(className = "android.widget.CheckBox")
	private WebElement termsCheckBox;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
	private WebElement proceedButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Cart\"]")
	private WebElement cartActionBar;

	public void waitForCartPageToLoad(String attribute, String value) {
		waitForElementToAppear(cartActionBar, attribute, value);
	}

	public Double getProductPriceByIndex(int index) {

		String firstProduct = productPrices.get(index).getAttribute("text").substring(1);
		Double firstProductPrice = Double.parseDouble(firstProduct);
		return firstProductPrice;

	}

	public Double getTotalPriceFromCart() {

		String finalPrice = totalAmountLabel.getAttribute("text").substring(1);
		Double totalPrice = Double.parseDouble(finalPrice);
		return totalPrice;

	}

	public void termsReadAndContinue() {
		longPressAction(termsButton);
		closeTermsButton.click();
		termsCheckBox.click();
		proceedButton.click();
	}

}
