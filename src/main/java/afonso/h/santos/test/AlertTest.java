package afonso.h.santos.test;
import static afonso.h.santos.core.DriverFactory.getDriver;
import static afonso.h.santos.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import afonso.h.santos.core.BaseTest;
import afonso.h.santos.core.DSL;

public class AlertTest extends BaseTest {

	private DSL dsl;

	@Before
	public void before() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}

	@After
	public void after() {
		killDriver();
	}

	@Test
	public void testSimpleAlert() {
		dsl.clickButton("alert");
		String Text = dsl.getAlertTextAndAccept();
		Assert.assertEquals("Alert Simples", Text);

		dsl.type("elementosForm:nome", Text);
	}

	@Test
	public void testConfirmAlert() {
		dsl.clickButton("confirm");
		Assert.assertEquals("Confirm Simples", dsl.getAlertTextAndAccept());
		Assert.assertEquals("Confirmado", dsl.getAlertTextAndAccept());

		dsl.clickButton("confirm");
		Assert.assertEquals("Confirm Simples", dsl.getAlertTextAndReject());
		Assert.assertEquals("Negado", dsl.getAlertTextAndReject());
	}

	@Test
	public void testPromptAlert() {
		dsl.clickButton("prompt");
		Assert.assertEquals("Digite um numero", dsl.getAlertText());
		dsl.typeAlert("12");
		Assert.assertEquals("Era 12?", dsl.getAlertTextAndAccept());
		Assert.assertEquals(":D", dsl.getAlertTextAndAccept());
	}
}
