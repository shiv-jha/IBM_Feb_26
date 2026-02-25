package ibm.training.android;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import ibm.training.setup.BaseClass;

public class AndroidChrome extends BaseClass{

	
	public static void main(String[] args) throws MalformedURLException {
		
		setupBrowser("android", "sauce login");
		driver.get("https://www.saucedemo.com/");
		System.out.println("title before login "+ driver.getTitle());
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button ")).click();
		System.out.println("title after login"+ driver.getTitle());
		driver.quit();
	}

}
