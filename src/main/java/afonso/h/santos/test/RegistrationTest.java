package afonso.h.santos.test;
import static afonso.h.santos.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import afonso.h.santos.core.BaseTest;
import afonso.h.santos.page.CampoTreinamentoPage;

public class RegistrationTest extends BaseTest {

	private CampoTreinamentoPage page;

	@Before
	public void before() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		page = new CampoTreinamentoPage();
	}

	@Test
	public void fillEntireForm() {
		page.setNome("Wagner");
		page.setSobrenome("Costa");
		page.setSexo("male");
		page.setComida("Pizza");
		page.setEscolaridade("Mestrado");
		page.setEsporte("Natacao");
		page.clickCadastrarButton();

		Assert.assertEquals("Cadastrado!", page.getRegistrationResult());
		Assert.assertEquals("Wagner", page.getRegisteredNome());
		Assert.assertEquals("Costa", page.getRegisteredSobrenome());
		Assert.assertEquals("Masculino", page.getRegisteredSexo());
		Assert.assertEquals("Pizza", page.getRegisteredComida());
		Assert.assertEquals("mestrado", page.getRegisteredEscolaridade());
		Assert.assertEquals("Natacao", page.getRegisteredEsportes());
	}
}
