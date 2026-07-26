package engine.assertion;

import engine.ActionsBot;
import engine.report.Report;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

// Assertions about a single element (the locator is the subject of every check).
public class ElementAssertions {
    private final Wait<WebDriver> wait;
    private final ActionsBot bot;
    private final By locator;

    // Package-private: built only by Assertions (same package).
    ElementAssertions(Wait<WebDriver> wait, ActionsBot bot, By locator) {
        this.wait = wait;
        this.bot = bot;
        this.locator = locator;
    }

    public ElementAssertions isDisplayed() {
        Report.step("Asserting element is displayed: " + locator,
                () -> wait.until(d -> {
                    Assert.assertTrue(d.findElement(locator).isDisplayed(),
                            "Element is not displayed: " + locator);
                    return true;
                }),
                bot::screenshot);
        return this;
    }

    public ElementAssertions isSelected() {
        Report.step("Asserting element is selected: " + locator,
                () -> wait.until(d -> {
                    Assert.assertTrue(d.findElement(locator).isSelected(),
                            "Element is not selected: " + locator);
                    return true;
                }),
                bot::screenshot);
        return this;
    }

    public ElementAssertions isEnabled() {
        Report.step("Asserting element is enabled: " + locator,
                () -> wait.until(d -> {
                    Assert.assertTrue(d.findElement(locator).isEnabled(),
                            "Element is not enabled: " + locator);
                    return true;
                }),
                bot::screenshot);
        return this;
    }

    public ElementAssertions textIs(String expectedText) {
        Report.step("Asserting text of " + locator + " is '" + expectedText + "'",
                () -> wait.until(d -> {
                    Assert.assertEquals(d.findElement(locator).getText(), expectedText, "text mismatch");
                    return true;
                }),
                bot::screenshot);
        return this;
    }

    public ElementAssertions textContains(String expectedText) {
        Report.step("Asserting text of " + locator + " contains '" + expectedText + "'",
                () -> wait.until(d -> {
                    String actual = d.findElement(locator).getText();
                    Assert.assertNotNull(actual, "Element has no text: " + locator);
                    Assert.assertTrue(actual.contains(expectedText),
                            "text '" + actual + "' does not contain '" + expectedText + "'");
                    return true;
                }),
                bot::screenshot);
        return this;
    }

    public ElementAssertions attributeIs(String attribute, String expectedValue) {
        Report.step("Asserting attribute '" + attribute + "' of " + locator + " is '" + expectedValue + "'",
                () -> wait.until(d -> {
                    Assert.assertEquals(d.findElement(locator).getDomAttribute(attribute), expectedValue,
                            "attribute '" + attribute + "' mismatch");
                    return true;
                }),
                bot::screenshot);
        return this;
    }

    public ElementAssertions linkHrefIs(String expectedHref) {
        Report.step("Asserting href of " + locator + " is '" + expectedHref + "'",
                () -> wait.until(d -> {
                    String currentHref = d.findElement(locator).getDomAttribute("href");
                    Assert.assertEquals(currentHref, expectedHref, "href mismatch");
                    return true;
                }),
                bot::screenshot);
        return this;
    }

    public ElementAssertions linkHrefContains(String expectedText) {
        Report.step("Asserting href of " + locator + " contains '" + expectedText + "'",
                () -> wait.until(d -> {
                    String currentHref = d.findElement(locator).getDomAttribute("href");
                    Assert.assertNotNull(currentHref, "Element has no 'href' attribute: " + locator);
                    Assert.assertTrue(currentHref.contains(expectedText),
                            "href '" + currentHref + "' does not contain '" + expectedText + "'");
                    return true;
                }),
                bot::screenshot);
        return this;
    }

    // Bridge back to the entry point so tests can chain across categories.
    public ActionsBot and() {
        return bot;
    }
}
