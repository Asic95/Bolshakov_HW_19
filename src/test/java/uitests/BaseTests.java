package uitests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import uitests.driver.WebDriverHolder;
import uitests.utils.ConfigProvider;

import java.io.File;
import java.io.IOException;
import java.util.Date;

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

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        if (result.isSuccess()) {
            makeScreenshot(result.getName() + new Date().getTime());
        }
    }

    public File makeScreenshot(String screenshotName) {
        File screenshot = ((TakesScreenshot)WebDriverHolder.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);

        File file = new File("screenshots", screenshotName + ".png");
        try {
            FileUtils.copyFile(screenshot, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    public void goToPart(String part) {
        goToUrl(ConfigProvider.getInstance().getProperty("app.shop.url") + part);
    }
}
