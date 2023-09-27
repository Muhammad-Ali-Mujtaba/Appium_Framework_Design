package testing_journey;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.android.options.context.SupportsChromeOptionsOption;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import testing_journey.pageObjects.android.FormPage;
import testing_journey.utils.AppiumUtils;

//A base test class for configurations so other classes can just extend it and implement test cases.
public class BaseTest extends AppiumUtils {

	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public FormPage formPage;

	@BeforeClass(alwaysRun = true)
	public void setupAppium() throws IOException {

		Properties properties = new Properties();
		FileInputStream fileInputStream = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\testing_journey\\resources\\data.properties");
		properties.load(fileInputStream);

		String ipAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress")
				: properties.getProperty("ipAddress");
		int port = Integer.parseInt(properties.getProperty("port"));

		service = startService(ipAddress, port);
		service.start();

		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(properties.getProperty("androidDeviceName"));

		options.setApp(System.getProperty("user.dir") + "\\src\\main\\resources\\General-Store.apk");

		driver = new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		formPage = new FormPage(driver);

	}

	
	@AfterClass(alwaysRun = true)
	public void clearAppiumProcess() {

		driver.quit();
		service.stop();

	}

}
