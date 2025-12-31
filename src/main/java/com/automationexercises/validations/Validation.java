package com.automationexercises.validations;

import com.automationexercises.utils.logs.LogsManager;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

//Soft Assertion
public class Validation extends BaseAssertion{
    private static SoftAssert softAssert = new SoftAssert();
    private static boolean usedSoftAssert = false; // Flag to track if any soft assertions were made

    protected Validation(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void assertTrue(boolean condition, String message) {
        usedSoftAssert=true;
        softAssert.assertTrue(condition, message);
    }

    @Override
    protected void assertFalse(boolean condition, String message) {
        usedSoftAssert=true;
        softAssert.assertFalse(condition, message);
    }

    @Override
    protected void assertEquals(String actual, String expected, String message) {
        usedSoftAssert=true;
        softAssert.assertEquals(actual, expected, message);
    }

    public static void assertAll(){
        if(!usedSoftAssert){
            LogsManager.info("No soft assertions were made.");
            return;
        }
        try {
            softAssert.assertAll();

        } catch (AssertionError e) {
            LogsManager.error("Soft assertion failed: " + e.getMessage());
            throw e;
        }
        finally {
            softAssert=new SoftAssert(); // Reset softAssert for future use
        }




    }


}
