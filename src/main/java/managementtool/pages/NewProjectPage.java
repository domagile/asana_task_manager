package managementtool.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewProjectPage extends BasePage {
    @FindBy(xpath = "//div[@class='TileStructure-name' and text()='Blank project']")
    private WebElement blankProjectButton;

    @FindBy(css = "input[id='new_project_dialog_content_name_input']")
    private WebElement projectNameInput;

    @FindBy(xpath = "//div[@class= 'ProjectLayoutSelector-rowItem']//span[text()='List']")
    private WebElement viewListButton;

    @FindBy(xpath = "//div[@class= 'ProjectLayoutSelector-rowItem']//span[text()='Board']")
    private WebElement viewBoard;

    @FindBy(xpath = "//div[@class= 'ProjectLayoutSelector-rowItem']//span[text()='Calendar']")
    private WebElement viewCalendar;

    @FindBy(css = "div[class='PotSetupFormStructure-submitButton']")
    private WebElement submitButton;

    protected NewProjectPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickBlankProject() {
        clickWithFluentWait(blankProjectButton);
    }

    public void setProjectName(String projectName) {
        clickWithFluentWait(projectNameInput);
        projectNameInput.sendKeys(projectName);
    }

    public void clickViewList() {
        clickWithFluentWait(viewListButton);
    }

    public ProjectPage clickSubmitButton(String projectName) {
        clickWithFluentWait(submitButton);
        By projectSelector = By.cssSelector(String.format("a[aria-label*=' Project'][aria-label*=' %s']", projectName));
        getWait().until(ExpectedConditions.presenceOfElementLocated(projectSelector));
        return new ProjectPage(getDriver(), getWait());
    }
}
