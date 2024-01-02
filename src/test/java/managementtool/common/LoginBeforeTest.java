package managementtool.common;

import managementtool.constants.Constants;
import managementtool.pages.HomePage;
import managementtool.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;

public class LoginBeforeTest {
    protected HomePage homePage;

    @BeforeEach
    void setUp() {
        performLogin();
    }

    private void performLogin() {
        LoginPage loginPage = LoginPage.open();
        loginPage.setEmail(Constants.Login.EMAIL);
        loginPage.clickContinue();
        loginPage.setPassword(Constants.Login.PASSWORD);
        homePage = loginPage.clickLogin();
    }
}
