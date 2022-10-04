package pages;

import enums.LoginPageInputFields;

public class LoginPage extends BasePage {

    private static final String LOGIN_PAGE_INPUT_FIELD = "//input[@id='%s']";
    private static final String SIGN_IN_BUTTON = "//input[@value='Sign in']";

    public void inputDataToInputField(LoginPageInputFields field, String data) {
        findElementByXPath(String.format(LOGIN_PAGE_INPUT_FIELD, field.toString())).sendKeys(data);
    }

    public void clickSignInButton() {
        findElementByXPath(SIGN_IN_BUTTON).click();
    }
}
