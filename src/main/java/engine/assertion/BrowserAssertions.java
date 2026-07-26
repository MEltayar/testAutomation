package engine.assertion;

import engine.ActionsBot;
import engine.report.Report;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

// Assertions about the browser/page (URL, title...).
public class BrowserAssertions {
    private final Wait<WebDriver> wait;
    private final ActionsBot bot;

    // Package-private: built only by Assertions (same package).
    BrowserAssertions(Wait<WebDriver> wait, ActionsBot bot) {
        this.wait = wait;
        this.bot = bot;
    }

    public BrowserAssertions urlIs(String expectedUrl) {
        Report.step("Asserting URL is '" + expectedUrl + "'",
                () -> wait.until(d -> {
                    Assert.assertEquals(d.getCurrentUrl(), expectedUrl, "URL mismatch");
                    return true;
                }),
                bot::screenshot);
        return this;
    }

    public BrowserAssertions urlContains(String expectedText) {
        Report.step("Asserting URL contains '" + expectedText + "'",
                () -> wait.until(d -> {
                    String url = d.getCurrentUrl();
                    Assert.assertNotNull(url, "Current URL is null");
                    Assert.assertTrue(url.contains(expectedText),
                            "URL '" + url + "' does not contain '" + expectedText + "'");
                    return true;
                }),
                bot::screenshot);
        return this;
    }

    public BrowserAssertions titleIs(String expectedTitle) {
        Report.step("Asserting title is '" + expectedTitle + "'",
                () -> wait.until(d -> {
                    Assert.assertEquals(d.getTitle(), expectedTitle, "Title mismatch");
                    return true;
                }),
                bot::screenshot);
        return this;
    }

    public BrowserAssertions titleContains(String expectedText) {
        Report.step("Asserting title contains '" + expectedText + "'",
                () -> wait.until(d -> {
                    String title = d.getTitle();
                    Assert.assertNotNull(title, "Page title is null");
                    Assert.assertTrue(title.contains(expectedText),
                            "Title '" + title + "' does not contain '" + expectedText + "'");
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
