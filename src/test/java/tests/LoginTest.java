package tests;

import static org.testng.Assert.*;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import parent.BaseTest;
import user.User;
import user.UserFactory;
import utils.PropertyReader;

import java.time.Duration;

public class LoginTest extends BaseTest {
    @Test
    public void chekCorrectLogin() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        loginPage.open();
        loginPage.login(UserFactory.withAdminPermission());
        assertTrue(productsPage.isTitlePresent());
        assertEquals(productsPage.getTitle(), "Products");
    }

    @DataProvider()
    public Object[][] loginData() {
        return new Object[][]{
                {UserFactory.withLockedUserPermission(), PropertyReader.getProperty("locked.error_msg")},
                {UserFactory.withEmptyUserPermission(), PropertyReader.getProperty("empty_login.error_msg")},
                {UserFactory.withEmptyPasswordPermission(), PropertyReader.getProperty("empty_password.error_msg")},
                {UserFactory.withInCorrectUserPermission(), PropertyReader.getProperty("incorrect_data.error_msg")},
                {UserFactory.withInCorrectPasswordPermission(), PropertyReader.getProperty("incorrect_data.error_msg")},
        };
    }

    @Test(dataProvider = "loginData")
    public void chekIncorrectLogin(User user, String ErrorMsg) throws InterruptedException {
        loginPage.open();
        loginPage.login(user);
        assertEquals(loginPage.chekErrorMsg(), ErrorMsg);
    }
}