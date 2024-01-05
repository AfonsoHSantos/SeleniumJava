package afonso.h.santos.suites;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import afonso.h.santos.core.BasePage;
import afonso.h.santos.core.DriverFactory;
import afonso.h.santos.pages.LoginPage;
import afonso.h.santos.tests.AccountTest;
import afonso.h.santos.tests.MensalResume;
import afonso.h.santos.tests.MovementTest;

@RunWith(Suite.class)
@SuiteClasses({
	AccountTest.class,
	MovementTest.class,
	MensalResume.class
})
public class GeneralSuite {
	private static LoginPage loginPage = new LoginPage();
	private static BasePage basePage = new BasePage();
	
	@BeforeClass
	public static void reset(){
		loginPage.openLoginPage();
		
		loginPage.signIn("afonso@test.com", "test");
		
		loginPage.resetData();
		
		Assert.assertEquals("Dados resetados com sucesso!", basePage.getSuccessMessage());
		
		DriverFactory.killDriver();
	}
}
