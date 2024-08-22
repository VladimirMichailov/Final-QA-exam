package lt.techin.lt.techin.vmichailov;

import lt.techin.vmichailov.AccountPage;
import lt.techin.vmichailov.LoginPage;
import lt.techin.vmichailov.RegistrationPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import utilities.RandomDataGenerator;

public class LoginPageTest extends BaseTest{

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/validLoginData.csv", numLinesToSkip = 1)
    public void loginUsingValidData(String userName, String password){

        LoginPage loginPage  = new LoginPage(driver);
        AccountPage accountPage = new AccountPage(driver);

        // Login
        // Send data to login form
        loginPage.inputUserName(userName);
        loginPage.inputPassword(password);

        // Click to log in
        loginPage.clickToLogin();
        // Assert login
        // By dashboard message
        Assertions.assertTrue(accountPage.getTextFromAccountArea().toLowerCase().contains(userName.toLowerCase()), "dashboard message does not contain " + userName);

    }

    @Test
    public void loginUsingNotRegistredCredentials(){
        LoginPage loginPage  = new LoginPage(driver);

        RandomDataGenerator.generateValidUserData();
        String randomUserName = RandomDataGenerator.getUserName();
        String randomPassword = RandomDataGenerator.getPassword();

        // Send data to login form
        loginPage.inputUserName(randomUserName);
        loginPage.inputPassword(randomPassword);

        // Click to log in
        loginPage.clickToLogin();

        // Assert not login by error message
        Assertions.assertEquals("Įvestas prisijungimo vardas ir/ arba slaptažodis yra neteisingi", loginPage.loginErrorMessage(), "messages are not matching");
    }

    @Test
    public void loginWithEmptyUserName(){
        LoginPage loginPage  = new LoginPage(driver);

        RandomDataGenerator.generateValidUserData();
        String randomUserName = RandomDataGenerator.getUserName();
        String randomPassword = RandomDataGenerator.getPassword();

        // Send data to login form
        loginPage.inputUserName("");
        loginPage.inputPassword(randomPassword);

        // Click to log in
        loginPage.clickToLogin();

        // Assert not login by error message
        Assertions.assertEquals("Įvestas prisijungimo vardas ir/ arba slaptažodis yra neteisingi", loginPage.loginErrorMessage(), "messages are not matching");
    }
    @Test
    public void loginWithEmptyPassword(){
        LoginPage loginPage  = new LoginPage(driver);

        RandomDataGenerator.generateValidUserData();
        String randomUserName = RandomDataGenerator.getUserName();
        String randomPassword = RandomDataGenerator.getPassword();

        // Send data to login form
        loginPage.inputUserName(randomUserName);
        loginPage.inputPassword("");

        // Click to log in
        loginPage.clickToLogin();

        // Assert not login by error message
        Assertions.assertEquals("Įvestas prisijungimo vardas ir/ arba slaptažodis yra neteisingi", loginPage.loginErrorMessage(), "messages are not matching");
    }

}
