package managementtool.pages;

import managementtool.constants.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class LoginPage extends BasePage {
    private static final String CHROMEDRIVER_PATH = "D:\\app\\chromedriver\\chromedriver.exe";

    @FindBy(css = "input[name='e']")
    private WebElement loginInputField;

    @FindBy(css = "input[name='p']")
    private WebElement passwordInputField;

    @FindBy(css = "div[class*='LoginEmailForm-continueButton']")
    private WebElement loginContinueButton;

    @FindBy(css = "form[class='LoginPasswordForm'] div[class*='NuxButton']")
    private WebElement loginSubmitButton;


    private LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public static LoginPage open() {
        ChromeDriverService service = new ChromeDriverService.Builder()
                .withLogOutput(System.out)
                .usingDriverExecutable(new File(CHROMEDRIVER_PATH))
                .build();
        WebDriver driver = new ChromeDriver(service);
        driver.get(Constants.Login.BASE_URL);
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return new LoginPage(driver, wait);
    }
    public void setEmail(String email) {
        clickWithFluentWait(loginInputField);
        loginInputField.sendKeys(email);
    }

    public void clickContinue() {
        loginContinueButton.click();
    }

    public void setPassword(String password) {
        clickWithFluentWait(passwordInputField);
        passwordInputField.sendKeys(password);
    }

    public HomePage clickLogin() {
        loginSubmitButton.click();
        return new HomePage(getDriver(), getWait());
    }
}
