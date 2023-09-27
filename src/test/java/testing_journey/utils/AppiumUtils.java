package testing_journey.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumUtils {

	public List<HashMap<String, String>> getJsonData(String jsonFile) throws IOException{
		
		String jsonContent = FileUtils.readFileToString(new File(jsonFile), StandardCharsets.UTF_8);
		
		ObjectMapper dataMapper = new ObjectMapper();
		
		List<HashMap<String, String>> data = dataMapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		
		return data;
		
	}
	
	public AppiumDriverLocalService startService(String ipAddress, int port) {
		return new AppiumServiceBuilder().withAppiumJS(new File(
				"C:\\\\Users\\\\Muhammad Ali\\\\AppData\\\\Roaming\\\\npm\\\\node_modules\\\\appium\\\\build\\\\lib\\\\main.js"))
				.withIPAddress(ipAddress).withTimeout(Duration.ofSeconds(50)).usingPort(port).build();
	}
	
	public String getScreenshotPath(String testCaseName, AppiumDriver driver) throws IOException {
		
		File sourceFile = driver.getScreenshotAs(OutputType.FILE);
		String destinationFilePath = System.getProperty("user.dir")+"\\reports"+testCaseName+".png";
		FileUtils.copyFile(sourceFile, new File(destinationFilePath));
		return destinationFilePath;
		
	}

}
