package testng;

import org.testng.annotations.Test;

import ibm.training.setup.BaseClass;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import org.testng.annotations.BeforeSuite;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;
import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.testng.annotations.AfterSuite;

public class AndroidTest extends BaseClass {

	@BeforeSuite
	public void beforeSuite() throws MalformedURLException {
		setupApps("android");

	}
	
	@Test(priority = 1)
	public void login() {
		appiumDriver.findElementByAccessibilityId("menu").click();
		appiumDriver.findElementByAccessibilityId("nav-signin").click();
		appiumDriver.findElementByXPath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Accepted usernames are\"]").click();
		appiumDriver.findElementByXPath("//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"fav_user\"]").click();
		appiumDriver.findElementByXPath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Password for all users\"]").click();
		appiumDriver.findElementByXPath("//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"testingisfun99\"]").click();
		appiumDriver.findElementByXPath("//android.widget.TextView[@text=\"Sign in\"]").click();
	}
	
	@Test(priority = 2)
	public void addtoCart() {
		clickWithScroll("add-to-cart-12");
		clickWithScroll("add-to-cart-16");
	}
	
	@Test(priority = 3)
	public void checkout() {
		appiumDriver.findElementByAccessibilityId("nav-cart").click();
		String actualText=appiumDriver.findElementByAccessibilityId("number-of-products").getText();
		assertEquals("2 product(s) found.", actualText);
	}
	
	@Test(priority = 0)
	public void testEcomAppSwipeFeature() {

		clickWithScroll("add-to-cart-16");
		appiumDriver.findElement(MobileBy.AccessibilityId("nav-cart")).click();

		MobileElement e1 = (MobileElement) appiumDriver.findElement(MobileBy.AccessibilityId("cart-item"));
		Point loc = e1.getLocation();
		Dimension dimension = appiumDriver.manage().window().getSize(); // total app size- 800,1000
		TouchAction touchAction = new TouchAction(appiumDriver);
		TouchAction tochWithWait = touchAction.waitAction(WaitOptions.waitOptions(Duration.ofMillis(500L)));
		TouchAction tochWithPress = tochWithWait.press(PointOption.point(dimension.getWidth() - 200, loc.getY())); // 1000
																													// -200
		tochWithPress.waitAction(WaitOptions.waitOptions(Duration.ofMillis(500L)))
				.moveTo(PointOption.point(dimension.getWidth() - 500, loc.getY())).release().perform();

		(appiumDriver.findElementByXPath("//*[@text = 'Delete']")).click();
		assertEquals(appiumDriver.findElement(MobileBy.AccessibilityId("number-of-products")).getText(),
				"0 product(s) found.");
	}

	

	@AfterSuite
	public void afterSuite() {

		JavascriptExecutor jse = (JavascriptExecutor) appiumDriver;
		jse.executeScript(
				"browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"All steps completed!\"}}");
		appiumDriver.quit();
	}

}
