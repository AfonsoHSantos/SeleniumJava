package afonso.h.santos.pages;

import static afonso.h.santos.core.DriverFactory.getDriver;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import afonso.h.santos.core.BasePage;

public class MovementPage extends BasePage {
	
	public void createMovement(String movementType, String movementDate, String paymentDate, String description, String interest, String value, String account, String status) {
		selectCombo("tipo", movementType);
		
		type(By.id("data_transacao"), movementDate);
		
		type(By.id("data_pagamento"), paymentDate);
		
		type(By.id("descricao"), description);
		
		type(By.id("interessado"), interest);
		
		type(By.id("valor"), value);
		
		selectCombo("conta", account);
		
		String statusKey = String.format("%s", status).toLowerCase();
		
		clickRadio(String.format("status_%s", statusKey));
		
		clickSubmitButton();
	}
	
	
	public List<String> getErros() {
		List<WebElement> errors = getDriver().findElements(By.xpath("//div[contains(@class,'alert-danger')]//li"));
		List<String> response = new ArrayList<String>();
		
		for(WebElement error: errors) {
			response.add(error.getText());
		}
		
		return response;
	}

}
