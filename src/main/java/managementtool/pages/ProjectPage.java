package managementtool.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectPage extends BasePage {
    @FindBy(xpath = "(//span[text()='Overview'])[2]")
    private WebElement overviewTab;

    @FindBy(xpath = "//div[@role='document']")
    private WebElement projectDescriptionElement;

    @FindBy(xpath = "//div[@aria-label='Show options']")
    private WebElement projectActions;

    @FindBy(xpath = "//span[text()='Edit project details']")
    private WebElement editProjectDetailsButton;

    @FindBy(xpath = "//span[contains(text(), 'Delete project')]")
    private WebElement deleteProjectButton;

    @FindBy(xpath = "//div[contains(text(), 'Delete')]")
    private WebElement deletionConfirmation;

    @FindBy(css = "*[aria-label='Home'] span[class*='SidebarNavigationLinkCard']")
    private WebElement goToHomePageIcon;

    @FindBy(css = "p.ProsemirrorEditor-paragraph")
    private WebElement descriptionField;

    @FindBy(css = "[aria-label='Add to starred']")
    private WebElement addToStarredButton;

    @FindBy(css = "[aria-label='Remove from starred']")
    private WebElement removeFromStarredButton;

    @FindBy(css = "[class*='TextInput TextInput--medium']")
    private WebElement headerProjectTitle;

    @FindBy(css = "[class*='TextInputBase SizedTextInput']")
    private WebElement projectNameField;

    @FindBy(css = "[class*='ProjectShareButton-joinOrShare']")
    WebElement shareProjectButton;

    @FindBy(css = "[class*='Section--secondaryButton']")
    WebElement emailFieldActivationElement;

    @FindBy(css = "[class='TextInputBase TokenizerInput-input']")
    WebElement addEmailField;

    @FindBy(css = "[class*='PrimaryButton ModalFooter-button']")
    WebElement sentEmailButton;

    @FindBy(css = "[aria-label='Close this dialog']")
    WebElement closeWindowWithInviteButton;

    protected ProjectPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickOverviewTab() {
        clickWithFluentWait(overviewTab);
        new HomePage(getDriver(), getWait());
    }

    public void setProjectDescription(String description) {
        clickWithFluentWait(projectDescriptionElement);
        projectDescriptionElement.sendKeys(description);
    }

    public void clickProjectActions() {
        clickWithFluentWait(projectActions);
    }

    public void clickEditProjectDetails() {
        clickWithFluentWait(editProjectDetailsButton);
    }

    public void checkTextPresentInDescription(String expectedText) {
        waitForElementVisibility(descriptionField);
        String actualText = getWait().until(ExpectedConditions.visibilityOf(descriptionField)).getText().trim();
        assertEquals(expectedText.trim(), actualText);
    }

    public void updateTextInDescription(String updateText) {
        clickWithFluentWait(descriptionField);
        descriptionField.clear();
        descriptionField.sendKeys(updateText);
    }

    public void deleteProject() {
        clickWithFluentWait(deleteProjectButton);
        clickWithFluentWait(deletionConfirmation);
        new HomePage(getDriver(), getWait());
    }

    public void goToHomePage() {
        clickWithFluentWait(goToHomePageIcon);
    }

    public void addToStarred() {
        clickWithFluentWait(addToStarredButton);
    }

    public void removeFromStarred() {
        clickWithFluentWait(removeFromStarredButton);
    }

    public void renameProject(String projectName) {
        clickWithFluentWait(projectNameField);
        projectNameField.clear();
        projectNameField.sendKeys(projectName);
    }

    public void shareProject() {
        clickWithFluentWait(shareProjectButton);
    }

    public void setEmailAddress(String email){
        clickWithFluentWait(emailFieldActivationElement);
        waitForElementVisibility(addEmailField);
        addEmailField.sendKeys(email + Keys.ENTER);
    }

    public void clickSentEmailButton()
    {
        clickWithFluentWait(sentEmailButton);
    }

    public void closeWindowWithInvite()
    {
        waitForElementVisibility(closeWindowWithInviteButton);
        closeWindowWithInviteButton.click();
    }

    public String getNotificationMessage() {
        WebElement inviteMessage = getWait().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='ToastNotificationContent-text']")));
        return inviteMessage.getText();
    }
}
