package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends BasePage {
    final By title = By.xpath("//span[@data-test='title']");
    final By title2 = By.xpath("//*[text() ='Products']");
    private static final By ADD_TO_CART_BUTTON = By.xpath("//*[text()='Add to cart']");
    private static final String ADD_TO_CART_BUTTON_PATTERN =
            ("//*[text()='%s'//ancestor::div[@class='inventiry_item']//child::button[text()='Add to cart']");
    private static final By CART_TO_BADGE = By.xpath("//*[@data-test = 'shopping-cart-badge']");
    private static final By PRODUCT_NAME_PATTERN = By.cssSelector(".inventory_item_name");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return driver.findElement(title).getText();
    }

    public boolean isTitlePresent() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title2)).isDisplayed();
    }

    public void addToCart(String GoodsName) {
        By addToCart = By.xpath(ADD_TO_CART_BUTTON_PATTERN.formatted(GoodsName));
        driver.findElement(addToCart).click();
    }

    public void addToCart(int GoodsIndex) {
        driver.findElements(ADD_TO_CART_BUTTON).get(GoodsIndex).click();
    }

    public String checkGoodsCountToBadge() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(CART_TO_BADGE)).getText();
    }

    public boolean getProductName(String ProductName){
        return driver.findElement(PRODUCT_NAME_PATTERN).isDisplayed();
    }

    public String getCountProducts() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(CART_TO_BADGE)).getText();
    }
}