package hooks;

import drivers.DriverInit;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class CucumberHook {

    private WebDriver driver;

    @Before
    public void getDriver() {
        driver = new DriverInit().setDriver();
        BasePage.setDriverThreadLocal(driver);
    }

    @After
    public void quitDriver() {
        if (driver != null)
            BasePage.getDriverThreadLocal().quit();
    }
}
