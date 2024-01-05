package afonso.h.santos.core;
import static afonso.h.santos.core.DriverFactory.getDriver;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {

	/* TextFields and Text Areas */

	public void type(By by, String Text) {
		getDriver().findElement(by).clear();
		getDriver().findElement(by).sendKeys(Text);
	}

	public void type(String id_campo, String Text) {
		type(By.id(id_campo), Text);
	}

	public String getFieldValue(String id_campo) {
		return getDriver().findElement(By.id(id_campo)).getAttribute("value");
	}

	/* Radio and Checkboxes */

	public void clickRadio(By by) {
		getDriver().findElement(by).click();
	}

	public void clickRadio(String id) {
		clickRadio(By.id(id));
	}

	public boolean isRadioChecked(String id) {
		return getDriver().findElement(By.id(id)).isSelected();
	}

	public void clickCheck(String id) {
		getDriver().findElement(By.id(id)).click();
	}

	public boolean isChecked(String id) {
		return getDriver().findElement(By.id(id)).isSelected();
	}

	/* Combos */

	public void selectCombo(String id, String valor) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		combo.selectByVisibleText(valor);
	}

	public void unselectCombo(String id, String valor) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		combo.deselectByVisibleText(valor);
	}

	public String getComboValue(String id) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}

	public List<String> getComboValues(String id) {
		WebElement element = getDriver().findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		List<String> valores = new ArrayList<String>();
		for (WebElement opcao : allSelectedOptions) {
			valores.add(opcao.getText());
		}
		return valores;
	}

	public int getOptionsQuantityCombo(String id) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		return options.size();
	}

	public boolean viewComboOption(String id, String opcao) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		for (WebElement option : options) {
			if (option.getText().equals(opcao)) {
				return true;
			}
		}
		return false;
	}

	public void selectPrimeCombo(String radical, String valor) {
		clickRadio(By.xpath("//*[@id='" + radical + "_input']/../..//span"));
		clickRadio(By.xpath("//*[@id='" + radical + "_items']//li[.='" + valor + "']"));
	}

	/* Buttons */

	public void clickButton(String id) {
		getDriver().findElement(By.id(id)).click();
	}

	public String getValueElemento(String id) {
		return getDriver().findElement(By.id(id)).getAttribute("value");
	}

	/* Links */

	public void clickLink(String link) {
		getDriver().findElement(By.linkText(link)).click();
	}

	/* Texts */

	public String getText(By by) {
		return getDriver().findElement(by).getText();
	}

	public String getText(String id) {
		return getText(By.id(id));
	}

	/* Alerts */

	public String getAlertText() {
		Alert alert = getDriver().switchTo().alert();
		return alert.getText();
	}

	public String getAlertTextAndAccept() {
		Alert alert = getDriver().switchTo().alert();
		String valor = alert.getText();
		alert.accept();
		return valor;

	}

	public String getAlertTextAndReject() {
		Alert alert = getDriver().switchTo().alert();
		String valor = alert.getText();
		alert.dismiss();
		return valor;

	}

	public void typeAlert(String valor) {
		Alert alert = getDriver().switchTo().alert();
		alert.sendKeys(valor);
		alert.accept();
	}

	/* Frames and Windows */

	public void selectFrame(String id) {
		getDriver().switchTo().frame(id);
	}

	public void exitFrame() {
		getDriver().switchTo().defaultContent();
	}

	public void switchWindow(String id) {
		getDriver().switchTo().window(id);
	}

	/* JS */

	public Object executeJS(String cmd, Object... param) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		return js.executeScript(cmd, param);
	}

	/* Tables */

	public void clickButtonTable(String rowBusca, String valor, String rowButton, String idTable) {
		WebElement table = getDriver().findElement(By.xpath("//*[@id='elementosForm:tableUsuarios']"));
		int idRow = getRowIndex(rowBusca, table);

		int idLines = getLineIndex(valor, table, idRow);

		int idRowButton = getRowIndex(rowButton, table);

		WebElement cell = table.findElement(By.xpath(".//tr[" + idLines + "]/td[" + idRowButton + "]"));
		cell.findElement(By.xpath(".//input")).click();

	}

	protected int getLineIndex(String valor, WebElement table, int idRow) {
		List<WebElement> lines = table.findElements(By.xpath("./tbody/tr/td[" + idRow + "]"));
		int idLines = -1;
		for (int i = 0; i < lines.size(); i++) {
			if (lines.get(i).getText().equals(valor)) {
				idLines = i + 1;
				break;
			}
		}
		return idLines;
	}

	protected int getRowIndex(String row, WebElement table) {
		List<WebElement> rows = table.findElements(By.xpath(".//th"));
		int idRow = -1;
		for (int i = 0; i < rows.size(); i++) {
			if (rows.get(i).getText().equals(row)) {
				idRow = i + 1;
				break;
			}
		}
		return idRow;
	}
}
