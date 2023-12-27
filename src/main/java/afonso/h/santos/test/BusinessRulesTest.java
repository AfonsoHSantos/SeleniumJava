package afonso.h.santos.test;
import static afonso.h.santos.core.DriverFactory.getDriver;
import static afonso.h.santos.core.DriverFactory.killDriver;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import afonso.h.santos.core.BaseTest;
import afonso.h.santos.core.DSL;
import afonso.h.santos.page.CampoTreinamentoPage;

@RunWith(Parameterized.class)
public class BusinessRulesTest {
	
	private DSL dsl;
	private CampoTreinamentoPage page;

	@Parameter
	public String nome;
	@Parameter(value = 1)
	public String sobrenome;
	@Parameter(value = 2)
	public String sexo;
	@Parameter(value = 3)
	public List<String> comidas;
	@Parameter(value = 4)
	public String[] esportes;
	@Parameter(value = 5)
	public String msg;

	@Before
	public void before() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
		page = new CampoTreinamentoPage();
	}

	@Parameters
	public static Collection<Object[]> getCollection() {
		return Arrays.asList(new Object[][] { { "", "", "", Arrays.asList(), new String[] {}, "Nome eh obrigatorio" },
				{ "Wagner", "", "", Arrays.asList(), new String[] {}, "Sobrenome eh obrigatorio" },
				{ "Wagner", "Costa", "", Arrays.asList(), new String[] {}, "Sexo eh obrigatorio" },
				{ "Wagner", "Costa", "Masculino", Arrays.asList("Carne", "Vegetariano"), new String[] {},
						"Tem certeza que voce eh vegetariano?" },
				{ "Wagner", "Costa", "Masculino", Arrays.asList("Carne"),
						new String[] { "Karate", "O que eh esporte?" }, "Voce faz esporte ou nao?" } });
	}

	@Test
	public void validateRules() {
		page.setNome(nome);
		page.setSobrenome(sobrenome);
		if (sexo.equals("Masculino")) {
			page.setSexo("male");
		}
		if (sexo.equals("Feminino")) {
			page.setSexo("female");
		}
		if (comidas.contains("Carne"))
			page.setComida("Carne");
		if (comidas.contains("Pizza"))
			page.setComida("Pizza");
		if (comidas.contains("Vegetariano"))
			page.setComida("Vegetariano");
		page.setEsporte(esportes);
		page.clickCadastrarButton();
		System.out.println(msg);
		Assert.assertEquals(msg, dsl.getAlertTextAndAccept());
	}
}
