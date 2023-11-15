package uitests;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import uitests.driver.WebDriverHolder;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotListener extends TestListenerAdapter {

    @Override
    public void onTestSuccess(ITestResult result) {
        super.onTestSuccess(result);
        makeScreenshot(result.getName() + getFormattedTimestamp());
    }

    private String getFormattedTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
        return "_" + dateFormat.format(new Date());
    }

    private File makeScreenshot(String screenshotName) {
        File screenshot = ((TakesScreenshot) WebDriverHolder.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);

        File file = new File("screenshots", screenshotName + ".png");
        try {
            org.apache.commons.io.FileUtils.copyFile(screenshot, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }
}
