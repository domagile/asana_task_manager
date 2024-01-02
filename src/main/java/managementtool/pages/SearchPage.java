package managementtool.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends BasePage {
    @FindBy(xpath = "//span[contains(@class, 'TypographyPresentation') and contains(text(), 'Projects')]")
    private WebElement projectTab;

    protected SearchPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickProjectTab() {
        clickWithFluentWait(projectTab);
    }

    public void clickProject(String searchText) {
        By xpathSelector = By.xpath(String.format("//div[starts-with(@id, 'Search') and contains(text(), '%s')]", searchText));
        getWait().until(ExpectedConditions.visibilityOfElementLocated(xpathSelector));
        WebElement element = getDriver().findElement(xpathSelector);
        element.click();
    }
}
