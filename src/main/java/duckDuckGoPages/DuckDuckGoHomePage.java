package duckDuckGoPages;

import engine.ActionsBot;
import org.openqa.selenium.By;

public class DuckDuckGoHomePage {

    ActionsBot bot;
    By duckDuckGoLogo = By.xpath("(//a[@title='Learn about DuckDuckGo'])[2]");
    By searchBoxInput = By.id("searchbox_input");
    By firstLinkResult = By.xpath("(//a[@data-testid='result-title-a'])[1]");
    By secondResultLink = By.xpath("(//article[@id='r1-0']//a[@data-testid='result-title-a'])");

    public DuckDuckGoHomePage(ActionsBot bot) {
        this.bot = bot;
    }

    public DuckDuckGoHomePage navigateTo(){
        bot.navigateTo(homePageURL);
        return this;
    }

    String homePageURL = "https://duckduckgo.com";

    public void assertPageTitle(String title) {
        bot.assertTitle(title);
    }

    public void assertLogoIsDisplayed() {
        bot.isDisplayed(duckDuckGoLogo);
    }

    public DuckDuckGoHomePage searchForSeleniumWebdriver() {
        bot.Type(searchBoxInput, "Selenium WebDriver");
        bot.Submit(searchBoxInput);
        return this;
    }
    public DuckDuckGoHomePage searchForSeleniumCucumberIO() {
        bot.Type(searchBoxInput, "Cucumber IO");
        bot.Submit(searchBoxInput);
        return this;
    }

    public void assertFirstResultLink(){
        bot.assertLinkHref(firstLinkResult, "https://www.selenium.dev/documentation/webdriver/");
    }
    //Assert that the link of the second result contains [https://www.linkedin.com]
    public void assertSecondResultLinkContainsLinkedIn(){
        bot.assertLinkHrefContains(secondResultLink, "https://www.linkedin.com");
    }

}
