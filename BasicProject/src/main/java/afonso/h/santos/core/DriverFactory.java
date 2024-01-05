package afonso.h.santos.core;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import afonso.h.santos.core.Properties.ExecutionType;

public class DriverFactory {
	private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>(){
		@Override
		protected synchronized WebDriver initialValue(){
			return initDriver();
		}	
	};
	
	private DriverFactory() {}
	
	public static WebDriver getDriver(){
		return threadDriver.get();
	}
	
	public static WebDriver initDriver(){
		WebDriver driver = null;
		
		if(Properties.EXECUTION_TYPE == ExecutionType.LOCAL) {
			switch (Properties.BROWSER) {
			case FIREFOX: driver = new FirefoxDriver(); break;
			case CHROME: driver = new ChromeDriver(); break;
			}
		}
		
		if(Properties.EXECUTION_TYPE == ExecutionType.GRID) {
			DesiredCapabilities cap = null;
			
			switch (Properties.BROWSER) {
				case FIREFOX: cap = DesiredCapabilities.firefox(); break;
				case CHROME: cap = DesiredCapabilities.chrome(); break;
			}
			try {
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
			} catch (MalformedURLException e) {
				System.out.print("Failed while trying to connect with GRID");
				e.printStackTrace();
			}
		}
		
		if(Properties.EXECUTION_TYPE == ExecutionType.CLOUD) {
			DesiredCapabilities cap = null;
			
			switch (Properties.BROWSER) {
				case FIREFOX: cap = DesiredCapabilities.firefox(); break;
				case CHROME: cap = DesiredCapabilities.chrome(); break;
			}
			try {
				driver = new RemoteWebDriver(new URL("https://oauth-contatoafonsohenrique-2d939:8994dd58-dcae-4a98-88fa-0ca4c8484184@ondemand.us-west-1.saucelabs.com:443/wd/hub"), cap);
			} catch (MalformedURLException e) {
				System.out.print("Failed while trying to connect with CLOUD");
				e.printStackTrace();
			}
		}
		
		driver.manage().window().setSize(new Dimension(1200, 765));
		return driver;
	}
	
	public static void killDriver(){
		WebDriver driver = getDriver();
		if(driver != null) {
			driver.quit();
			driver = null;
		}
		if(threadDriver != null) {
			threadDriver.remove();
		}
	}
}
