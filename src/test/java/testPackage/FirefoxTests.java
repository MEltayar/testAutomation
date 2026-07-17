package testPackage;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class FirefoxTests {

    WebDriver driver;

    /**
     * Open Mozilla Firefox
     * Navigate to [<a href="https://duckduckgo.com/">...</a>]
     * Search for [TestNG]
     * Assert that the text of the fourth result is [TestNG Tutorial]
     * Close Mozilla Firefox
     */
    @BeforeMethod
    public void setUp() {
        driver = new FirefoxDriver(getFirefoxOptions());
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void checkTestNGFourthResult() {

        //Open Mozilla Firefox

        //Navigate to [https://duckduckgo.com/]
        driver.get("https://duckduckgo.com");

        //Search for [TestNG]
        driver.findElement(By.id("searchbox_input")).sendKeys("TestNG" + Keys.ENTER);

        //Assert that the text of the fourth result is [TestNG Tutorial]
        By fourthResultLocator = By.xpath("//article[@id='r1-3']//h2");

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(NotFoundException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(StaleElementReferenceException.class);

        // wait until we can successfully get the text of the fourth result
        wait.until(d -> {
            d.findElement(fourthResultLocator).getText();
            return true;
        });

        //Assert that the text of the fourth result is [TestNG Tutorial]
        Assert.assertEquals(driver.findElement(fourthResultLocator).getText(), "TestNG Tutorial", "The text of the fourth result does not match the expected value");
    }


    public static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        // Disable notifications (location, alerts, popups)
        firefoxOptions.addArguments("--disable-notifications");

        // Incognito mode (clean session each run)
        firefoxOptions.addArguments("--private");

        return firefoxOptions;
    }

}