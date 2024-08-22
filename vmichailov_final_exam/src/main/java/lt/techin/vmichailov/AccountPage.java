package lt.techin.vmichailov;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage extends  BasePage{
    public AccountPage(WebDriver driver) {
        super(driver);
    }
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    // Account info selectors
    @FindBy(xpath = "//nav[@class='navbar navbar-default']//ul[@class='nav navbar-nav navbar-right']")
    WebElement accountArea;

    // Calculator area selectors
    @FindBy(xpath = "//form[@id='number']/input[@name='sk1']")
    WebElement inputFirsNumber;

    @FindBy(xpath = "//form[@id='number']/input[@name='sk2']")
    WebElement inputSecondNumber;

    @FindBy(xpath = "//form[@id='number']/select[@name='zenklas']")
    WebElement selectOperation;

    @FindBy(xpath = "//form[@id='number']/input[@value='skaičiuoti']")
    WebElement countButton;

    // Input data tu Calculator

    public void inputFirstNumber(String num1){
        inputFirsNumber.clear();
        inputFirsNumber.sendKeys(num1);
    }
    public void inputSecondNumber(String num2){
        inputSecondNumber.clear();
        inputSecondNumber.sendKeys(num2);
    }

    public void selectDropdownByText (String text){Select dropdown = new Select(selectOperation);
       dropdown.selectByVisibleText(text);
    }

    public void clickToCalculate(){
        countButton.click();
    }










    // Account area methods
    // Logout
    public void logoutFromAccount(){
        accountArea.click();
    }
    // Get dashboard text
    public String getTextFromAccountArea(){
        return accountArea.getText();
    }



}
