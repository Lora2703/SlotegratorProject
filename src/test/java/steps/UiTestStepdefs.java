package steps;

import enums.LoginPageInputFields;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import pages.DashboardSidebar;
import pages.LoginPage;
import pages.PlayerManagementPage;
import utils.Constants;
import utils.PropertyLoader;

import java.util.List;

public class UiTestStepdefs extends BasePage {

    private static final LoginPage loginPage = new LoginPage();
    private static final DashboardSidebar dashboardPage = new DashboardSidebar();
    private static final PlayerManagementPage playerManagementPage = new PlayerManagementPage();

    @When("User enters the login in Login input field on Login Page")
    public void user_enter_the_login_in_login_input_field() {
        loginPage.inputDataToInputField(LoginPageInputFields.LOGIN, PropertyLoader.getPropertyValue
                (Constants.LOGIN_PROPERTIES, "login"));
    }

    @When("User enters the password in Password input field on Login Page")
    public void user_enter_the_password_in_password_input_field() {
        loginPage.inputDataToInputField(LoginPageInputFields.PASSWORD, PropertyLoader.getPropertyValue
                (Constants.LOGIN_PROPERTIES, "password"));
    }

    @When("User clicks on the Sign in button on Login Page")
    public void user_click_on_the_sign_in_button() {
        loginPage.clickSignInButton();
    }

    @Then("Casino Dashboard Page in opened")
    public void casino_dashboard_in_opened() {
        String actualClassName = dashboardPage.getClassNameOfDashboardButton();
        Assert.assertEquals("active",actualClassName);
    }

    @When("User clicks on the Users button on Dashboard Sidebar")
    public void userClicksOnTheUsersButtonOnDashboardSidebar() {
        dashboardPage.clickUsersButton();
    }

    @And("User clicks on the Players button on Dashboard Sidebar")
    public void userClicksOnThePlayersButtonOnDashboardSidebar() {
        dashboardPage.clickPlayersButton();
    }

    @Then("User see the Players table on Player management Page")
    public void userSeeThePlayersTableOnPlayerManagementPage() {
        Assert.assertTrue(playerManagementPage.isPlayerManagementPageTitlePresent());
        Assert.assertTrue(playerManagementPage.isPlayersTablePresent());
    }

    @And("User enters name in the Player name search field in the header of the table on Player management page")
    public void userEntersNameInThePlayerNameSearchFieldInTheHeaderOfTheTableOnPlayerManagementPage() {
        String playerName = PropertyLoader.getPropertyValue(Constants.TEST_DATA_PROPERTIES, "playerName");
        playerManagementPage.inputDataInPlayerNameSearchField(playerName);
    }

    @Then("Players table is sorted by the names")
    public void playersTableIsSortedByTheUsernames() {
        List<WebElement> rows = playerManagementPage.getRowsFromPlayersTable();
        String playerName = PropertyLoader.getPropertyValue(Constants.TEST_DATA_PROPERTIES, "playerName");
        for (WebElement row : rows) {
            System.out.println(row.getText());
            Assert.assertTrue(row.getText().contains(playerName));
        }
    }
}
