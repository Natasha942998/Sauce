package pages;

import io.qameta.allure.Step;
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
    private static final By CART_BUTTON = By.xpath("//*[@data-test='shopping-cart-link']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверка названия заголовка страницы")
    public String getTitle() {
        return driver.findElement(title).getText();
    }

    @Step("Проверка отображения заголовка страницы")
    public boolean isTitlePresent() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title2)).isDisplayed();
    }

    public void addToCart(String goodsName) {
        By addToCart = By.xpath(ADD_TO_CART_BUTTON_PATTERN.formatted(goodsName));
        driver.findElement(addToCart).click();
    }

    public void addToCart(int goodsIndex) {
        driver.findElements(ADD_TO_CART_BUTTON).get(goodsIndex).click();
    }

    public String checkGoodsCountToBadge() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(CART_TO_BADGE)).getText();
    }

    public boolean getProductName(String productName){
        return driver.findElement(PRODUCT_NAME_PATTERN).isDisplayed();
    }

    public String getCountProducts() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(CART_TO_BADGE)).getText();
    }

    public void openCart() {
        driver.findElement(CART_BUTTON).click();
    }
}
