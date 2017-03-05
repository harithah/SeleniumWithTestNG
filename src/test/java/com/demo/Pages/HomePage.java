package com.demo.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterQuery() {

        WebDriverWait wait=new WebDriverWait(driver,100);

        driver.findElement(By.cssSelector(".departure input.sb-input")).sendKeys("Berlin, Germany");
        driver.findElement(By.cssSelector(".departure input.sb-input")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector( ".departure .sb-suggestion"))));
        driver.findElements(By.cssSelector(".departure .sb-suggestion")).get(0).click();

        driver.findElement(By.cssSelector(".arrival div div input.sb-input")).sendKeys("Prague Czech republic");
        driver.findElement(By.cssSelector(".arrival div div input.sb-input")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector( ".arrival .sb-suggestion"))));
        driver.findElements(By.cssSelector(".arrival .sb-suggestion")).get(0).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("sb-chosen-card")));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("dLsbSubmit")));
        driver.findElement(By.id("dLsbSubmit")).submit();
    }
}

