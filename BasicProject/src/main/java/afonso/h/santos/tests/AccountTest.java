package afonso.h.santos.tests;

import org.junit.Assert;
import org.junit.Test;

import afonso.h.santos.core.BasePage;
import afonso.h.santos.core.BaseTest;
import afonso.h.santos.pages.AccountPage;
import afonso.h.santos.pages.MenuPage;
import afonso.h.santos.pages.MovementPage;
import afonso.h.santos.utils.DateUtils;

public class AccountTest extends BaseTest {
	MenuPage menuPage = new MenuPage();
	AccountPage accountPage = new AccountPage();
	BasePage basePage = new BasePage();
	DateUtils dateUtils = new DateUtils();
	MovementPage movementPage= new MovementPage();
	
	@Test
	public void CanCreateAccount() {
		menuPage.goToMenu("Contas", "Adicionar");
		
		accountPage.submitAccount("Test Account");
		
		Assert.assertEquals("Conta adicionada com sucesso!", basePage.getSuccessMessage());
	}
	
	@Test
	public void CanEditAccount() {	
		menuPage.goToMenu("Contas", "Listar");
		
		accountPage.editButton("Conta para alterar");
		
		accountPage.submitAccount("Edited Account");
		
		Assert.assertEquals("Conta alterada com sucesso!", basePage.getSuccessMessage());
	}
	
	@Test
	public void CanNotAddDuplicatedAccount() {
		menuPage.goToMenu("Contas", "Adicionar");
		
		accountPage.submitAccount("Conta mesmo nome");
		
		Assert.assertEquals("Já existe uma conta com esse nome!", basePage.getErrorMessage());
	}
	
	@Test
	public void CanNotRemoveAccountWithMovement() {
		menuPage.goToMenu("Contas", "Listar");
		
		accountPage.removeButton("Conta com movimentacao");
		
		String test = basePage.getErrorMessage();
		
		System.out.print(test);
		
		Assert.assertEquals("Conta em uso na movimentações", basePage.getErrorMessage());
	}
}
