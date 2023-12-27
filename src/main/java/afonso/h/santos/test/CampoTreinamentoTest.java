package afonso.h.santos.test;
import static afonso.h.santos.core.DriverFactory.getDriver;
import static afonso.h.santos.core.DriverFactory.killDriver;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import afonso.h.santos.core.BaseTest;
import afonso.h.santos.core.DSL;

public class CampoTreinamentoTest extends BaseTest {

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
	public void testTextField() {
		dsl.type("elementosForm:nome", "Teste de escrita");
		Assert.assertEquals("Teste de escrita", dsl.getFieldValue("elementosForm:nome"));
	}

	@Test
	public void testDoubleTextFields() {
		dsl.type("elementosForm:nome", "Wagner");
		Assert.assertEquals("Wagner", dsl.getFieldValue("elementosForm:nome"));
		dsl.type("elementosForm:nome", "Aquino");
		Assert.assertEquals("Aquino", dsl.getFieldValue("elementosForm:nome"));
	}

	@Test
	public void interactWithTextArea() {
		dsl.type("elementosForm:sugestoes", "teste\n\naasldjdlks\nUltima line");
		Assert.assertEquals("teste\n\naasldjdlks\nUltima line", dsl.getFieldValue("elementosForm:sugestoes"));
	}

	@Test
	public void interactWithRadio() {
		dsl.clickRadio("elementosForm:sexo:0");
		Assert.assertTrue(dsl.isRadioChecked("elementosForm:sexo:0"));
	}

	@Test
	public void interactWithCheckbox() {
		dsl.clickCheck("elementosForm:comidaFavorita:2");
		Assert.assertTrue(dsl.isChecked("elementosForm:comidaFavorita:2"));
	}

	@Test
	public void interactWithCombo() {
		dsl.selectCombo("elementosForm:escolaridade", "2o grau completo");
		Assert.assertEquals("2o grau completo", dsl.getComboValue("elementosForm:escolaridade"));
	}

	@Test
	public void assertComboValues() {
		Assert.assertEquals(8, dsl.getOptionsQuantityCombo("elementosForm:escolaridade"));
		Assert.assertTrue(dsl.viewComboOption("elementosForm:escolaridade", "Mestrado"));
	}

	@Test
	public void assertMultipleComboValues() {
		dsl.selectCombo("elementosForm:esportes", "Natacao");
		dsl.selectCombo("elementosForm:esportes", "Corrida");
		dsl.selectCombo("elementosForm:esportes", "O que eh esporte?");

		List<String> opcoesMarcadas = dsl.getComboValues("elementosForm:esportes");
		Assert.assertEquals(3, opcoesMarcadas.size());

		dsl.unselectCombo("elementosForm:esportes", "Corrida");
		opcoesMarcadas = dsl.getComboValues("elementosForm:esportes");
		Assert.assertEquals(2, opcoesMarcadas.size());
		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));
	}

	@Test
	public void interactWithButton() {
		dsl.clickButton("ButtonSimple");
		Assert.assertEquals("Obrigado!", dsl.getValueElemento("ButtonSimple"));
	}

	@Test
	public void interactWithLink() {
		dsl.clickLink("Voltar");

		Assert.assertEquals("Voltou!", dsl.getText("resultado"));
	}

	@Test
	public void assertPageTexts() {
		Assert.assertEquals("Campo de Treinamento", dsl.getText(By.tagName("h3")));

		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.getText(By.className("facilAchar")));
	}

	@Test
	public void testJavascript() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via js'");
		js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");

		WebElement element = driver.findElement(By.id("elementosForm:nome"));
		js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
	}

	@Test
	public void InteractWithTableButtons() {
		dsl.clickButtonTable("Escolaridade", "Mestrado", "Radio", "elementosForm:tableUsuarios");
	}

}
