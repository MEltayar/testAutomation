package pages.saucedemo;

import engine.ActionsBot;
import engine.config.Config;

public class Inventory {

    ActionsBot bot;
    public Inventory(ActionsBot bot) {
        this.bot = bot;
    }

    String inventoryPageURL = Config.get("sauceDemoBaseURL") + "/inventory.html";

    public void assertInventoryPageURL() {
       bot.assertThat().browser().urlIs(inventoryPageURL);
    }
}
