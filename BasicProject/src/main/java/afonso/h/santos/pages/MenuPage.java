package afonso.h.santos.pages;

import org.openqa.selenium.By;

import afonso.h.santos.core.BasePage;

public class MenuPage extends BasePage{
	
	public void goToMenu(String option) {
		click(By.xpath(String.format("//div[@id='navbar']//li/a[contains(.,'%s')]", option)));

	}
	
	public void goToMenu(String option, String subOption) {
		click(By.xpath(String.format("//div[@id='navbar']//li/a[contains(.,'%s')]", option)));
		 
		click(By.xpath(String.format("//ul/li/a[contains(.,'%s')]", subOption)));
	}
	
	public void goToMovimentacao() {
		click(By.xpath("//div[@id='navbar']//li/a[@href='/movimentacao']"));

	}
	
}
