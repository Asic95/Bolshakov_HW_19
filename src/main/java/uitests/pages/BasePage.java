package uitests.pages;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import uitests.driver.WebDriverHolder;

public class BasePage {

    public WebDriverWait wait;
    public BasePage() {
        PageFactory.initElements(WebDriverHolder.getInstance().getDriver(), this);
    }

    public void sleep(long msec) throws InterruptedException {
        Thread.sleep(msec);
    }
}
