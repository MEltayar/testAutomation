package pages.duckduckgo;

import engine.ActionsBot;
import engine.config.Config;
import org.openqa.selenium.By;

public class DuckDuckGoHomePage {

    private final ActionsBot bot;

    private final By duckDuckGoLogo = By.xpath("(//a[@title='Learn about DuckDuckGo'])[2]");
    private final By searchBoxInput = By.id("searchbox_input");
    private final By firstLinkResult = By.xpath("(//a[@data-testid='result-title-a'])[1]");
    private final By secondResultLink = By.xpath("(//article[@id='r1-0']//a[@data-testid='result-title-a'])");

    private final String homePageURL = Config.get("duckDuckGoBaseURL");

    public DuckDuckGoHomePage(ActionsBot bot) {
        this.bot = bot;
    }

    public DuckDuckGoHomePage navigateTo() {
        bot.browser().navigateTo(homePageURL);
        return this;
    }

    public DuckDuckGoHomePage assertPageTitleIsGoogle() {
        bot.assertThat().browser().titleIs("Google");
        return this;
    }

    public DuckDuckGoHomePage assertLogoIsDisplayed() {
        bot.assertThat().element(duckDuckGoLogo).isDisplayed();
        return this;
    }

    public DuckDuckGoHomePage searchForSeleniumWebdriver() {
        bot.element()
           .type(searchBoxInput, "Selenium WebDriver")
           .submit(searchBoxInput);
        return this;
    }

    public DuckDuckGoHomePage searchForSeleniumCucumberIO() {
        bot.element()
           .type(searchBoxInput, "Cucumber IO")
           .submit(searchBoxInput);
        return this;
    }

    public void assertFirstResultLink() {
        bot.assertThat().element(firstLinkResult).linkHrefIs("https://www.selenium.dev/documentation/webdriver/");
    }

    // Assert that the link of the second result contains [https://www.linkedin.com]
    public void assertSecondResultLinkContainsLinkedIn() {
        bot.assertThat().element(secondResultLink).linkHrefContains("https://www.linkedin.com");
    }

}
