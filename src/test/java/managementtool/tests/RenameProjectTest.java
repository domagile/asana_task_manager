package managementtool.tests;

import managementtool.common.LoginBeforeTest;
import org.junit.jupiter.api.Test;
import managementtool.pages.NewProjectPage;
import managementtool.pages.ProjectPage;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class RenameProjectTest extends LoginBeforeTest {
    private static final String PROJECT_NAME = "Randomize";
    private static final String UPDATED_PROJECT_NAME = "Combine Sheets";

    @Test
    public void renameProject() {
        NewProjectPage newProjectPage = homePage.clickCreateProject();
        newProjectPage.clickBlankProject();
        newProjectPage.setProjectName(PROJECT_NAME);
        newProjectPage.clickViewList();
        ProjectPage projectPage = newProjectPage.clickSubmitButton(PROJECT_NAME);
        projectPage.clickOverviewTab();
        projectPage.goToHomePage();

        homePage.clickProject(PROJECT_NAME);
        projectPage.renameProject(UPDATED_PROJECT_NAME);

        assertTrue(homePage.isProjectInSidebar(UPDATED_PROJECT_NAME), "Project with updated name was not found");
    }
}
