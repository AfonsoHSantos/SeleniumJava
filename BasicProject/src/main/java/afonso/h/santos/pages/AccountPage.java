package afonso.h.santos.pages;

import org.openqa.selenium.By;

import afonso.h.santos.core.BasePage;

public class AccountPage extends BasePage {
	
	public void submitAccount(String accountName) {
		type("nome", accountName);
		
		click(By.xpath("//button[@type='submit']"));
	}
	
	public void removeButton (String accountName) {
		click(By.xpath(String.format("//td[text()='%s']//following-sibling::td/a[contains(@href,'removerConta')]", accountName)));
	}
	
	public void editButton (String accountName) {
		click(By.xpath(String.format("//td[text()='%s']//following-sibling::td/a[contains(@href,'editarConta')]", accountName)));
	}
	
}
