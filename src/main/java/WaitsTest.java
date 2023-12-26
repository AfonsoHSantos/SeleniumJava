import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitsTest {

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
	public void testFixedWait() throws InterruptedException {
		dsl.clickButton("ButtonDelay");
		Thread.sleep(5000);
		dsl.type("novoCampo", "Deu certo?");
	}

	@Test
	public void testImplicitWait() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		dsl.clickButton("ButtonDelay");
		dsl.type("novoCampo", "Deu certo?");
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}

	@Test
	public void testExplicitWait() throws InterruptedException {
		dsl.clickButton("ButtonDelay");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
		dsl.type("novoCampo", "Deu certo?");
	}
}
