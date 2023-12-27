package afonso.h.santos.suites;

import static afonso.h.santos.core.DriverFactory.killDriver;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import afonso.h.santos.core.DriverFactory;
import afonso.h.santos.test.BusinessRulesTest;
import afonso.h.santos.test.RegistrationTest;

@RunWith(Suite.class)
@SuiteClasses({ RegistrationTest.class, BusinessRulesTest.class})
public class TestSuite {

	@AfterClass
	public static void tearDown() {
		killDriver();
	}
	
}
