package com.demo.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    WebDriver driver;
    By departureTextElement = By.cssSelector(".departure input.sb-input");
    By arrivalTextElement = By.cssSelector(".arrival div div input.sb-input");
    By suggestionsListElement = By.cssSelector(".arrival .sb-suggestion");
    By departureSuggestionsListElement = By.cssSelector(".departure .sb-suggestion");
    By btnSubmit = By.id("dLsbSubmit");
    By cardElement = By.className("sb-chosen-card");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterQuery() {

        WebDriverWait wait = new WebDriverWait(driver, 100);

        driver.findElement(departureTextElement).sendKeys("Berlin, Germany");
        driver.findElement(departureTextElement).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated((departureSuggestionsListElement)));
        driver.findElements(departureSuggestionsListElement).get(0).click();

        driver.findElement(arrivalTextElement).sendKeys("Prague Czech republic");
        driver.findElement(arrivalTextElement).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated((suggestionsListElement)));
        driver.findElements(suggestionsListElement).get(0).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(cardElement));
        wait.until(ExpectedConditions.elementToBeClickable(btnSubmit));
        driver.findElement(btnSubmit).submit();
    }
}

