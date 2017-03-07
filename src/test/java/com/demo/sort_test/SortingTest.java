package com.demo.sort_test;

import com.demo.Pages.HomePage;
import com.demo.Pages.ResultsPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class SortingTest {

    WebDriver driver;
    HomePage homePage;
    ResultsPage resultsPage;
    public static Logger logger = Logger.getLogger(HomePage.class);

    @BeforeMethod
    public void setUp() {
        driver = WebDriverManager.startDriver();
        logger.info("Starting the driver");
        WebDriverManager.startBrowser(driver);
        logger.info("Navigating to the site");
        homePage = new HomePage(this.driver);
        resultsPage = new ResultsPage(this.driver);
    }

    @DataProvider
    public Object[][] getData(){
        Object[][] queryData=new Object[1][2];
        queryData[0][0]="Berlin, Germany";
        queryData[0][1]="Prague Czech republic";
        return queryData;
    }

    @Test(dataProvider="getData")
    public void verifyIfPriceArrangedAscendingInTrainMode(String strDeparture, String strArrival) throws InterruptedException {
        enterSearch(strDeparture,strArrival);
        verifyAndLog("Train mode");

    }

    @Test(dataProvider="getData")
    public void verifyIfPriceArrangedAscendingInAirMode(String strDeparture, String strArrival) throws InterruptedException {
        enterSearch(strDeparture,strArrival);
        resultsPage.goToAirMode();
        verifyAndLog("Air mode");

    }

    @Test(dataProvider="getData")
    public void verifyIfPriceArrangedAscendingInBusMode(String strDeparture, String strArrival) throws InterruptedException {
        enterSearch(strDeparture,strArrival);
        resultsPage.goToBusMode();
        verifyAndLog("Bus mode");

    }

    private void enterSearch(String strDeparture,String strArrival) {
        homePage.enterQuery(strDeparture,strArrival);
        Assert.assertEquals(resultsPage.waitForResultsPage(), "GoEuro");
    }

    private void verifyAndLog(String strTravelMode) throws InterruptedException {
        boolean blnVerifyPriceAscending = resultsPage.verifyIfPriceArrangedAscending();
        logger.info("Price in ascending order for "+strTravelMode+" is asserted to be: " + blnVerifyPriceAscending);
        Assert.assertTrue(blnVerifyPriceAscending,"Price not found in ascending order for "+strTravelMode);
    }

    @AfterMethod
    public void tearDown() {
        WebDriverManager.stopDriver(driver);
    }

}
