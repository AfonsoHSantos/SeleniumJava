package afonso.h.santos.pages;

import static afonso.h.santos.core.DriverFactory.getDriver;

import org.openqa.selenium.By;

import afonso.h.santos.core.BasePage;

public class LoginPage extends BasePage {

	public void openLoginPage() {
		getDriver().get("https://seubarriga.wcaquino.me");
	}
	
	public void signIn(String email, String password) {
		type("email", email);
		
		type("senha", password);
		
		click(By.xpath("//button[@type='submit']"));
	}
	
	public void resetData() {
		click(By.xpath("//a[@href='/reset']"));
	}
		
}
