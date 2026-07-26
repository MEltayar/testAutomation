package engine.element;

import engine.ActionsBot;
import engine.report.Report;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

public class ElementActions {
    private final Wait<WebDriver> wait;
    private final ActionsBot bot;

    // Public (cross-package): only ActionsBot builds this, but sub-packages force public.
    public ElementActions(Wait<WebDriver> wait, ActionsBot bot) {
        this.wait = wait;
        this.bot = bot;
    }

    public ElementActions type(By locator, String text) {
        Report.step("Typing '" + text + "' into " + locator,
                () -> wait.until(d -> { d.findElement(locator).sendKeys(text); return true; }),
                bot::screenshot);
        return this;
    }

    public ElementActions click(By locator) {
        Report.step("Clicking " + locator,
                () -> wait.until(d -> { d.findElement(locator).click(); return true; }),
                bot::screenshot);
        return this;
    }

    public ElementActions submit(By locator) {
        Report.step("Submitting " + locator,
                () -> wait.until(d -> { d.findElement(locator).submit(); return true; }),
                bot::screenshot);
        return this;
    }

    public ElementActions clear(By locator) {
        Report.step("Clearing " + locator,
                () -> wait.until(d -> { d.findElement(locator).clear(); return true; }),
                bot::screenshot);
        return this;
    }

    public ElementActions doubleClick(By locator) {
        Report.step("Double-clicking " + locator,
                () -> wait.until(d -> {
                    new Actions(d).doubleClick(d.findElement(locator)).perform();
                    return true;
                }),
                bot::screenshot);
        return this;
    }

    public ElementActions hover(By locator) {
        Report.step("Hovering over " + locator,
                () -> wait.until(d -> {
                    new Actions(d).moveToElement(d.findElement(locator)).perform();
                    return true;
                }),
                bot::screenshot);
        return this;
    }

    public ElementActions selectByText(By locator, String visibleText) {
        Report.step("Selecting '" + visibleText + "' in " + locator,
                () -> wait.until(d -> {
                    new Select(d.findElement(locator)).selectByVisibleText(visibleText);
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
