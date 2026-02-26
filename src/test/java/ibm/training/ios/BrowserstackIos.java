package ibm.training.ios;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;

import ibm.training.setup.BaseClass;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

class BrowserstackIos extends BaseClass {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		setupApps("ios");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) appiumDriver;
		jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"All steps completed!\"}}");
		appiumDriver.quit();
	}

	@Test
	void testIosSampleApp() {
		MobileElement el1 = (MobileElement) appiumDriver.findElement(MobileBy.AccessibilityId("Text Button"));
		el1.click();
		MobileElement el2 =(MobileElement) appiumDriver.findElement(MobileBy.AccessibilityId("Text Input"));
		el2.click();
		el2.sendKeys("Testing");
		
		MobileElement el6 = (MobileElement) appiumDriver.findElement(MobileBy.AccessibilityId("Return"));
		el6.click();
		MobileElement el7 = (MobileElement) appiumDriver.findElement(MobileBy.AccessibilityId("Text Output"));
		assertEquals("Testing", el7.getText());
		MobileElement el8 = (MobileElement) appiumDriver.findElement(MobileBy.AccessibilityId("Web View"));
		el8.click();
		MobileElement el9 = (MobileElement) appiumDriver.findElement(MobileBy.AccessibilityId("Web Testing - Test websites or web apps on real browsers"));
		el9.click();
			
	}

}
