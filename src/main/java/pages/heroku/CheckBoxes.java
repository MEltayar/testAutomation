package pages.heroku;

import engine.ActionsBot;
import engine.config.Config;
import org.openqa.selenium.By;

public class CheckBoxes {
    ActionsBot bot;

    public CheckBoxes(ActionsBot bot) {
        this.bot = bot;
    }
    By checkBox1 = By.xpath("(//input[@type='checkbox'])[1]");
    By checkBox2 = By.xpath("(//input[@type='checkbox'])[2]");

    public CheckBoxes navigateTo() {
        bot.browser().navigateTo(Config.get("herokuBaseURL") + "/checkboxes");
        return this;
    }

    public CheckBoxes checkCheckBox1() {
        bot.element().click(checkBox1);
        return this;
    }
    public void assertThatCheckbox1IsSelected() {
        bot.assertThat().element(checkBox1).isSelected();
    }
    public void assertThatCheckbox2IsSelected() {
        bot.assertThat().element(checkBox2).isSelected();
    }
}
