package tests;

import static org.testng.Assert.*;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import parent.BaseTest;

import java.time.Duration;

public class LoginTest extends BaseTest {
    @Test
    public void chekCorrectLogin() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        loginPage.open();
        loginPage.loginThryZip("standard_user", "secret_sauce");
        assertTrue(productsPage.isTitlePresent());
        assertEquals(productsPage.getTitle(), "Products");
    }

    @DataProvider()
    public Object[][] loginData() {
        return new Object[][]{
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
        };
    }

    @Test(dataProvider = "loginData")
    public void chekIncorrectLogin(String user, String password, String errorMsg) throws InterruptedException {
        loginPage.open();
        loginPage.loginThryZip(user, password);
        assertEquals(loginPage.chekErrorMsg(), errorMsg);
    }
}
