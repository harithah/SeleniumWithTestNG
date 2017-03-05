package com.demo.sort_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverManager {
    private static Wait<WebDriver> wait;
    private static String url;

    protected static WebDriver startDriver() {
        WebDriver driver;
        driver = new FirefoxDriver();
        return driver;
    }

    protected static void startBrowser(WebDriver driver) {
        driver.get(getUrl());
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 200);
    }

    private static String getUrl() {
        return "http://www.goeuro.com/";
    }

    protected static void stopDriver(WebDriver driver) {
        driver.close();
    }
}
