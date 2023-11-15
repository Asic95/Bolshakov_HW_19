package uitests.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
    public static WebDriver getDriver(Browser browser) {
        WebDriver driver = null;
        switch (browser) {
            case CHROME:
                driver = new ChromeDriver();
                break;
            case EDGE:
                driver = new EdgeDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
        }
        driver.manage().window().maximize();
        return driver;
    }

    public static WebDriver getDriver() {
        Browser browser = null;
        try {
            browser = Browser.valueOf(System.getProperty("browser", "chrome").toUpperCase());
        } catch (IllegalArgumentException ex) {
            System.out.println("This driver is not supported.\nPlease choose: chrome, edge, firefox");
            System.exit(-1); // Щоб не йшло виконання далі
        }
        return getDriver(browser);
    }
}
