package managementtool.tests;

import managementtool.common.LoginBeforeTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginTest extends LoginBeforeTest {
    @Test
    public void login() {
        String expectedGreeting = "Good .* Olga";
        String actualGreeting = homePage.getHomePageGreetingText();
        assertTrue(actualGreeting.matches(expectedGreeting));
    }
}



