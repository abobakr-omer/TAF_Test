package com.automationexercises.utils.actions;

import com.automationexercises.utils.WaitManager;
import com.automationexercises.utils.logs.LogsManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;

public class ElementActions {

    private WebDriver driver;
    private WaitManager waitManager;

    public ElementActions(WebDriver driver){
        this.driver=driver;
        this.waitManager =new WaitManager(driver);
    }

    /* Clicking
    *  @param locator
     */
    public void click(By locator){
        waitManager.fluentWait().until(d -> {
            try {
                WebElement element=d.findElement(locator);
                scrollToElementJS(locator);
                element.click();
                LogsManager.info("Clicked on element: " + locator);
                return true;
            } catch (Exception e) {
                return false;
            }

        });

    }



    /*    Typing
     * @param locator
     * @param text
     */
    public void type(By locator, String text){
        waitManager.fluentWait().until(d->{
            try {
                WebElement element=d.findElement(locator);
                scrollToElementJS(locator);
                element.clear();
                element.sendKeys(text);
                LogsManager.info("Typed text '" + text + "' into element: " + locator);
                return true;

            } catch (Exception e) {
                return false;
            }
        });

    }



    /* Getting text
     * @param locator
     * @return String
     */
    public String getText(By locator){
      return   waitManager.fluentWait().until(d->{
            try {
                WebElement element=d.findElement(locator);
                scrollToElementJS(locator);
                String msg=element.getText();
                LogsManager.info("Retrieved text from element: " + locator + " - Text: " + msg);
                return !msg.isEmpty() ? msg : null;

            } catch (Exception e) {
                return null;
            }
        });


    }


    /* Uploading file
     * @param locator
     * @param filePath
     */
    public void uploadFile(By locator, String filePath){

        String absoluteFilePath=System.getProperty("user.dir")+ File.separator +filePath;

        waitManager.fluentWait().until(d->{
            try {
                WebElement element=d.findElement(locator);
                scrollToElementJS(locator);
                element.sendKeys(absoluteFilePath);
                LogsManager.info("Uploaded file: " + absoluteFilePath + " to element: " + locator);
                return true;

            } catch (Exception e) {
                return false;
            }
        });

    }




    //Find an element
    public WebElement findElement(By locator){
        return driver.findElement(locator);
    }


    //Method to scroll to element using JS
    public void scrollToElementJS(By locator){
        ((JavascriptExecutor)driver).executeScript("""
                arguments[0].scrollIntoView({behavior:"auto",block:"center",inline:"center"});""", findElement(locator));
    }


}
