package testing_journey;

import static org.testng.Assert.assertFalse;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import testing_journey.pageObjects.android.CartPage;
import testing_journey.pageObjects.android.FormPage;
import testing_journey.pageObjects.android.ProductsPage;

public class Ecommerce_Cart_WebView extends BaseTest {

	// Test cart of app and open website in WebView to complete purchase implemented
	// with Page Factory design and data provider with JSON parsing into HashMaps
	
	@BeforeMethod(alwaysRun = true)
	public void preSetup() {
		formPage.setActivity();
	}

	@Test(dataProvider = "getData", groups = { "Smoke" })
	public void webViewValidation(HashMap<String, String> data) throws InterruptedException {

		formPage.setNameField(data.get("name"));
		formPage.setGender(data.get("gender"));
		formPage.countrySelection(data.get("country"));

		ProductsPage productsPage = formPage.letsShop();
		productsPage.addItemsToCartByIndex(0);
		productsPage.addItemsToCartByIndex(0);

		CartPage cartPage = productsPage.goToCart();
		// Waiting using WebDriverWait for new screen
		cartPage.waitForCartPageToLoad("text", "Cart");

		Double firstProductPrice = cartPage.getProductPriceByIndex(0);
		Double secondProductPrice = cartPage.getProductPriceByIndex(1);
		Double totalPriceDisplayed = cartPage.getTotalPriceFromCart();
		Assert.assertEquals(totalPriceDisplayed, firstProductPrice + secondProductPrice);

		// Dealing with terms and opening WebView
		cartPage.termsReadAndContinue();

		Thread.sleep(5000);

	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "\\src\\test\\java\\testing_journey\\testData\\formData.json");

		return new Object[][] { { data.get(0) }, { data.get(1) }, };
	}

}
