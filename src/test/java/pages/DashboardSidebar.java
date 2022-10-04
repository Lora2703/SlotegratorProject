package pages;

public class DashboardSidebar extends BasePage {

    private static final String DASHBOARD_BUTTON = "//span[text()='Dashboard']/ancestor::li";
    private static final String USERS_BUTTON = "//span[text()='Users']";
    private static final String PLAYERS_BUTTON = "//a[text()='Players']";


    public String getClassNameOfDashboardButton() {
        return findElementByXPath(DASHBOARD_BUTTON).getAttribute("class");
    }

    public void clickUsersButton() {
        findElementByXPath(USERS_BUTTON).click();
    }

    public void clickPlayersButton() {
        findElementByXPath(PLAYERS_BUTTON).click();
    }


}
