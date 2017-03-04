package com.demo.sort_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by harithah on 3/4/17.
 */
public class WebDriverManager {
    public static Wait<WebDriver> wait;
    public static String url;

    public static WebDriver startDriver() {
        WebDriver d;
        d = new ChromeDriver();
//        d = new FirefoxDriver();
        return d;
    }

    public static void startBrowser(WebDriver driver) {
        driver.get(getUrl());
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 200);
    }

    public static String getUrl() {
        return "http://www.goeuro.es/";
    }

    public static void stopDriver(WebDriver driver) {
        driver.close();
    }
}
