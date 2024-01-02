package managementtool.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage extends BasePage {
    protected HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(css = "*[class*='createNewProjectText']")
    private WebElement createNewProjectButton;

    @FindBy(css = "[class='SidebarFavoritesAndMore-favorites']")
    private WebElement starredSidebar;

    @FindBy(css = "[class='SidebarProjectsSectionCleanAndClearProjectList-projects']")
    private WebElement projectsSection;

    @FindBy(css = "[class*='TextInputBase SearchTextInput']")
    private WebElement searchTextInput;

    @FindBy(css = "[class*='HomePageContent-greeting']")
    private WebElement homePageGreeting;

    @FindBy(css = "[class*='addTaskButton']")
    private WebElement createTaskButton;

    @FindBy(xpath = "//textarea[@aria-label='Task Name']")
    WebElement addTaskInput;

    @FindBy(css = "[class*='Medium MyTasksWidgetContent-title']")
    WebElement myTasksTitle;

    public NewProjectPage clickCreateProject() {
        clickWithFluentWait(createNewProjectButton);
        return new NewProjectPage(getDriver(), getWait());
    }

    public String getHomePageGreetingText() {
        waitForElementVisibility(homePageGreeting);
        return homePageGreeting.getText();
    }

    private By getProjectSelector(String projectName) {
        String css = String.format("a[aria-label*=' Project'][aria-label*='%s']", projectName);
        return By.cssSelector(css);
    }

    public ProjectPage clickProject(String projectName) {
        By projectSelector = getProjectSelector(projectName);
        clickWithFluentWait(projectsSection);
        WebElement projectElement = projectsSection.findElement(projectSelector);
        projectElement.click();
        return new ProjectPage(getDriver(), getWait());
    }

    public boolean isProjectStarredInSidebar(String projectName) {
        By projectSelector = getProjectSelector(projectName);
        List<WebElement> projectElements = starredSidebar.findElements(projectSelector);
        return !projectElements.isEmpty() && projectElements.get(0).isDisplayed();
    }

    public boolean isProjectInSidebar(String projectName) {
        By projectSelector = getProjectSelector(projectName);
        WebElement projectElement = projectsSection.findElement(projectSelector);
        return projectElement.isDisplayed();
    }

    public SearchPage setSearchBox(String value) {
        waitForElementVisibility(homePageGreeting);
        clickWithFluentWait(searchTextInput);
        searchTextInput.sendKeys(value + Keys.ENTER);
        return new SearchPage(getDriver(), getWait());
    }

    public void clickCreateTask() {
        clickWithFluentWait(createTaskButton);
    }

    public void enterNewTask(String newTaskName) {
        clickWithFluentWait(addTaskInput);
        addTaskInput.sendKeys(newTaskName);
    }

    public MyTasksPage goToMyTasks() {
        clickWithFluentWait(myTasksTitle);
        return new MyTasksPage(getDriver(), getWait());
    }

    private WebElement getTaskNameElement(String taskName) {
        waitForElementVisibility(homePageGreeting);
        String xpath = String.format("//div[contains(text(), '%s')]", taskName);
        return getDriver().findElement(By.xpath(xpath));
    }
    public MyTasksPage openTaskWithDetails(String taskName) {
        waitForElementVisibility(homePageGreeting);
        getTaskNameElement(taskName).click();
        return new MyTasksPage(getDriver(), getWait());
    }
}
