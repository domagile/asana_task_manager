package managementtool.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyTasksPage extends BasePage {
    @FindBy(css = "[class*='TopbarPageHeaderStructure-titleAndNav']")
    WebElement topbarMyTasksButton;

    @FindBy(css = "[class*='ProsemirrorEditor--withDraggableContent']")
    WebElement taskDescriptionField;

    @FindBy(css = "[aria-label='Comments']")
    WebElement commentSection;

    @FindBy(css = "[aria-label='Add to projects']")
    private WebElement addTaskToProject;

    @FindBy(css = "[placeholder='Add task to a project...']")
    private WebElement addTaskToProjectInput;

    @FindBy(css = "[class*='DueDateTokenButton-label']")
    private WebElement dueDateButton;

    @FindBy(css = "[aria-label='Due date']")
    WebElement dueDateInput;

    @FindBy(xpath = "//div[(@class='DueDate--canWrap DueDate DueDateWithRecurrence-dueDate DueDate--future')]//span[contains(@class, 'DueDate-noWrapSegment')]")
    WebElement dueDateValue;

    @FindBy(css = "[class='TokenizerPillBase-name']")
    WebElement projectNameValue;

    @FindBy(css = "[aria-label='More actions for this task']")
    WebElement moreActionsButton;

    @FindBy(css = "[class*='Button-deleteTask']")
    WebElement deleteTaskButton;

    protected MyTasksPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }


    public WebElement getTaskNameElement(String taskName) {
        waitForElementVisibility(topbarMyTasksButton);
        return getDriver().findElement(By.xpath(String.format("//textarea[contains(., '%s')]", taskName)));
    }

    public void clickDetails(String taskName) {
        clickWithFluentWait(topbarMyTasksButton);
        String xpath = String.format("//textarea[contains(., '%s' )]/following::div[1]", taskName);
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
    }

    public void clearDescription() {
        waitForElementVisibility(taskDescriptionField);
        taskDescriptionField.clear();
    }

    public void addTaskDescription(String description) {
        waitForElementVisibility(taskDescriptionField);
        clickWithFluentWait(taskDescriptionField);
        taskDescriptionField.sendKeys(description);
    }

    public WebElement getTaskDescriptionElement(String description) {
        waitForElementVisibility(commentSection);
        String xpath = String.format("//p[contains(., '%s' )]", description);
        return getDriver().findElement(By.xpath(xpath));
    }

    public String getDueDateText() {
        waitForElementVisibility(dueDateValue);
        return dueDateValue.getText();
    }

    public String getProjectName() {
        waitForElementVisibility(projectNameValue);
        return projectNameValue.getText();
    }

    public void addTaskToProject(String projectName) {
        clickWithFluentWait(addTaskToProject);
        clickWithFluentWait(addTaskToProjectInput);
        addTaskToProjectInput.sendKeys(projectName + Keys.ENTER);
    }

    public void enterDueDate(String date) {
        clickWithFluentWait(dueDateButton);
        waitForElementVisibility(dueDateInput);
        dueDateInput.sendKeys(date + Keys.ENTER);

    }

    public void clickMoreOptions() {
        clickWithFluentWait(moreActionsButton);
    }

    public void deleteTask() {
        clickWithFluentWait(deleteTaskButton);
    }

    public String getDeletionNotification(String taskName) {
        WebElement deletionNotificationElement = getWait().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(String.format("//a[contains(@aria-label,'%s deleted')]", taskName))));
        return deletionNotificationElement.getText();
    }
}
