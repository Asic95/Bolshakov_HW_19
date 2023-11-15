package uitests.driver;

import org.openqa.selenium.WebDriver;
public class WebDriverHolder {
    private static WebDriverHolder instance = null;

    private WebDriver driver;

    WebDriverHolder() {
        driver = WebDriverFactory.getDriver();
    }

    public static WebDriverHolder getInstance() {
        if (instance == null) {
            instance = new WebDriverHolder();
        }
        return instance;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void driverQuit() {
        if (driver != null) {
            driver.quit();
        }
    }
}
