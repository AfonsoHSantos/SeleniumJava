package afonso.h.santos.pages;

import org.openqa.selenium.By;

import afonso.h.santos.core.BasePage;

public class MensalResumePage extends BasePage {
	public void RemoveMovement(String movementTitle) {
		click(By.xpath(String.format("//table[@id='tabelaExtrato']//tr/td[text()='%s']//following-sibling::td/a[contains(@href,'removerMovimentacao')]", movementTitle)));
	}
}
