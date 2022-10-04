package drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.Constants;
import utils.PropertyLoader;

import java.util.concurrent.TimeUnit;

@Getter
public class DriverInit {

    private WebDriver driver;

    public WebDriver setDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(PropertyLoader.getPropertyValue(Constants.LOGIN_PROPERTIES, "url"));
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        return driver;
    }
}
