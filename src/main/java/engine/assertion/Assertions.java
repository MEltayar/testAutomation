package engine.assertion;

import engine.ActionsBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

// Dispatcher: pick the target of the assertion, then chain the check on it.
public class Assertions {
    private final Wait<WebDriver> wait;
    private final ActionsBot bot;

    // Public (cross-package): only ActionsBot builds this, but sub-packages force public.
    public Assertions(Wait<WebDriver> wait, ActionsBot bot) {
        this.wait = wait;
        this.bot = bot;
    }

    public ElementAssertions element(By locator) {
        return new ElementAssertions(wait, bot, locator);
    }

    public BrowserAssertions browser() {
        return new BrowserAssertions(wait, bot);
    }
}
