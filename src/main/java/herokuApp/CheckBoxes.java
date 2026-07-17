package herokuApp;

import engine.ActionsBot;
import org.openqa.selenium.By;

public class CheckBoxes {
    ActionsBot bot;

    public CheckBoxes(ActionsBot bot) {
        this.bot = bot;
    }
    By checkBox1 = By.xpath("(//input[@type='checkbox'])[1]");
    By checkBox2 = By.xpath("(//input[@type='checkbox'])[2]");

    public CheckBoxes navigateTo() {
        bot.navigateTo("http://the-internet.herokuapp.com/checkboxes");
        return this;
    }

    public CheckBoxes checkCheckBox1() {
        bot.Click(checkBox1);
        return this;
    }
    public void assertThatCheckbox1IsSelected() {
        bot.isSelected(checkBox1);
    }
    public void assertThatCheckbox2IsSelected() {
        bot.isSelected(checkBox2);
    }
}
