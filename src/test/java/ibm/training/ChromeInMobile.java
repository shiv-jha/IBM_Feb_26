package ibm.training;

import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

class ChromeInMobile {

	static WebDriver driver;
	
	//
	static MutableCapabilities bsCapability = new MutableCapabilities();
	static HashMap<String, Object> bsOptions = new HashMap<String, Object>();
	static String userName = "sktraining_aznSMe";
	static String accesskey = "v5SDLeHyH6zWHocUTEGG";
	
	
	
	
	
	
	
	public static final String URL = "https://" + userName + ":" + accesskey + "@hub-cloud.browserstack.com/wd/hub";
	
	@Test
	void SauceLogin() throws MalformedURLException {

		bsOptions.put("platformName", "android");
		bsOptions.put("osVersion", "14");
		bsOptions.put("browserName", "chrome");
		bsOptions.put("deviceName", "Google Pixel 8 Pro");
		bsOptions.put("realMobile", "true");
		bsOptions.put("projectName","ibm training");
		bsOptions.put("buildName","day1");
		//bsOptions.put("local", "true");
		bsCapability.setCapability("bstack:options", bsOptions);
		driver = new RemoteWebDriver(new URL(URL), bsCapability);
		driver.get("https://www.saucedemo.com/");
		String sTitle=driver.getTitle();
		System.out.println("title:"+driver.getTitle());
		assertEquals("Swag Labs", sTitle);
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		driver.close();
		driver.quit();
	
		
	}

}
