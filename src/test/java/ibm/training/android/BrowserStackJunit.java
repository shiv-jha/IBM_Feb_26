package ibm.training.android;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import ibm.training.setup.BaseClass;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

class BrowserStackJunit extends BaseClass {
	
	
	@BeforeAll 
	 static void setUpBeforeClass() throws Exception {
		setupApps( "android");	
	}

	@Test
	@Order(1)
	void navigateAppToHomePage() {
		MobileElement continueBtn= (MobileElement)appiumDriver.findElementById("com.android.permissioncontroller:id/continue_button");
		continueBtn.click();
		
		WebDriverWait wait = new WebDriverWait(appiumDriver, 10);
		MobileElement okBtn = (MobileElement) wait.until(
			    ExpectedConditions.elementToBeClickable(By.id("android:id/button1"))
			);
		okBtn.click();
	}
	
	@Test
	@Order(2)
	void menuVerification() {
		
		List<MobileElement> menus= (List<MobileElement>)appiumDriver.findElementsByXPath("//android.widget.ListView/android.widget.TextView");
		assertEquals(11, menus.size());
		System.out.println(menus.size());
		for(MobileElement menu: menus) {
			System.out.println(menu.getText());
		}
		
		MobileElement appBtn= (MobileElement)appiumDriver.findElementByAccessibilityId("App");
		appBtn.click();
		MobileElement eventsBtn= (MobileElement)appiumDriver.findElementByAccessibilityId("Events");
		eventsBtn.click();
		
		
		
		MobileElement addBtn = (MobileElement) appiumDriver.findElementByAccessibilityId("Add Button");
		TouchAction touch = new TouchAction(appiumDriver);
		touch.tap(TapOptions.tapOptions().withElement(ElementOption.element(addBtn))).perform();

		
		
	}
	
	@AfterAll
	 static void tearDownAfterClass() throws Exception {
		
		JavascriptExecutor jse = (JavascriptExecutor) appiumDriver;
		jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"All steps completed!\"}}");
		appiumDriver.quit();

	}

	public void tapElement(MobileElement el) {
	    Rectangle rect = el.getRect();
	    int x = rect.x + (rect.width / 2);
	    int y = rect.y + (rect.height / 2);
	    System.out.println("x-"+x);
	    System.out.println("y-"+y);

	    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
	    Sequence tap = new Sequence(finger, 1);
	    
	    tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
	    tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
	    tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
	    
	    appiumDriver.perform(Arrays.asList(tap));
	}
}
