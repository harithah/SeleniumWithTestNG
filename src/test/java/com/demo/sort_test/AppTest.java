package com.demo.sort_test;

import com.demo.Pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    WebDriver driver;
    HomePage homePage;

    @BeforeClass
//    @Parameters("browser_type")
    public void setUp() {
        driver=WebDriverManager.startDriver();
        WebDriverManager.startBrowser(driver);
        homePage = new HomePage(this.driver);
    }

    @Test
    public void testGoogleSearch() {
        homePage.enterQuery();
    }


    @AfterClass
    public void tearDown() {
        WebDriverManager.stopDriver(driver);
    }


}
