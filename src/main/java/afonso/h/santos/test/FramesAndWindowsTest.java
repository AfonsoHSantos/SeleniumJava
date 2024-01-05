package afonso.h.santos.test;
import static afonso.h.santos.core.DriverFactory.getDriver;
import static afonso.h.santos.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import afonso.h.santos.core.BaseTest;
import afonso.h.santos.core.DSL;

public class FramesAndWindowsTest extends BaseTest {

	private WebDriver driver;
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
	public void interactWithFrame() {
		dsl.selectFrame("frame1");
		dsl.clickButton("frameButton");
		String msg = dsl.getAlertTextAndAccept();
		Assert.assertEquals("Frame OK!", msg);

		dsl.exitFrame();
		dsl.type("elementosForm:nome", msg);
	}

	@Test
	public void interactWithHiddenFrame() {
		WebElement frame = driver.findElement(By.id("frame2"));
		dsl.executeJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);
		dsl.selectFrame("frame2");
		dsl.clickButton("frameButton");
		String msg = dsl.getAlertTextAndAccept();
		Assert.assertEquals("Frame OK!", msg);
	}

	@Test
	public void interactWithWindows() {
		dsl.clickButton("ButtonPopUpEasy");
		dsl.switchWindow("Popup");
		dsl.type(By.tagName("Textarea"), "Deu certo?");
		driver.close();
		dsl.switchWindow("");
		dsl.type(By.tagName("Textarea"), "e agora?");
	}

	@Test
	public void interactWithUnnamedWindow() {
		dsl.clickButton("ButtonPopUpHard");
		System.out.println(driver.getWindowHandle());
		System.out.println(driver.getWindowHandles());
		dsl.switchWindow((String) driver.getWindowHandles().toArray()[1]);
		dsl.type(By.tagName("Textarea"), "Deu certo?");
		dsl.switchWindow((String) driver.getWindowHandles().toArray()[0]);
		dsl.type(By.tagName("Textarea"), "e agora?");
	}
}
