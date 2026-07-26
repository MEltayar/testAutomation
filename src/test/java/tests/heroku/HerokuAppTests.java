package tests.heroku;

import pages.heroku.CheckBoxes;
import org.testng.annotations.Test;
import templates.TestScenario;

public class HerokuAppTests extends TestScenario {


    /**
     * Open Google Chrome
     * Navigate to [<a href="http://the-internet.herokuapp.com/checkboxes">...</a>]
     * Check Checkbox 1
     * Assert that both Checkboxes are checked
     * Close Google Chrome
     */
    @Test
    public void assertCheckBox1IsSelected()
    {
        new CheckBoxes(bot)
                .navigateTo()
                .checkCheckBox1()
                .assertThatCheckbox1IsSelected();
    }

    @Test(dependsOnMethods = "assertCheckBox1IsSelected")
    public void assertCheckBox2IsSelected()
    {
        new CheckBoxes(bot)
                .assertThatCheckbox2IsSelected();
    }
}
