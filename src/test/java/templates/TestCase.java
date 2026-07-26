package templates;

import engine.ActionsBot;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class TestCase {

    public ActionsBot bot;

    @BeforeMethod
    public void setUp() {
        bot = new ActionsBot();
    }

    @AfterMethod
    public void tearDown() {
        bot.browser().quitBrowser();
    }
}
