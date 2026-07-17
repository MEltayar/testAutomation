package duckDuckGoTests;

import duckDuckGoPages.DuckDuckGoHomePage;
import org.testng.annotations.Test;
import templates.TestCase;

public class DuckDuckGoTests extends TestCase {

    /**
     * Open Google Chrome
     * Navigate to [<a href="https://duckduckgo.com/">...</a>]
     * Assert that the page title is [Google]
     * Close Google Chrome
     */

    @Test
    public void checkPageTitle() {
       new DuckDuckGoHomePage(bot)
               .navigateTo()
               .assertPageTitle("Google");
    }


    /**
     * Open Google Chrome
     * Navigate to [<a href="https://duckduckgo.com/">...</a>]
     * Assert that the DuckDuckGo logo is displayed
     * Close Google Chrome
     */
    @Test
    public void assertDuckDuckGoLogoIsDisplayed() {
        new DuckDuckGoHomePage(bot)
                .navigateTo()
                .assertLogoIsDisplayed();
    }

    /**
     * Open Google Chrome
     * Navigate to [<a href="https://duckduckgo.com/">...</a>"]
     * Search for [Selenium WebDriver]
     * Assert that the link of the first result is [<a href="https://www.selenium.dev/documentation/webdriver/">...</a>]
     * Close Google Chrome
     */

    @Test
    public void assertTheLinkOfTheFirstResult() {
        new DuckDuckGoHomePage(bot)
                .navigateTo()
                .searchForSeleniumWebdriver()
                .assertFirstResultLink();
    }

    /**
     * Open Google Chrome
     * Navigate to [<a href="https://duckduckgo.com/">...</a>]
     * Search for [Cucumber IO]
     * Assert that the link of the second result contains [<a href="https://www.linkedin.com">...</a>]
     * Close Google Chrome
     */

    @Test
    public void assertTheLinkOfTheSecondResultContainsLinkedIn() {
        new DuckDuckGoHomePage(bot)
                .navigateTo()
                .searchForSeleniumCucumberIO()
                .assertSecondResultLinkContainsLinkedIn();
    }
}
