package managementtool.tests;

import managementtool.common.LoginBeforeTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import managementtool.pages.ProjectPage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FavoritesTest extends LoginBeforeTest {
    private static final String PROJECT_NAME = "Convert";
    @Order(1)
    @Test
    public void testAddProjectToFavorites() {
        ProjectPage projectPage = homePage.clickProject(PROJECT_NAME);
        projectPage.addToStarred();
        assertTrue(homePage.isProjectStarredInSidebar(PROJECT_NAME));

    }

    @Order(2)
    @Test
    public void testRemoveProjectFromFavorites() {
        ProjectPage projectPage = homePage.clickProject(PROJECT_NAME);
        projectPage.removeFromStarred();
        assertFalse(homePage.isProjectStarredInSidebar(PROJECT_NAME));
    }
}

