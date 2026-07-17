package sauceDemoTests;

import engine.ActionsBot;
import org.openqa.selenium.By;

public class Login {
    ActionsBot bot;

    //Create Constructor

    public Login(ActionsBot bot) {
        this.bot = bot;
    }


    By usernameInput = By.id("user-name");
    By passwordInput = By.id("password");
    By loginButton = By.id("login-button");
    String loginURL = "https://www.saucedemo.com/";

    public Login navigateTo() {
        bot.navigateTo(loginURL);
        return new Login(bot);
    }

    public Inventory login(String username, String password) {
        bot.Type(usernameInput, username);
        bot.Type(passwordInput, password);
        bot.Click(loginButton);
        return new Inventory(bot);

    }

}
