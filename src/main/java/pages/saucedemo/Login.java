package pages.saucedemo;

import engine.ActionsBot;
import engine.config.Config;
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
    String loginURL = Config.get("sauceDemoBaseURL") + "/";

    public Login navigateTo() {
        bot.browser().navigateTo(loginURL);
        return new Login(bot);
    }

    public Inventory login(String username, String password) {
        bot.element()
           .type(usernameInput, username)
           .type(passwordInput, password)
           .click(loginButton);
        return new Inventory(bot);

    }

    // make one change to check the CI/CD pipleline

}
