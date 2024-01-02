package managementtool.tests;

import managementtool.common.LoginBeforeTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import managementtool.pages.NewProjectPage;
import managementtool.pages.ProjectPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProjectCrudTest extends LoginBeforeTest {
    private static final String PROJECT_DESCRIPTION = "The SmartTask Management System revolutionizes task management through AI and cloud technologies. It provides an intuitive interface for efficient task handling, real-time collaboration, and streamlined project coordination, enhancing overall productivity for modern businesses.";
    private static final String UPDATED_PROJECT_DESCRIPTION = "The SmartTask Management System revolutionizes task management through AI and cloud technologies.";
    private static final String PROJECT_NAME = "Project one";

    @Order(1)
    @Test
    public void createProjectFromHomePage() {
        NewProjectPage newProjectPage = homePage.clickCreateProject();
        newProjectPage.clickBlankProject();
        newProjectPage.setProjectName(PROJECT_NAME);
        newProjectPage.clickViewList();
        ProjectPage projectPage = newProjectPage.clickSubmitButton(PROJECT_NAME);
        projectPage.clickOverviewTab();
        projectPage.setProjectDescription(PROJECT_DESCRIPTION);

        projectPage.clickOverviewTab();
        assertTrue(homePage.isProjectInSidebar(PROJECT_NAME));
    }

    @Order(2)
    @Test
    public void readProject() {
        ProjectPage projectPage = homePage.clickProject(PROJECT_NAME);
        projectPage.clickProjectActions();
        projectPage.clickEditProjectDetails();
        projectPage.checkTextPresentInDescription(PROJECT_DESCRIPTION);
    }

    @Order(3)
    @Test
    public void updateProject() {
        ProjectPage projectPage = homePage.clickProject(PROJECT_NAME);
        projectPage.clickProjectActions();
        projectPage.clickEditProjectDetails();
        projectPage.updateTextInDescription(UPDATED_PROJECT_DESCRIPTION);
        projectPage.checkTextPresentInDescription(UPDATED_PROJECT_DESCRIPTION);
    }

    @Order(4)
    @Test
    public void deleteProject() {
        ProjectPage projectPage = homePage.clickProject(PROJECT_NAME);
        projectPage.clickProjectActions();
        projectPage.deleteProject();

        String notificationMessage = projectPage.getNotificationMessage();
        assertTrue(notificationMessage.contains(PROJECT_NAME));
    }
}
