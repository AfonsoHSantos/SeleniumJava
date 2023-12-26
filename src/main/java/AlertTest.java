import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AlertTest {

	private WebDriver driver;
	private DSL dsl;

	@Before
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}

	@After
	public void tearDown() {
		driver.quit();
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
