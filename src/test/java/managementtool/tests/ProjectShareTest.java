package managementtool.tests;

import managementtool.common.LoginBeforeTest;

import org.junit.jupiter.api.Test;
import managementtool.pages.ProjectPage;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class ProjectShareTest extends LoginBeforeTest {
    private static final String EMAIL = "domagile5@gmail.com";

    @Test
    public void invitePeopleToProject() {
        ProjectPage projectPage = homePage.clickProject("Combine Sheets");
        projectPage.shareProject();
        projectPage.setEmailAddress(EMAIL);
        projectPage.clickSentEmailButton();

        String notificationMessage = projectPage.getNotificationMessage();
        assertTrue(notificationMessage.contains(EMAIL));
        projectPage.closeWindowWithInvite();
    }
}
