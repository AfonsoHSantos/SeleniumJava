import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegistrationTest {

	private WebDriver driver;
	private CampoTreinamentoPage page;

	@Before
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		page = new CampoTreinamentoPage(driver);
	}

	@After
	public void tearDown() {
		driver.quit();
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
