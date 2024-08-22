package lt.techin.vmichailov;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "/html//div[@class='container']/form[@action='/prisijungti']//a[@href='/registruoti']")
    WebElement createNewAccountLink;

    // Login form selectors
    @FindBy(xpath = "/html//form[@action='/prisijungti']//input[@name='username']")
    WebElement loginUserNameInput;

    @FindBy(xpath = "/html//form[@action='/prisijungti']//input[@name='password']")
    WebElement loginPasswordInput;

    @FindBy(xpath = "/html//form[@action='/prisijungti']//button[@type='submit']")
    WebElement loginButton;

    @FindBy(xpath = "/html//form[@action='/prisijungti']/div/span[2]")
    WebElement loginErrorMessage;






    // Click to go Registration page
    public void goToRegistrationPage(){
        createNewAccountLink.click();

    }

    // Login form methods
    public void inputUserName(String userName){
        loginUserNameInput.clear();
        loginUserNameInput.sendKeys(userName);
    }

    public void inputPassword(String password){
        loginPasswordInput.clear();
        loginPasswordInput.sendKeys(password);
    }

    public void clickToLogin(){
        loginButton.click();
    }

    // Get text from error message

    public String loginErrorMessage(){
        return loginErrorMessage.getText();
    }













}