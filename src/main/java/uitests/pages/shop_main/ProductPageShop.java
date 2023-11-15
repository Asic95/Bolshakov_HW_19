package uitests.pages.shop_main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import uitests.driver.WebDriverHolder;
import uitests.pages.BasePage;

import java.util.ArrayList;
import java.util.List;

public class ProductPageShop extends BasePage {

    int timeCounter = 0;
    public static String generalProductCount = "Showing 1 - 12 of 30 items";
    @FindBy(id = "selectProductSort")
    private WebElement sortByElement;

    @FindBy(id = "nb_item")
    private WebElement recordsPerPage;

    @FindBy(className = "product-count")
    private WebElement countOfProducts;

    @FindBy(css = "div[class='right-block'] a[class='product-name']")
    private WebElement productName;

    @FindBy(css = "div[class='right-block'] div[class='content_price'] span[class='price product-price']")
    private WebElement productPrice;

    public ProductPageShop() {
        super();
    }

    public void extendProductList() throws InterruptedException {

        Select select = new Select(recordsPerPage);
        select.selectByValue("60");
        do {
            sleep(300);
            timeCounter++;
        } while ((timeCounter < 3) || (countOfProducts.getText().equals(generalProductCount)));
    }

    public List<Product> getProducts() throws InterruptedException {
        extendProductList();
        List<WebElement> productItems = WebDriverHolder.getInstance().getDriver().findElements(By.xpath("//div[@class='product-container']"));
        List<Product> productList = new ArrayList<>();

        String xpathProductName = "div[class='right-block'] a[class='product-name']";
        String xpathProductPrice = "div[class='right-block'] div[class='content_price'] span[class='price product-price']";
        String xpathProductID = "div[class='functional-buttons clearfix'] div[class='compare'] a[class='add_to_compare']";

        for (WebElement product : productItems) {
            String parsedName = product.findElement(By.cssSelector(xpathProductName)).getText();
            String parsedPrice = product.findElement(By.cssSelector(xpathProductPrice)).getText();
            Integer parsedID = Integer.valueOf(product.findElement(By.cssSelector(xpathProductID)).getAttribute("data-id-product"));

            Double productPrice = Double.parseDouble(parsedPrice.replace("₴", "").replace(",", ".").replace(" ", ""));
            Product productModel = new Product().setName(parsedName).setPrice(productPrice).setID(parsedID);

            productList.add(productModel);
        }
        return productList;
    }

    public ProductPageShop sortBy(SortDirection sortDirection) throws InterruptedException {
        Select select = new Select(sortByElement);
        select.selectByValue(sortDirection.getValue());
        sleep(1500);
        return new ProductPageShop();
    }
}
