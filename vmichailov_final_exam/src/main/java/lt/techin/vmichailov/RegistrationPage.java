package lt.techin.vmichailov;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class RegistrationPage extends BasePage{

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    //Credentials input selectors

    @FindBy(xpath = "/html//input[@id='username']")
    WebElement loginNameInput;

    @FindBy(xpath = "/html//input[@id='password']")
    WebElement passwordInput;

    @FindBy(xpath = "/html//input[@id='passwordConfirm']")
    WebElement repeatPasswordInput;

    // Create account button
    @FindBy(xpath = "//form[@id='userForm']/button[@type='submit']")
    WebElement createAccountButton;

    // Username error messages
    @FindBy(xpath = "/html//span[@id='username.errors']")
    WebElement userNameErrorMessage;

    // Password warning message
    @FindBy(xpath = "/html//span[@id='password.errors']")
    WebElement passwordErrorMessage;

    // Repeat password warning message
    @FindBy(xpath = "/html//span[@id='passwordConfirm.errors']")
    WebElement repeatPasswordErrorMessage;



    // Fill registration form with data
    // Send login name
    public void inputLoginName(String loginName) {
        loginNameInput.clear();
        loginNameInput.sendKeys(loginName);
    }

    // Send password
    public void inputPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }


    // Repeat password
    public void inputRepeatPassword(String password) {
        repeatPasswordInput.clear();
        repeatPasswordInput.sendKeys(password);
    }

    // Finish registration
    public void clickToCreateAccount(){
        createAccountButton.click();
    }

    //Get text from username error message
    public String userNameErrorText(){
        return userNameErrorMessage.getText();
    }
    //Get text from password error message
    public String passwordErrorText(){
        return passwordErrorMessage.getText();
    }

    //Get text from password error message
    public String repeatPasswordErrorText(){
        return repeatPasswordErrorMessage.getText();
    }

    // Create csv file with login data
    public void writeValidDataToCSV(File file, List<String[]> validLoginData) {
        boolean fileExists = file.exists();

        try (FileWriter out = new FileWriter(file, true); // Open in append mode
             CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withRecordSeparator("\n"))) {

            // Add header only if the file does not exist or is empty
            if (!fileExists || Files.size(file.toPath()) == 0) {
                printer.printRecord("userName", "password");
            }

            for (String[] record : validLoginData) {
                printer.printRecord((Object[]) record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
