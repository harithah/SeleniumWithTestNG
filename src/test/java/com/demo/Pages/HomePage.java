package com.demo.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;


public class HomePage {
    WebDriver driver;
    By departureTextElement = By.cssSelector(".departure input.sb-input");
    By arrivalTextElement = By.cssSelector(".arrival div div input.sb-input");
    By suggestionsListElement = By.cssSelector(".arrival .sb-suggestion");
    By departureSuggestionsListElement = By.cssSelector(".departure .sb-suggestion");
    By btnSubmit = By.id("dLsbSubmit");
    By cardElement = By.className("sb-chosen-card");
    public static Logger logger = Logger.getLogger(HomePage.class);

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterQuery(String strDeparture,String strArrival) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 100);
            logger.info("On the home page");
            driver.findElement(departureTextElement).sendKeys(strDeparture);
            driver.findElement(departureTextElement).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated((departureSuggestionsListElement)));
            driver.findElements(departureSuggestionsListElement).get(0).click();

            driver.findElement(arrivalTextElement).sendKeys(strArrival);
            driver.findElement(arrivalTextElement).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated((suggestionsListElement)));
            driver.findElements(suggestionsListElement).get(0).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(cardElement));
            wait.until(ExpectedConditions.elementToBeClickable(btnSubmit));
            driver.findElement(btnSubmit).submit();
            logger.info("Performing search");
        } catch (Exception exception) {
            logger.error("Exception in the home page:" + exception);
        }
    }
}

