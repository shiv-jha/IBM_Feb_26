package ibm.training.setup;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class BaseClass {
	public static AppiumDriver appiumDriver;
	public static WebDriver driver;
	public static String USERNAME = "shiv_o9E5TA";  
	public static String AUTOMATE_KEY = "Ndz6xKa1bybGeae2QbGs";
	public static WebDriverWait wait;

public static void setupApps(String platformType,String appPath,String appName) throws MalformedURLException {
		
		DesiredCapabilities cap= new DesiredCapabilities();
		if(platformType.toLowerCase().equals("android")) {
			cap.setCapability("platformName", platformType);
			cap.setCapability("os_version", "11.0");
			cap.setCapability("device", "Samsung Galaxy S21 Ultra");
		}
		else {
			cap.setCapability("platformName", platformType);
			cap.setCapability("os_version", "15.0");
			cap.setCapability("device", "iPhone 13");
		}

		cap.setCapability("app", appPath);
		cap.setCapability("project", "Training-IBM-Feb-Batch");
		cap.setCapability("build", "Build 1.4");
		cap.setCapability("name", appName);
		String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
		if(platformType.toLowerCase().equals("android")) {
			appiumDriver = new AndroidDriver(new URL(URL),cap);
		}
		else if(platformType.equals("ios")) {
			appiumDriver = new IOSDriver(new URL(URL),cap);
		}
		
		appiumDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wait = new WebDriverWait(appiumDriver, 10);
	}

public static void setupBrowser(String platformType,String featureName) throws MalformedURLException {
	
	DesiredCapabilities cap= new DesiredCapabilities();
	if(platformType.toLowerCase().equals("android")) {
		cap.setCapability("platformName", platformType);
		cap.setCapability("os_version", "11.0");
		cap.setCapability("device", "Samsung Galaxy S21 Ultra");
		cap.setCapability("browserName","chrome");
	}
	else {
		cap.setCapability("platformName", platformType);
		cap.setCapability("os_version", "15.0");
		cap.setCapability("device", "iPhone 13");
		cap.setCapability("browserName","safari");
	}

	cap.setCapability("realMobile","true");
	cap.setCapability("project", "Training-IBM-Feb-Batch");
	cap.setCapability("build", "Build 1.4");
	cap.setCapability("name", featureName);
	String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	driver = new RemoteWebDriver(new URL(URL),cap);
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	wait = new WebDriverWait(driver, 10);
}

public static void clickWithScroll(String desc) {
	appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).setMaxSearchSwipes(25).scrollIntoView(new UiSelector().description(\"" + desc + "\"))"));
	appiumDriver.findElementByAccessibilityId(desc).click();
}
}
