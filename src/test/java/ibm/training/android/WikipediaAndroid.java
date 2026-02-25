package ibm.training.android;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ibm.training.setup.BaseClass;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;

class WikipediaAndroid extends BaseClass{

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		setupApps( "android","bs://7c00be543fb646e1eda898567cfc9ed22d274791","Wikipedia app");	
	}

	@Test
	void wikipediaSearchFeature() {
		MobileElement searchHeader = (MobileElement) appiumDriver
				.findElementById("org.wikipedia.alpha:id/fragment_feed_header");
		
		//tap on the element
		TouchAction touch = new TouchAction(appiumDriver);
		//touch.tap(TapOptions.tapOptions().withElement(ElementOption.element(searchHeader))).perform();
		
		//click on the element
		searchHeader.click();
		
		MobileElement seachText = (MobileElement) wait.until(ExpectedConditions
				.visibilityOf((MobileElement) appiumDriver.findElementById("org.wikipedia.alpha:id/search_src_text")));
		seachText.sendKeys("Browserstack");
		
		List<MobileElement> result= (List<MobileElement>)appiumDriver.findElementsByXPath("//android.widget.FrameLayout/android.widget.LinearLayout");
		System.out.println(result.size());
		List<MobileElement> resultTexts= (List<MobileElement>)appiumDriver.findElementsByXPath("//android.widget.LinearLayout/android.widget.TextView");
		assertEquals("browserstack", resultTexts.get(1).getText().toLowerCase());
		
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) appiumDriver;
		jse.executeScript(
				"browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"All steps completed!\"}}");
		appiumDriver.quit();
	}

}
