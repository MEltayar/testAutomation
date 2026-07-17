package templates;

import engine.ActionsBot;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.*;

import java.time.Duration;

public abstract class TestScenario {

    public ActionsBot bot;


    public WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriver driver;

        // Create a new Chrome browser instance and assign it to the WebDriver reference
        // This will open a new Chrome browser window with the specified options
        // Declare a WebDriver reference (currently not pointing to any browser)
        driver = new ChromeDriver(getOptimizedOptions());

        Wait<WebDriver> wait;
        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(NotFoundException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(AssertionError.class)
                .ignoring(StaleElementReferenceException.class);

        bot = new ActionsBot(wait);

    }

    @AfterClass
    public void tearDown() {

        // Close the currently open browser window and end the browser session
        bot.quitBrowser();
    }

    public static ChromeOptions getOptimizedOptions() {

        ChromeOptions options = new ChromeOptions();

        // ============================
        // BASIC & MOST IMPORTANT OPTIONS
        // ============================

        // Start browser maximized (common for UI tests)
        options.addArguments("--start-maximized");   // official common arg

        // Disable notifications (location, alerts, popups)
        options.addArguments("--disable-notifications");

        // Incognito mode (clean session each run)
        options.addArguments("--incognito");

        // Allow cross-origin requests (fixes Selenium 4 CORS issues)
        options.addArguments("--remote-allow-origins=*");

        // ============================
        // HEADLESS MODE (OPTIONAL)
        // ============================

        // Modern headless mode (Chrome 109+)
        // Comment this line if you want to SEE the browser
        //options.addArguments("--headless=new");      // official common arg

        // ============================
        // KEEP BROWSER OPEN (OPTIONAL)
        // ============================

        // Keep Chrome open after test unless driver.quit() is called
        // (Java already defaults to this)
        options.setExperimentalOption("detach", true);

        // ============================
        // CREATE DRIVER
        // ============================

        return options;
    }
}
