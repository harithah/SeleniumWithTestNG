package com.demo.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by harithah on 3/4/17.
 */
public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterQuery() {
        driver.findElement(By.className("departure")).click();
        driver.findElement(By.className("departure")).sendKeys("Berlin");
        driver.findElement(By.className("arrival")).click();
        driver.findElement(By.className("arrival")).sendKeys("Prague");
        driver.findElement(By.id("dLsbSubmit")).submit();
    }
}

