package uitests.pages.shop_main;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
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

    @FindBy(css = "div[class='product-container'] div[class='right-block'] a[class='product-name']")
    private List<WebElement> productName;

    @FindBy(css = "div[class='product-container'] div[class='right-block'] div[class='content_price'] span[class='price product-price']")
    private List<WebElement> productPrice;

    @FindBy(css = "div[class='product-container'] div[class='functional-buttons clearfix'] div[class='compare'] a[class='add_to_compare']")
    private List<WebElement> productID;

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
        List<Product> productList = new ArrayList<>();

        for (int k = 0; k < productName.size(); k++) {
            String parsedName = String.valueOf(productName.get(k).getText());
            String parsedPrice = String.valueOf(productPrice.get(k).getText());
            Integer parsedID = Integer.valueOf(String.valueOf(productID.get(k).getAttribute("data-id-product")));

            Double productPrice = Double.valueOf(parsedPrice.replace("₴", "").replace(",", ".").replace(" ", ""));
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
