package engine;

import engine.assertion.Assertions;
import engine.browser.BrowserActions;
import engine.config.Config;
import engine.driver.DriverFactory;
import engine.element.ElementActions;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class ActionsBot {
    private final WebDriver driver;
    private final ElementActions element;
    private final BrowserActions browser;
    private final Assertions assertions;

    // Real use: the engine builds the browser + wait for you.
    public ActionsBot() {
        this(DriverFactory.createDriver());
    }

    // Wrap a wait around a given driver (browser built elsewhere).
    public ActionsBot(WebDriver driver) {
        this(driver, buildWait(driver));
    }

    // Inject a ready-made wait (handy for testing the engine itself).
    public ActionsBot(Wait<WebDriver> wait) {
        this(null, wait);
    }

    // Primary constructor: every other constructor ends up here.
    private ActionsBot(WebDriver driver, Wait<WebDriver> wait) {
        this.driver = driver;
        this.element = new ElementActions(wait, this);
        this.browser = new BrowserActions(wait, this);
        this.assertions = new Assertions(wait, this);
    }

    private static Wait<WebDriver> buildWait(WebDriver driver) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(Config.timeout()))
                .pollingEvery(Duration.ofMillis(Config.pollingMillis()))
                .ignoring(NotFoundException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(AssertionError.class)
                .ignoring(StaleElementReferenceException.class);
    }

    // Grab a PNG of the current screen (used for screenshot-on-failure reporting).
    public byte[] screenshot() {
        if (driver instanceof TakesScreenshot ts) {
            return ts.getScreenshotAs(OutputType.BYTES);
        }
        return new byte[0];
    }

    // Accessors: hand out the specialist so the test calls the action on it.
    public ElementActions element() {
        return element;
    }

    public BrowserActions browser() {
        return browser;
    }

    public Assertions assertThat() {
        return assertions;
    }
}
