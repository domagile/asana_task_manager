package managementtool.tests;

import managementtool.common.LoginBeforeTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.WebElement;
import managementtool.pages.MyTasksPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MyTasksCrudTest extends LoginBeforeTest {
    private final static String TASK_NAME = "Test valid email login";
    private static final String TASK_DESCRIPTION = "Validate the user input for accuracy";
    private static final String UPDATED_TASK_DESCRIPTION = "Verify the accuracy of user input";

    @Order(1)
    @Test
    public void createTask() {
        homePage.clickCreateTask();
        homePage.enterNewTask(TASK_NAME);
        MyTasksPage myTasksPage = homePage.goToMyTasks();
        myTasksPage.clickDetails(TASK_NAME);
        myTasksPage.addTaskDescription(TASK_DESCRIPTION);
        WebElement taskElement = myTasksPage.getTaskNameElement(TASK_NAME);
        assertEquals(TASK_NAME, taskElement.getText());
    }

    @Order(2)
    @Test
    public void readTask() {
        MyTasksPage myTasksPage = homePage.openTaskWithDetails(TASK_NAME);
        WebElement descriptionOfTask = myTasksPage.getTaskDescriptionElement(TASK_DESCRIPTION);
        assertEquals(TASK_DESCRIPTION, descriptionOfTask.getText());
    }

    @Order(3)
    @Test
    public void updateTask() {
        MyTasksPage myTasksPage = homePage.goToMyTasks();
        myTasksPage.clickDetails(TASK_NAME);
        myTasksPage.enterDueDate("10.11.24");
        myTasksPage.addTaskToProject("Randomize");
        myTasksPage.clearDescription();
        myTasksPage.addTaskDescription(UPDATED_TASK_DESCRIPTION);

        WebElement taskDescriptionElement = myTasksPage.getTaskDescriptionElement(UPDATED_TASK_DESCRIPTION);
        assertEquals(UPDATED_TASK_DESCRIPTION, taskDescriptionElement.getText());

        String expectedProjectName = "Randomize";
        String projectName = myTasksPage.getProjectName();
        assertEquals(projectName, expectedProjectName);

        String expectDate = "Oct 11, 2024";
        String date = myTasksPage.getDueDateText();
        assertEquals(expectDate, date);
    }

    @Order(4)
    @Test
    public void deleteTask() {
        MyTasksPage myTasksPage = homePage.goToMyTasks();
        myTasksPage.clickDetails(TASK_NAME);
        myTasksPage.clickMoreOptions();
        myTasksPage.deleteTask();

        String deletionNotification = myTasksPage.getDeletionNotification(TASK_NAME);
        assertTrue(deletionNotification.contains(TASK_DESCRIPTION));
    }
}
