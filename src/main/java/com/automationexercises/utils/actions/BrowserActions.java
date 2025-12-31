package com.automationexercises.utils.actions;

import com.automationexercises.utils.logs.LogsManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class BrowserActions {

    private final WebDriver driver;

    public BrowserActions(WebDriver driver) {
        this.driver = driver;
    }

    /*  Maximize the browser window
     */
    public void maximizeWindow() {
        driver.manage().window().maximize();
        LogsManager.info("Browser window maximized.");
    }
    /*  Gets the current URL of the web page
    *  @return the current URL as a String
     */
    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        LogsManager.info("Current URL: " + url);
        return url;
    }
    /*  Navigates to a specified URL
     */
    public void navigateTo(String url) {
        driver.get(url);
        LogsManager.info("Navigated to URL: " + url);
    }
    /*  Refreshes the current web page
     */
    public void refreshPage() {
        driver.navigate().refresh();
        LogsManager.info("Current page refreshed.");
    }
    /*  Closes the current browser window
     */
    public void closeCurrentWindow() {
        driver.close();
        LogsManager.info("Current browser window closed.");
    }
    /*  Opens a new browser window
     */
    public void openNewWindow() {
        driver.switchTo().newWindow(WindowType.WINDOW);
        LogsManager.info("New browser window opened.");
    }




}
