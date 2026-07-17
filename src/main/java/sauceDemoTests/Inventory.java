package sauceDemoTests;

import engine.ActionsBot;

public class Inventory {

    ActionsBot bot;
    public Inventory(ActionsBot bot) {
        this.bot = bot;
    }

    String inventoryPageURL = "https://www.saucedemo.com/inventory.html";

    public void assertInventoryPageURL() {
       bot.assertUrl(inventoryPageURL);
    }
}