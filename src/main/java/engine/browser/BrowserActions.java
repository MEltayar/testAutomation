package engine.browser;

import engine.ActionsBot;
import engine.report.Report;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

public class BrowserActions {
    private final Wait<WebDriver> wait;
    private final ActionsBot bot;

    // Public (cross-package): only ActionsBot builds this, but sub-packages force public.
    public BrowserActions(Wait<WebDriver> wait, ActionsBot bot) {
        this.wait = wait;
        this.bot = bot;
    }

    public BrowserActions navigateTo(String url) {
        Report.step("Navigating to " + url,
                () -> wait.until(d -> { d.navigate().to(url); return true; }),
                bot::screenshot);
        return this;
    }

    public BrowserActions refresh() {
        Report.step("Refreshing the page",
                () -> wait.until(d -> { d.navigate().refresh(); return true; }),
                bot::screenshot);
        return this;
    }

    public BrowserActions back() {
        Report.step("Navigating back",
                () -> wait.until(d -> { d.navigate().back(); return true; }),
                bot::screenshot);
        return this;
    }

    public BrowserActions forward() {
        Report.step("Navigating forward",
                () -> wait.until(d -> { d.navigate().forward(); return true; }),
                bot::screenshot);
        return this;
    }

    public void quitBrowser() {
        Report.step("Quitting browser",
                () -> wait.until(d -> { d.quit(); return true; }),
                bot::screenshot);
    }

    // Bridge back to the entry point so tests can chain across categories.
    public ActionsBot and() {
        return bot;
    }
}
