package uitests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import uitests.driver.WebDriverHolder;
import uitests.utils.ConfigProvider;

public class BaseTests {

    @BeforeSuite
    public void beforeClass() {
        WebDriverHolder.getInstance().getDriver();
        goToPart("8-dresses");
    }

    @AfterSuite
    public void afterClass() {
        WebDriverHolder.getInstance().driverQuit();
    }

    public void goToUrl(String url) {
        WebDriverHolder.getInstance().getDriver().get(url);
    }

    public void goToPart(String part) {
        goToUrl(ConfigProvider.getInstance().getProperty("app.shop.url") + part);
    }
}
