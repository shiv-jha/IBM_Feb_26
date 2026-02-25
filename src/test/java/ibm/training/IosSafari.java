package ibm.training;

import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ibm.training.setup.BaseClass;
import io.appium.java_client.MobileElement;

class IosSafari  {

	static WebDriver driver;
	static MutableCapabilities capabilities = new MutableCapabilities();
	static HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
	static String username = "sktraining_aznSMe";
	static String accesskey = "v5SDLeHyH6zWHocUTEGG";
	public static WebDriverWait wait;
	
	public static final String URL = "https://" + username + ":" + accesskey + "@hub-cloud.browserstack.com/wd/hub";
	
	public static void main(String[] args) throws MalformedURLException {
		browserstackOptions.put("platformName", "ios");
		browserstackOptions.put("osVersion", "15");
		browserstackOptions.put("browserName", "safari");
		browserstackOptions.put("deviceName", "iPhone 13");
		browserstackOptions.put("realMobile", "true");
		
		capabilities.setCapability("bstack:options", browserstackOptions);
		driver = new RemoteWebDriver(new URL(URL),capabilities);
		driver.get("https://www.saucedemo.com/");
		System.out.println("title before login "+ driver.getTitle());
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("login-button")))).click();
		//driver.findElement(By.id("login-button")).click();
		System.out.println("title after login"+ driver.getTitle());
		driver.quit();
	}
}
