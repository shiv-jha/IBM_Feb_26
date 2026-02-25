package ibm.training.setup;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class BaseClass {
	public static AndroidDriver appiumDriver;
	public static String USERNAME = "sktraining_aznSMe";  
	public static String AUTOMATE_KEY = "";
	public static WebDriverWait wait;

public static AndroidDriver setup(String platformType,String appPath,String appName) throws MalformedURLException {
		
		DesiredCapabilities cap= new DesiredCapabilities();
		if(platformType.toLowerCase().equals("android")) {
			cap.setCapability("platformName", platformType);
			cap.setCapability("os_version", "11.0");
			cap.setCapability("device", "Samsung Galaxy S21 Ultra");
		}
		else {
			//capability for ios
		}
		cap.setCapability("app", appPath);
		cap.setCapability("project", "Training-IBM-Feb-Batch");
		cap.setCapability("build", "Build 1.3");
		cap.setCapability("name", appName);
		String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
		appiumDriver = new AndroidDriver(new URL(URL), cap);
		appiumDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wait = new WebDriverWait(appiumDriver, 10);
		return appiumDriver;	
	}
}
