package lt.techin.lt.techin.vmichailov;

import lt.techin.vmichailov.AccountPage;
import lt.techin.vmichailov.LoginPage;
import org.junit.jupiter.api.Test;

public class CalculatorPageTest extends BaseTest{

    @Test
    public void countSumOfNumbers(){
        LoginPage loginPage = new LoginPage(driver);
        AccountPage accountPage = new AccountPage(driver);

        // Login
        // Send data to login form
        loginPage.inputUserName("testas10");
        loginPage.inputPassword("testas10");

        // Click to log in
        loginPage.clickToLogin();

        int num1 = 10;
        accountPage.inputFirstNumber(String.valueOf(num1));
        int num2 = 5;
        accountPage.inputSecondNumber(String.valueOf(num2));
        accountPage.selectDropdownByText("Sudėtis");
        accountPage.clickToCalculate();
    }

    @Test
    public void differenceOfNumbers(){
        LoginPage loginPage = new LoginPage(driver);
        AccountPage accountPage = new AccountPage(driver);

        // Login
        // Send data to login form
        loginPage.inputUserName("testas10");
        loginPage.inputPassword("testas10");

        // Click to log in
        loginPage.clickToLogin();

        int num1 = 10;
        accountPage.inputFirstNumber(String.valueOf(num1));
        int num2 = 5;
        accountPage.inputSecondNumber(String.valueOf(num2));
        accountPage.selectDropdownByText("Atimtis");
        accountPage.clickToCalculate();
    }

    @Test
    public void multiplicationOfNumbers(){
        LoginPage loginPage = new LoginPage(driver);
        AccountPage accountPage = new AccountPage(driver);

        // Login
        // Send data to login form
        loginPage.inputUserName("testas10");
        loginPage.inputPassword("testas10");

        // Click to log in
        loginPage.clickToLogin();

        int num1 = 10;
        accountPage.inputFirstNumber(String.valueOf(num1));
        int num2 = 5;
        accountPage.inputSecondNumber(String.valueOf(num2));
        accountPage.selectDropdownByText("Daugyba");
        accountPage.clickToCalculate();
    }

    @Test
    public void divisionOfNumbers(){
        LoginPage loginPage = new LoginPage(driver);
        AccountPage accountPage = new AccountPage(driver);

        // Login
        // Send data to login form
        loginPage.inputUserName("testas10");
        loginPage.inputPassword("testas10");

        // Click to log in
        loginPage.clickToLogin();

        int num1 = 10;
        accountPage.inputFirstNumber(String.valueOf(num1));
        int num2 = 5;
        accountPage.inputSecondNumber(String.valueOf(num2));
        accountPage.selectDropdownByText("Dalyba");
        accountPage.clickToCalculate();
    }



}
