package lt.techin.lt.techin.vmichailov;

import lt.techin.vmichailov.AccountPage;
import lt.techin.vmichailov.LoginPage;
import lt.techin.vmichailov.RegistrationPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utilities.RandomDataGenerator;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RegistrationPageTest extends BaseTest{

    // Positive tests
    @Test
    void registerUsingValidRandomDataAndGenerateCsv(){
        LoginPage loginPage  = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        AccountPage accountPage = new AccountPage(driver);

        // Go to registration page
        loginPage.goToRegistrationPage();

        // Assert redirection
        Assertions.assertEquals("http://localhost:8080/registruoti", driver.getCurrentUrl(), "web addresses after clicking create account link not match");

        // Input registration credentials
        // Generate user data
        RandomDataGenerator.generateValidUserData();
        String randomUserName = RandomDataGenerator.getUserName();
        String randomPassword = RandomDataGenerator.getPassword();

        // Input registration data
        registrationPage.inputLoginName(randomUserName);
        registrationPage.inputPassword(randomPassword);
        registrationPage.inputRepeatPassword(randomPassword);

        // Safe login data to csv file
        List<String[]> validLoginData = new ArrayList<>();
        validLoginData.add(new String[]{randomUserName, randomPassword});
        String filePath = Paths.get("C:", "Users", "Vartotojas", "Desktop", "QA praktikos egzaminas", "vmichailov_final_exam", "src", "test", "resources", "validLoginData.csv").toString();
        File csvFile = new File(filePath);

        registrationPage.writeValidDataToCSV(csvFile, validLoginData);

        // Finish registration
        registrationPage.clickToCreateAccount();

        // Assert registration
        // By URL
        Assertions.assertEquals("http://localhost:8080/skaiciuotuvas", driver.getCurrentUrl(), "we were not redirected to account page");
        // By dashboard message
        Assertions.assertTrue(accountPage.getTextFromAccountArea().toLowerCase().contains(randomUserName.toLowerCase()), "dashboard message does not contain " + randomUserName);


    }

    @Test
    void registerValidRandomDataAndThenLogin(){
        LoginPage loginPage  = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        AccountPage accountPage = new AccountPage(driver);

        // Go to registration page
        loginPage.goToRegistrationPage();

        // Assert redirection
        Assertions.assertEquals("http://localhost:8080/registruoti", driver.getCurrentUrl(), "web addresses after clicking create account link not match");

        // Input registration credentials
        // Generate user data
        RandomDataGenerator.generateValidUserData();
        String randomUserName = RandomDataGenerator.getUserName();
        String randomPassword = RandomDataGenerator.getPassword();

        // Input registration data
        registrationPage.inputLoginName(randomUserName);
        registrationPage.inputPassword(randomPassword);
        registrationPage.inputRepeatPassword(randomPassword);

        // Finish registration
        registrationPage.clickToCreateAccount();

        // Assert registration
        // By Url
        Assertions.assertEquals("http://localhost:8080/skaiciuotuvas", driver.getCurrentUrl(), "we were not redirected to account page");
        // By dashboard message
        Assertions.assertTrue(accountPage.getTextFromAccountArea().toLowerCase().contains(randomUserName.toLowerCase()), "dashboard message does not contain " + randomUserName);

        // Logout
        accountPage.logoutFromAccount();

        // Login
        // Send data to login form
        loginPage.inputUserName(randomUserName);
        loginPage.inputPassword(randomPassword);

        // Click to log in
        loginPage.clickToLogin();
        // Assert login
        // By dashboard message
        Assertions.assertTrue(accountPage.getTextFromAccountArea().toLowerCase().contains(randomUserName.toLowerCase()), "dashboard message does not contain " + randomUserName);

    }

    // Negative tests

    @Test
    public void registerUsingSameUserName(){

        LoginPage loginPage  = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        AccountPage accountPage = new AccountPage(driver);

        // Go to registration page
        loginPage.goToRegistrationPage();

        // Assert redirection
        Assertions.assertEquals("http://localhost:8080/registruoti", driver.getCurrentUrl(), "web addresses after clicking create account link not match");

        // Input registration credentials
        // Generate user data
        RandomDataGenerator.generateValidUserData();
        String randomUserName = RandomDataGenerator.getUserName();
        String randomPassword = RandomDataGenerator.getPassword();

        // Input registration data
        registrationPage.inputLoginName(randomUserName);
        registrationPage.inputPassword(randomPassword);
        registrationPage.inputRepeatPassword(randomPassword);

        // Finish registration
        registrationPage.clickToCreateAccount();

        // Assert registration
        // By Url
        Assertions.assertEquals("http://localhost:8080/skaiciuotuvas", driver.getCurrentUrl(), "we were not redirected to account page");
        // By dashboard message
        Assertions.assertTrue(accountPage.getTextFromAccountArea().toLowerCase().contains(randomUserName.toLowerCase()), "dashboard message does not contain " + randomUserName);

        // Logout
        accountPage.logoutFromAccount();

        // Register again using same data

        // Go to registration page
        loginPage.goToRegistrationPage();

        // Assert redirection
        Assertions.assertEquals("http://localhost:8080/registruoti", driver.getCurrentUrl(), "web addresses after clicking create account link not match");

        // Input registration data
        registrationPage.inputLoginName(randomUserName);
        registrationPage.inputPassword(randomPassword);
        registrationPage.inputRepeatPassword(randomPassword);

        // Finish registration
        registrationPage.clickToCreateAccount();

        // Assert we were not redirected to account page
        Assertions.assertNotEquals("http://localhost:8080/skaiciuotuvas", driver.getCurrentUrl(), "we are redirected to account page but should not");
        // Assert we get error message
        Assertions.assertEquals("Toks vartotojo vardas jau egzistuoja", registrationPage.userNameErrorText(), "messages are not matching");
    }

    @Test
    public void registerWithEmptyUserName(){
        LoginPage loginPage  = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);

        // Go to registration page
        loginPage.goToRegistrationPage();

        // Assert redirection
        Assertions.assertEquals("http://localhost:8080/registruoti", driver.getCurrentUrl(), "web addresses after clicking create account link not match");

        // Input registration credentials
        // Generate user data
        RandomDataGenerator.generateValidUserData();
        String randomPassword = RandomDataGenerator.getPassword();

        // Input registration data
        registrationPage.inputLoginName("");
        registrationPage.inputPassword(randomPassword);
        registrationPage.inputRepeatPassword(randomPassword);

        // Finish registration
        registrationPage.clickToCreateAccount();

        // Assert we were not redirected to account page
        Assertions.assertNotEquals("http://localhost:8080/skaiciuotuvas", driver.getCurrentUrl(), "we are redirected to account page but should not");
        // Assert we get error message
        Assertions.assertEquals("Šį laukelį būtina užpildyti\n" +
                "Privaloma įvesti nuo 3 iki 32 simbolių", registrationPage.userNameErrorText(), "messages are not matching");
    }

    @Test
    public void registerWithTooShortUserName(){
        LoginPage loginPage  = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);

        // Go to registration page
        loginPage.goToRegistrationPage();

        // Assert redirection
        Assertions.assertEquals("http://localhost:8080/registruoti", driver.getCurrentUrl(), "web addresses after clicking create account link not match");

        // Input registration credentials
        // Generate user data
        RandomDataGenerator.generateValidUserData();
        String randomUserName = RandomDataGenerator.getShortUserName();
        String randomPassword = RandomDataGenerator.getPassword();

        // Input registration data
        registrationPage.inputLoginName(randomUserName);
        registrationPage.inputPassword(randomPassword);
        registrationPage.inputRepeatPassword(randomPassword);

        // Finish registration
        registrationPage.clickToCreateAccount();

        // Assert we were not redirected to account page
        Assertions.assertNotEquals("http://localhost:8080/skaiciuotuvas", driver.getCurrentUrl(), "we are redirected to account page but should not");
        // Assert we get error message
        Assertions.assertEquals("Privaloma įvesti nuo 3 iki 32 simbolių", registrationPage.userNameErrorText(), "messages are not matching");
    }

    @Test
    public void registerWithTooLongUserName(){
        LoginPage loginPage  = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);

        // Go to registration page
        loginPage.goToRegistrationPage();

        // Assert redirection
        Assertions.assertEquals("http://localhost:8080/registruoti", driver.getCurrentUrl(), "web addresses after clicking create account link not match");

        // Input registration credentials
        // Generate user data
        RandomDataGenerator.generateValidUserData();
        String randomUserName = RandomDataGenerator.getLongUserName();
        String randomPassword = RandomDataGenerator.getPassword();

        // Input registration data
        registrationPage.inputLoginName(randomUserName);
        registrationPage.inputPassword(randomPassword);
        registrationPage.inputRepeatPassword(randomPassword);

        // Finish registration
        registrationPage.clickToCreateAccount();

        // Assert we were not redirected to account page
        Assertions.assertNotEquals("http://localhost:8080/skaiciuotuvas", driver.getCurrentUrl(), "we are redirected to account page but should not");
        // Assert we get error message
        Assertions.assertEquals("Privaloma įvesti nuo 3 iki 32 simbolių", registrationPage.userNameErrorText(), "messages are not matching");
    }

    @Test
    public void  registerWithEmptyPassword(){
        LoginPage loginPage  = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);

        // Go to registration page
        loginPage.goToRegistrationPage();

        // Assert redirection
        Assertions.assertEquals("http://localhost:8080/registruoti", driver.getCurrentUrl(), "web addresses after clicking create account link not match");

        // Input registration credentials
        // Generate user data
        RandomDataGenerator.generateValidUserData();
        String randomUserName = RandomDataGenerator.getUserName();
        String randomPassword = RandomDataGenerator.getPassword();


        // Input registration data
        registrationPage.inputLoginName(randomUserName);
        registrationPage.inputPassword("");
        registrationPage.inputRepeatPassword(randomPassword);

        // Finish registration
        registrationPage.clickToCreateAccount();

        // Assert we were not redirected to account page
        Assertions.assertNotEquals("http://localhost:8080/skaiciuotuvas", driver.getCurrentUrl(), "we are redirected to account page but should not");
        // Assert we get error message
        Assertions.assertEquals("Šį laukelį būtina užpildyti\n" +
                "Privaloma įvesti bent 3 simbolius", registrationPage.passwordErrorText(), "messages are not matching");
    }

    @Test
    public void  registerWithEmptyRepeatPassword(){
        LoginPage loginPage  = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);

        // Go to registration page
        loginPage.goToRegistrationPage();

        // Assert redirection
        Assertions.assertEquals("http://localhost:8080/registruoti", driver.getCurrentUrl(), "web addresses after clicking create account link not match");

        // Input registration credentials
        // Generate user data
        RandomDataGenerator.generateValidUserData();
        String randomUserName = RandomDataGenerator.getUserName();
        String randomPassword = RandomDataGenerator.getPassword();


        // Input registration data
        registrationPage.inputLoginName(randomUserName);
        registrationPage.inputPassword(randomPassword);
        registrationPage.inputRepeatPassword("");

        // Finish registration
        registrationPage.clickToCreateAccount();

        // Assert we were not redirected to account page
        Assertions.assertNotEquals("http://localhost:8080/skaiciuotuvas", driver.getCurrentUrl(), "we are redirected to account page but should not");
        // Assert we get error message
        Assertions.assertEquals("Įvesti slaptažodžiai nesutampa", registrationPage.repeatPasswordErrorText(), "messages are not matching");
    }

    @Test
    public void  registerPasswordRepeatPasswordNotMatch(){
        LoginPage loginPage  = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);

        // Go to registration page
        loginPage.goToRegistrationPage();

        // Assert redirection
        Assertions.assertEquals("http://localhost:8080/registruoti", driver.getCurrentUrl(), "web addresses after clicking create account link not match");

        // Input registration credentials
        // Generate user data
        RandomDataGenerator.generateValidUserData();
        String randomUserName = RandomDataGenerator.getUserName();
        String randomPassword = RandomDataGenerator.getPassword();
        String wrongRepeatPassword = RandomDataGenerator.getPassword() + "a";


        // Input registration data
        registrationPage.inputLoginName(randomUserName);
        registrationPage.inputPassword(randomPassword);
        registrationPage.inputRepeatPassword(wrongRepeatPassword);

        // Finish registration
        registrationPage.clickToCreateAccount();

        // Assert we were not redirected to account page
        Assertions.assertNotEquals("http://localhost:8080/skaiciuotuvas", driver.getCurrentUrl(), "we are redirected to account page but should not");
        // Assert we get error message
        Assertions.assertEquals("Įvesti slaptažodžiai nesutampa", registrationPage.repeatPasswordErrorText(), "messages are not matching");
    }


    }


