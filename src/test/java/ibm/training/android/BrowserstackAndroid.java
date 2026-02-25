package ibm.training.android;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileBrowserType;

public class BrowserstackAndroid {
	
	public static AndroidDriver appiumDriver;

	public static void main(String[] args) throws MalformedURLException {

		String USERNAME = "sktraining_aznSMe";  
		String AUTOMATE_KEY = "v5SDLeHyH6zWHocUTEGG";
		DesiredCapabilities cap= new DesiredCapabilities();
		cap.setCapability("platformName", "Android");
		cap.setCapability("os_version", "11.0");
		cap.setCapability("device", "Samsung Galaxy S21 Ultra");
		cap.setCapability("app", "bs://49419f8dbba53bdec25555bde2636623a28bce7c");
		cap.setCapability("project", "Training-IBM-Feb-Batch");
		cap.setCapability("build", "Build 1.3");
		cap.setCapability("name", "Demo App Test");
		

		String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
		appiumDriver = new AndroidDriver(new URL(URL), cap);
		appiumDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		MobileElement continueBtn= (MobileElement)appiumDriver.findElementById("com.android.permissioncontroller:id/continue_button");
		continueBtn.click();
		
		WebDriverWait wait = new WebDriverWait(appiumDriver, 10);
		MobileElement okBtn = (MobileElement) wait.until(
			    ExpectedConditions.elementToBeClickable(By.id("android:id/button1"))
			);
		okBtn.click();
		
		List<MobileElement> menus= (List<MobileElement>)appiumDriver.findElementsByXPath("//android.widget.ListView/android.widget.TextView");
		System.out.println("total menu count: "+menus.size());
		for(MobileElement menu: menus) {
			System.out.println(menu.getText());
		}
		
		JavascriptExecutor jse = (JavascriptExecutor) appiumDriver;
		jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"All steps completed!\"}}");

		appiumDriver.quit();

		//browserstack - 
		//appium- quit done
	}

}
