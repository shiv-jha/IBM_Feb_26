package ibm.training;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;

import ibm.training.setup.BaseClass;

class BrowserstackIos extends BaseClass {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		setup("ios", "bs://d4b63dd37b963dff27922add882dab565a881387", "sample ios app");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) appiumDriver;
		jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"All steps completed!\"}}");
		appiumDriver.quit();
	}

	@Test
	void test() {
		
		System.out.println("ios app launched");
	}

}
