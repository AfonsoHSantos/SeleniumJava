package afonso.h.santos.page;
import org.openqa.selenium.By;

import afonso.h.santos.core.BasePage;

public class CampoTreinamentoPage extends BasePage{

	public void setNome(String nome) {
		dsl.type("elementosForm:nome", nome);
	}

	public void setSobrenome(String sobrenome) {
		dsl.type("elementosForm:sobrenome", sobrenome);
	}

	public void setSexo(String gender) {
		if(gender == "male") {
			dsl.clickRadio("elementosForm:sexo:0");
		}
		else if (gender == "female") {
			dsl.clickRadio("elementosForm:sexo:1");
		}
	}

	public void setComida(String food) {
		switch (food) {
			case "Carne":
				dsl.clickRadio("elementosForm:comidaFavorita:0");
				break;
		
			case "Pizza":
				dsl.clickRadio("elementosForm:comidaFavorita:2");
				break;

			case "Vegetariano":
				dsl.clickRadio("elementosForm:comidaFavorita:3");
			break;	
		}
	}

	public void setEscolaridade(String valor) {
		dsl.selectCombo("elementosForm:escolaridade", valor);
	}

	public void setEsporte(String... valores) {
		for (String valor : valores)
			dsl.selectCombo("elementosForm:esportes", valor);
	}

	public void clickCadastrarButton() {
		dsl.clickButton("elementosForm:cadastrar");
	}

	public String getRegistrationResult() {
		return dsl.getText(By.xpath("//*[@id='resultado']/span"));
	}

	public String getRegisteredNome() {
		return dsl.getText(By.xpath("//*[@id='descNome']/span"));
	}

	public String getRegisteredSobrenome() {
		return dsl.getText(By.xpath("//*[@id='descSobrenome']/span"));
	}

	public String getRegisteredSexo() {
		return dsl.getText(By.xpath("//*[@id='descSexo']/span"));
	}

	public String getRegisteredComida() {
		return dsl.getText(By.xpath("//*[@id='descComida']/span"));
	}

	public String getRegisteredEscolaridade() {
		return dsl.getText(By.xpath("//*[@id='descEscolaridade']/span"));
	}

	public String getRegisteredEsportes() {
		return dsl.getText(By.xpath("//*[@id='descEsportes']/span"));
	}
}
