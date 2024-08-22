package utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomDataGenerator {


    private static String randomPassword;
    private static String randomUserName;

    public static  String randomTooShortName;
    public static  String randomTooLongName;




    public static String generateRandomUserName(){
        return RandomStringUtils.randomAlphanumeric(10);
    }

    public static String generateRandomPassword(){
        return RandomStringUtils.randomAlphanumeric(8) + "Aa+1";
    }

    public static String generateRandomTooShortUserName(){
        return RandomStringUtils.randomAlphanumeric(2);
    }
    public static String generateRandomTooLongUserName(){
        return RandomStringUtils.randomAlphanumeric(33);
    }



    public static void generateValidUserData() {
        randomUserName = generateRandomUserName();
        randomPassword = generateRandomPassword();
        randomTooShortName = generateRandomTooShortUserName();
        randomTooLongName = generateRandomTooLongUserName();
    }


    public static String getPassword() {
        return randomPassword;
    }

    public static String getUserName() {
        return randomUserName;
    }

    public static String getShortUserName() {
        return randomTooShortName;
    }

    public static String getLongUserName() {
        return randomTooLongName;
    }

}
