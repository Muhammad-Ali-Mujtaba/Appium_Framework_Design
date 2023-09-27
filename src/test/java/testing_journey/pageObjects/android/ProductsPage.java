package testing_journey.pageObjects.android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductsPage {
	
	AndroidDriver driver;
	
	public ProductsPage(AndroidDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"ADD TO CART\"]")
	private List<WebElement> itemCartButtons;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement goToCartButton;
	
	public void addItemsToCartByIndex(int index) {
		
		itemCartButtons.get(index).click();
	}
	
	public CartPage goToCart() {
		goToCartButton.click();
		return new CartPage(driver);
	}

}
