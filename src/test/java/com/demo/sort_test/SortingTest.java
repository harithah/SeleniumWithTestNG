package com.demo.sort_test;

import com.demo.Pages.HomePage;
import com.demo.Pages.ResultsPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Unit test for simple App.
 */
public class SortingTest {

    WebDriver driver;
    HomePage homePage;
    ResultsPage resultsPage;

    @BeforeMethod
    public void setUp() {
        driver = WebDriverManager.startDriver();
        WebDriverManager.startBrowser(driver);
        homePage = new HomePage(this.driver);
        resultsPage = new ResultsPage(this.driver);
    }


    @Test
    public void verifyIfPriceArrangedAcendingInTrainMode() throws InterruptedException {
        homePage.enterQuery();
        Assert.assertEquals(resultsPage.waitForResultsPage(), "GoEuro");
        Assert.assertTrue(resultsPage.verifyIfPriceArrangedAscending());
//        Assert.assertTrue(verifyIfPriceArrangedAscending());
    }

    @Test
    public void verifyIfPriceArrangedAcendingInAirMode() throws InterruptedException {
        homePage.enterQuery();
        Assert.assertEquals(resultsPage.waitForResultsPage(), "GoEuro");
        resultsPage.goToAirMode();
        Assert.assertTrue(resultsPage.verifyIfPriceArrangedAscending());
    }

    @Test
    public void verifyIfPriceArrangedAcendingInBusMode() throws InterruptedException {
        homePage.enterQuery();
        Assert.assertEquals(resultsPage.waitForResultsPage(), "GoEuro");
        resultsPage.goToBusMode();
        Assert.assertTrue(resultsPage.verifyIfPriceArrangedAscending());
    }

    private boolean verifyIfPriceArrangedAscending() throws InterruptedException {
        boolean isPriceArrangedAscending;
        isPriceArrangedAscending = resultsPage.verifyIfPriceArrangedAscending();
        if (isPriceArrangedAscending) {
            resultsPage.goToAirMode();
            isPriceArrangedAscending = resultsPage.verifyIfPriceArrangedAscending();
        }
        if (isPriceArrangedAscending) {
            resultsPage.goToBusMode();
            isPriceArrangedAscending = resultsPage.verifyIfPriceArrangedAscending();
        }
        if (isPriceArrangedAscending) {
            return true;
        }
        else {
            return false;
        }
    }

    @AfterMethod
    public void tearDown() {
        WebDriverManager.stopDriver(driver);
    }


}
