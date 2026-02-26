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
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
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
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BrowserStackECom extends BaseClass {
	
	@BeforeAll 
	 static void setUpBeforeClass() throws Exception {
		setupApps( "android");	
	}

	@Test
	@Order(0)
	public void check() {
		
     
	 clickWithScroll("add-to-cart-16");
     
	 appiumDriver.findElement(MobileBy.AccessibilityId("nav-cart")).click();
     
	 MobileElement e1=(MobileElement) appiumDriver.findElement(MobileBy.AccessibilityId("cart-item"));
	 Point loc = e1.getLocation();
     Dimension dimension = appiumDriver.manage().window().getSize();
     TouchAction touchAction=  new TouchAction(appiumDriver);
     TouchAction tochWithWait = touchAction.waitAction(WaitOptions.waitOptions(Duration.ofMillis(500L)));
     TouchAction tochWithPress = tochWithWait.press(PointOption.point(dimension.getWidth() - 200, loc.getY()));
     tochWithPress.waitAction(WaitOptions.waitOptions(Duration.ofMillis(500L))).moveTo(PointOption.point(dimension.getWidth() - 500, loc.getY())).release().perform();
    
     (appiumDriver.findElementByXPath("//*[@text = 'Delete']")).click();
     assertEquals(appiumDriver.findElement(MobileBy.AccessibilityId("number-of-products")).getText(), "0 product(s) found.");
}
	//@Test
	@Order(1)
	void login() {
		appiumDriver.findElementByAccessibilityId("menu").click();
		appiumDriver.findElementByAccessibilityId("nav-signin").click();
		appiumDriver.findElementByXPath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Accepted usernames are\"]").click();
		appiumDriver.findElementByXPath("//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"fav_user\"]").click();
		appiumDriver.findElementByXPath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Password for all users\"]").click();
		appiumDriver.findElementByXPath("//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"testingisfun99\"]").click();
		appiumDriver.findElementByXPath("//android.widget.TextView[@text=\"Sign in\"]").click();
	}
	
	//@Test
	@Order(2)
	void addToCart() {
		clickWithScroll("add-to-cart-12");
		clickWithScroll("add-to-cart-16");
	}
	
	//@Test
	@Order(3)
	void checkout() {
		appiumDriver.findElementByAccessibilityId("nav-cart").click();
		String actualText=appiumDriver.findElementByAccessibilityId("number-of-products").getText();
		assertEquals("2 product(s) found.", actualText);
				
	}
	
	@AfterAll
	 static void tearDownAfterClass() throws Exception {
		
		JavascriptExecutor jse = (JavascriptExecutor) appiumDriver;
		jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"All steps completed!\"}}");
		appiumDriver.quit();

	}

	
	
	
	public void performLowLevel() {
		 PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		  Sequence tap = new Sequence(finger, 1);
		    
		    tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 2,5));
		    tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		    tap.addAction(finger.createPointerMove(Duration.ofMillis(20), PointerInput.Origin.viewport(), 2,5));
		    tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		    appiumDriver.perform(Arrays.asList(tap));
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


