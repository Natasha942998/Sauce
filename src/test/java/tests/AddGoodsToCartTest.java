package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import parent.BaseTest;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

public class AddGoodsToCartTest extends BaseTest {
    @Test
    public void addToCartItems() {

        loginPage.open();
        loginPage.loginThryZip("standard_user", "secret_sauce");
        productsPage.getProductName("Sauce Labs Bolt T-Shirt");
        assertTrue(productsPage.getProductName("Sauce Labs Bolt T-Shirt"));
        productsPage.addToCart(3);
        productsPage.addToCart(4);
        productsPage.checkGoodsCountToBadge();
        assertTrue(productsPage.checkGoodsCountToBadge(), true);
        loginPage.open("cart.html");
    }

    @DataProvider
    public static Object[][] CountData() {
        return new Object[][]{
                {1},
                {2},
                {3}
        };
    }

    @Test(dataProvider = "CountData")
    public void checkAddProductsToCart(int count) {
        loginPage.open();
        loginPage.loginThryZip("standard_user", "secret_sauce");
        for (int i = 0; i < count; i++) {
            productsPage.addToCart(0);
        }
        assertEquals(productsPage.getCountProducts(), String.valueOf(count));
    }
}