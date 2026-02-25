package ibm.training;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidChrome {
	
	static WebDriver driver;
	static MutableCapabilities capabilities = new MutableCapabilities();
	static HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
	static String username = "sktraining_aznSMe";
	static String accesskey = "v5SDLeHyH6zWHocUTEGG";
	
	public static final String URL = "https://" + username + ":" + accesskey + "@hub-cloud.browserstack.com/wd/hub";
	
	public static void main(String[] args) throws MalformedURLException {
		browserstackOptions.put("platformName", "android");
		browserstackOptions.put("osVersion", "14");
		browserstackOptions.put("browserName", "chrome");
		browserstackOptions.put("deviceName", "Google Pixel 8 Pro");
		browserstackOptions.put("realMobile", "true");
		
		capabilities.setCapability("bstack:options", browserstackOptions);
		driver = new RemoteWebDriver(new URL(URL),capabilities);
		driver.get("https://www.saucedemo.com/");
		System.out.println("title before login "+ driver.getTitle());
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button ")).click();
		System.out.println("title after login"+ driver.getTitle());
		driver.quit();
	}

}
