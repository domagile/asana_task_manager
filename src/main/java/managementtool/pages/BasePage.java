package managementtool.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    protected BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected WebDriverWait getWait() {
        return wait;
    }

    private FluentWait<WebDriver> createFluentWait() {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
    }


    protected void clickWithFluentWait(WebElement element) {
        createFluentWait().until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected void waitForElementVisibility(WebElement element) {
        createFluentWait().until(ExpectedConditions.visibilityOf(element));
    }
}

