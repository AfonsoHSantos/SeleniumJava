package afonso.h.santos.tests;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import afonso.h.santos.core.BasePage;
import afonso.h.santos.core.BaseTest;
import afonso.h.santos.pages.AccountPage;
import afonso.h.santos.pages.MensalResumePage;
import afonso.h.santos.pages.MenuPage;
import afonso.h.santos.pages.MovementPage;
import afonso.h.santos.utils.DateUtils;

public class MovementTest extends BaseTest {
	MenuPage menuPage = new MenuPage();
	BasePage basePage = new BasePage();
	MovementPage movementPage = new MovementPage();
	AccountPage accountPage = new AccountPage();
	DateUtils dateUtils = new DateUtils();
	MensalResumePage mensalResumePage = new MensalResumePage();
	
	@Before
	public void MovementSetUp() {
		menuPage.goToMovimentacao();
	}

	@Test
	public void CreateMovement () {		
		movementPage.createMovement("Receita", "01/01/2024", "01/01/2024", 
				"Movement Description", "Interested Person", "1000", "Conta para movimentacoes", "Pago");
		
		Assert.assertEquals("Movimentação adicionada com sucesso!", basePage.getSuccessMessage());
	}
	
	@Test
	public void ViewMandatoryFieldsErrors() {
		basePage.clickSubmitButton();
		
		List<String> mandatoryFieldErrors = movementPage.getErros();
		
		Assert.assertTrue(mandatoryFieldErrors.containsAll(Arrays.asList(
				"Data da Movimentação é obrigatório", "Data do pagamento é obrigatório", 
				"Descrição é obrigatório", "Interessado é obrigatório",
				"Valor é obrigatório", "Valor deve ser um número")));
		
		Assert.assertEquals(6, mandatoryFieldErrors.size());
	}
	
	@Test
	public void CanNotSubmitWithFutureMovDate() {
		Date futureDate = dateUtils.getFutureDateByDays(1);
		String date = dateUtils.getFormatedDate(futureDate);
		
		
		movementPage.createMovement("Receita", date, date, 
				"Movement Description", "Interested Person", "1000", "Conta para movimentacoes", "Pendente");
		
		Assert.assertEquals("Data da Movimentação deve ser menor ou igual à data atual", basePage.getErrorMessage());
	}
	
	@Test
	public void CanRemoveMovement() {
		menuPage.goToMenu("Resumo Mensal");

		basePage.selectCombo("mes", "Janeiro");
		
		basePage.selectCombo("ano", "2024");
		
		basePage.clickSubmitButton();
		
		mensalResumePage.RemoveMovement("Movimentacao para exclusao	");
		
		Assert.assertEquals("Movimentação removida com sucesso!", basePage.getSuccessMessage());
	}
}
