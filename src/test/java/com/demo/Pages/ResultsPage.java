package com.demo.Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ResultsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private double doubleInitialPrice;
    private By tranportModeElement = By.cssSelector("li a[class*=ResultTabs__]");
    private By element = By.cssSelector("div[class*=Paging__pagingContainer___289JA] div[class*=Paging__cell] >span[data-key='dw.paging.next']");
    private By tabResults = By.className("resultTabs");
    private By labelPrice = By.cssSelector("div[class*=Results__tabsBody] div[class*=Result__result__] span[data-test='price']");
    private By tabAlternativeResults = By.cssSelector("div[class*=Results__alternativeResultsDivider]");
    public static Logger logger = Logger.getLogger(HomePage.class);


    public ResultsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 100);
    }

    public String waitForResultsPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tabResults));
        logger.info(driver.getTitle());
        return driver.getTitle();
    }

    private boolean comparePrice() {

        List<WebElement> resultsTab =
                driver.findElements(labelPrice);
        for (WebElement result : resultsTab) {
            String strPrice = result.getText().replaceAll("€", "").replaceAll(",", "");
            double doublePrice = Double.parseDouble(strPrice);
            if(driver.findElements(tabAlternativeResults).size()!=0){
                doubleInitialPrice=0.0;
            }
            if (doublePrice < doubleInitialPrice) {
                logger.info("Price found in decending order");
                return false;
            } else {
                doubleInitialPrice = doublePrice;
            }
        }
        return true;
    }

    private boolean clickNextAndContinueCompare() throws InterruptedException {
        boolean blnAscendingOrder = true;

        while (driver.findElements(element).size() != 0) {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            Actions action = new Actions(driver);
            Thread.sleep(1000);
            action.moveToElement(driver.findElement(element)).click().perform();
            logger.info("Clicking NEXT button");
            wait.until(ExpectedConditions.visibilityOfElementLocated(tabResults));
//            comparePrice();
            if (!comparePrice()){
                return false;
            }
        }
        return true;
    }

    public boolean verifyIfPriceArrangedAscending() throws InterruptedException {
        boolean blnAscendingOrder;
        doubleInitialPrice = 0.0;
        blnAscendingOrder = comparePrice();
        if (!blnAscendingOrder) {
            return false;
        }
        blnAscendingOrder = clickNextAndContinueCompare();
        return blnAscendingOrder;
    }

    public void goToAirMode() {
        driver.findElements(tranportModeElement).get(1).click();
        logger.info("Air mode");
    }

    public void goToBusMode() {
        driver.findElements(tranportModeElement).get(2).click();
        logger.info("Bus mode");
    }
}
