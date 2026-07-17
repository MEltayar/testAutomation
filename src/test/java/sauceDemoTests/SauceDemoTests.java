package sauceDemoTests;

import org.testng.annotations.Test;
import templates.TestCase;

public class SauceDemoTests extends TestCase {

    @Test
    public void successfulLoginTest() {

       new Login(bot)
               .navigateTo()
               .login("standard_user", "secret_sauce")
               .assertInventoryPageURL();
    }
    }
