package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import parent.BaseTest;
import user.UserFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

public class AddGoodsToCartTest extends BaseTest {
    @Test
    public void addToCartItems() {
        loginPage.open();
        loginPage.login(UserFactory.withAdminPermission());
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
        loginPage.login(UserFactory.withAdminPermission());
        Assert.assertTrue(productsPage.isTitlePresent());
        for (int i = 0; i < count; i++) {
            productsPage.addToCart(0);
        }
        assertEquals(productsPage.getCountProducts(), String.valueOf(count));
    }

    @Test
    public void checkAddToCart() throws InterruptedException {
        loginPage.open();
        loginPage.login(UserFactory.withAdminPermission());
        assertTrue(productsPage.isTitlePresent());
        productsPage.addToCart(0);
        productsPage.addToCart(1);
        productsPage.addToCart(2);
        productsPage.openCart();
        assertTrue(cartPage.getProductsNames().contains("Sauce Labs Backpack"));
        assertEquals(cartPage.getProductsNames().size(), 3);
        assertFalse(cartPage.getProductsNames().isEmpty());
    }
}