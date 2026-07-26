package templates;

import engine.ActionsBot;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class TestScenario {

    public ActionsBot bot;

    @BeforeClass
    public void setUp() {
        bot = new ActionsBot();
    }

    @AfterClass
    public void tearDown() {
        bot.browser().quitBrowser();
    }
}
