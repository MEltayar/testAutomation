package engine;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

public class ActionsBot {
    Wait<WebDriver> wait;

    public ActionsBot(Wait<WebDriver> wait) {
        this.wait = wait;
    }

    public ActionsBot navigateTo(String url) {
        wait.until(d -> {
            System.out.println("Navigating to '" + url + "'. ");
            d.navigate().to(url);
            System.out.println("Navigation was successful.");
            return true;
        });
        return this;
    }
    public ActionsBot Type(By locator, String text) {
        wait.until(d -> {
            System.out.println("Typing '" + text + "' into '" + locator + "'. ");
            d.findElement(locator).sendKeys(text);
            System.out.println("Typing was successful.");
            return true;
        });
        return this;
    }

    public ActionsBot Click(By locator) {
        wait.until(d -> {
            System.out.println("Clicking '" + locator + "'. ");
            d.findElement(locator).click();
            System.out.println("Clicking was successful.");
            return true;
        });
        return this;
    }

    public ActionsBot assertUrl(String expectedUrl){
        wait.until(d -> {
            String currentUrl = d.getCurrentUrl();
            System.out.println("Current URL: " + currentUrl);

            System.out.println("Asserting current URL is equal to: " + expectedUrl);
            Assert.assertEquals(currentUrl, expectedUrl, "Assertion FAILED");
            System.out.println("Assertion PASSED");
            return true;
        });
        return this;
    }

    //Assert title
    public ActionsBot assertTitle(String expectedTitle){
        wait.until(d -> {
            String currentTitle = d.getTitle();
            System.out.println("Current Title: " + currentTitle);

            System.out.println("Asserting current Title is equal to: " + expectedTitle);
            Assert.assertEquals(currentTitle, expectedTitle, "Assertion FAILED");
            System.out.println("Assertion PASSED");
            return true;
        });
        return this;
    }

    public ActionsBot isSelected(By locator) {
        wait.until(d -> {
            boolean isSelected = d.findElement(locator).isSelected();
            System.out.println("Checking if element is selected: " + locator);
            Assert.assertTrue(isSelected, "Element is not selected");
            System.out.println("Element is selected");
            return true;
        });
        return this;
    }


    public ActionsBot isDisplayed(By locator) {
        wait.until(d -> {
            boolean isDisplayed = d.findElement(locator).isDisplayed();
            System.out.println("Checking if element is displayed: " + locator);
            Assert.assertTrue(isDisplayed, "Element is not displayed");
            System.out.println("Element is displayed");
            return true;
        });
        return this;
    }


    public ActionsBot Submit(By locator) {
        wait.until(d -> {
            System.out.println("Submitting form for element: " + locator);
            d.findElement(locator).submit();
            System.out.println("Form submitted for element: " + locator);
            return true;
        });
        return this;
    }
    public ActionsBot assertLinkHref(By locator, String expectedHref){
        wait.until(d -> {
            String currentHref = d.findElement(locator).getDomAttribute("href");
            System.out.println("Current href: " + currentHref);

            System.out.println("Asserting current href is equal to: " + expectedHref);
            Assert.assertEquals(currentHref, expectedHref, "Assertion FAILED");
            System.out.println("Assertion PASSED");
            return true;
        });
        return this;
    }
    public ActionsBot assertLinkHrefContains(By locator, String expectedText){
        wait.until(d -> {
            String currentHref = d.findElement(locator).getDomAttribute("href");
            System.out.println("Current href: " + currentHref);

            System.out.println("Asserting current href contains: " + expectedText);
            Assert.assertTrue(currentHref.contains(expectedText), "Assertion FAILED");
            System.out.println("Assertion PASSED");
            return true;
        });
        return this;
    }

    public void quitBrowser(){
        wait.until(d -> {
            d.quit();
            System.out.println("Quitting Browser");
            return true;
        });
    }

}
