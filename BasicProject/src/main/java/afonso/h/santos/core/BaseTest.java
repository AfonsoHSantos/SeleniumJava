package afonso.h.santos.core;

import static afonso.h.santos.core.DriverFactory.getDriver;
import static afonso.h.santos.core.DriverFactory.killDriver;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import afonso.h.santos.pages.LoginPage;

public class BaseTest {
	
	private LoginPage loginPage = new LoginPage();
	
	@Rule
	public TestName testName = new TestName();
	
	@Before
	public void setUp() {
		loginPage.openLoginPage();
		
		loginPage.signIn("afonso@test.com", "test");
	}
	
	@After
	public void tearDown() throws IOException {
		TakesScreenshot ss = (TakesScreenshot)getDriver();
		File arquivo = ss.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(arquivo, new File("target" + File.separator + "screenshots"
				+ File.separator + testName.getMethodName() + ".jpg"));
		
		if(Properties.CLOSE_BROWSER) {
			killDriver();			
		}
	}

}
