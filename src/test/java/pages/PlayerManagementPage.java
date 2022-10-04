package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PlayerManagementPage extends BasePage {
    private static final String PLAYER_MANAGEMENT_PAGE_TITLE = "//a[text()='Player management']";
    private static final String PLAYERS_TABLE = "//table[@class='table table-hover table-striped table-bordered table-condensed']";
    private static final String PLAYER_NAME_SEARCH_FIELD = "//input[@name='PlayerSearch[name]']";
    private static final String PLAYERS_TABLE_BODY = "//table/tbody/tr";


    public Boolean isPlayerManagementPageTitlePresent() {
        return isElementPresent(PLAYER_MANAGEMENT_PAGE_TITLE);
    }

    public void inputDataInPlayerNameSearchField(String name) {
        WebElement playerNameSearch = findElementByXPath(PLAYER_NAME_SEARCH_FIELD);
        playerNameSearch.sendKeys(name);
        playerNameSearch.sendKeys(Keys.ENTER);
        waitUntilStalenessOf(findElementByXPath(PLAYERS_TABLE_BODY));
    }

    public Boolean isPlayersTablePresent() {
        return isElementPresent(PLAYERS_TABLE);
    }

    public List<WebElement> getRowsFromPlayersTable() {
        return findAllElementsByXPath(PLAYERS_TABLE_BODY);
    }
}
