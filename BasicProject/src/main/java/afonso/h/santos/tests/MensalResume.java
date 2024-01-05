package afonso.h.santos.tests;

import org.junit.Assert;
import org.junit.Test;

import afonso.h.santos.core.BasePage;
import afonso.h.santos.core.BaseTest;
import afonso.h.santos.pages.MensalResumePage;
import afonso.h.santos.pages.MenuPage;

public class MensalResume extends BaseTest {
	
	MenuPage menuPage = new MenuPage();
	BasePage basePage = new BasePage();
	MensalResumePage mensalResumePage = new MensalResumePage();
	
	@Test
	public void CanRemoveMovement() {
		menuPage.goToMenu("Resumo Mensal");

		basePage.selectCombo("mes", "Janeiro");
		
		basePage.selectCombo("ano", "2023");
		
		basePage.clickSubmitButton();
		
		mensalResumePage.RemoveMovement("Movement Description");
		
		Assert.assertEquals("Movimentação removida com sucesso!", basePage.getSuccessMessage());
	}
}
