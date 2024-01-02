package managementtool.tests;

import managementtool.common.LoginBeforeTest;
import org.junit.jupiter.api.Test;
import managementtool.pages.SearchPage;
import org.openqa.selenium.TimeoutException;

import static org.junit.jupiter.api.Assertions.fail;

public class ProjectSearchTest extends LoginBeforeTest {
    private static final String PROJECT_NAME = "Convert";

    @Test
    public void searchProject() {
        SearchPage searchPage = homePage.setSearchBox(PROJECT_NAME);
        searchPage.clickProjectTab();

        try {
            searchPage.clickProject(PROJECT_NAME);
        } catch (TimeoutException e) {
            fail("Project was not found", e);
        }
    }
}
