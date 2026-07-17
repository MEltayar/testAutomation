/*

package testPackage;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import sauceDemoPages.templates.TestCase;

import java.io.File;

public class TestClass {

    @Test
    public void duckduckgoPageTitle(){

        driver.get("https://duckduckgo.com");
        var pageTitle =  driver.getTitle();
        Assert.assertEquals(pageTitle, "Google");

    }

    @Test
    public void logoIsDisplayed(){

        driver.get("https://duckduckgo.com");
        var Logo =  driver.findElement(By.xpath("//div[contains(@class,'header_headerWrapDesktop')]//a[@aria-label = 'Learn about DuckDuckGo']/picture/img"));
        Assert.assertTrue(Logo.isDisplayed(), "Logo is not displayed on the page");

    }
    @Test
    public void checkSeleniumFirstResult(){

        driver.get("https://duckduckgo.com");

        var searchBox = driver.findElement(By.id("searchbox_input"));
        searchBox.sendKeys("Selenium WebDriver");
        searchBox.submit();

        var firstLink = driver.findElement(By.xpath("//article[@id='r1-0']//a[@data-testid='result-title-a']"));
        var  href = firstLink.getAttribute("href");
        Assert.assertEquals(href, "https://www.selenium.dev/documentation/webdriver/", "First result is not 'https://www.selenium.dev/documentation/webdriver/' ");

    }

        ///***Task 5 ***
////
*
     * Open Google Chrome
     * Navigate to [https://duckduckgo.com/]
     * Search for [Cucumber IO]
     * Assert that the link of the second result contains [https://www.linkedin.com]
     * Close Google Chrome


    @Test
    public void checkSeleniumSecondResult(){

        //Open Google Chrome

        //Navigate to [https://duckduckgo.com/]
        driver.get("https://duckduckgo.com");

        //Search for [Cucumber IO]
        driver.findElement(By.id("searchbox_input")).sendKeys("Cucumber IO" + Keys.ENTER);

        // Assert that the link of the second result contains [https://www.linkedin.com]
        Assert.assertTrue(driver.findElement(By.xpath("(//a[@data-testid='result-title-a'])[2]")).getDomAttribute("href").contains("https://www.linkedin.com"), "Second result does not contain the expected URL");
    }

    ///****Task 6 ***
////

*
     * Open Google Chrome
     * Navigate to [http://the-internet.herokuapp.com/checkboxes]
     * Check Checkbox 1
     * Assert that both Checkboxes are checked
     * Close Google Chrome


    @Test
    public void checkSeleniumThirdResult(){
        //Open Google Chrome

        //Navigate to [http://the-internet.herokuapp.com/checkboxes]
        driver.get("http://the-internet.herokuapp.com/checkboxes");

        //Check Checkbox 1

        By checkBoxOneLocator = By.xpath("//input[@type='checkbox'][following-sibling::text()[contains(., 'checkbox 1')]]");
        driver.findElement(checkBoxOneLocator).click();

        // Assert that both Checkboxes are checked
        By checkBoxTwoLocator = By.xpath("//input[@type='checkbox'][following-sibling::text()[contains(., 'checkbox 2')]]");
        Assert.assertTrue(driver.findElement(checkBoxTwoLocator).isSelected() && driver.findElement(checkBoxOneLocator).isSelected(), "Boxes are not checked");
    }

    ////****task 7 **
////
*
     * Open Google Chrome
     * Navigate to [https://www.w3schools.com/html/html_tables.asp]
     * Assert that the Country for the Company [Ernst Handel] is [Austria]
     * Close Google Chrome


    @Test
    public void checkTheCountryForTheCompanyErnstHandelIsAustria(){

        //Open Google Chrome

        //Navigate to [https://www.w3schools.com/html/html_tables.asp]
        driver.get("https://www.w3schools.com/html/html_tables.asp");

        // Get the Country for the Company [Ernst Handel]
        String country = driver.findElement(By.xpath("//table[@id='customers']//tr[td[text()='Ernst Handel']]/td[3]")).getText();


        //Assert that the Country for the Company [Ernst Handel] is [Austria]
        Assert.assertEquals(country, "Austria");
    }


    ///****Task 9 **
///

*
     * Open Google Chrome
     * Navigate to [http://the-internet.herokuapp.com/upload]
     * Upload a small image file
     * Assert that the file was uploaded successfully
     * Close Google Chrome

    @Test
    public void uploadFile(){
        //open google chrome

        //Navigate to [http://the-internet.herokuapp.com/upload]
        driver.get("http://the-internet.herokuapp.com/upload");

        //Upload a small image file
        File uploadFile = new File("C:\\Users\\mosta\\GitHub\\testAutomation\\src\\test\\resources\\360_F_800407388_lrcVUTH5bxJfLZECnYufRbR8RWSgWTkI.jpg");
        driver.findElement(By.id("file-upload")).sendKeys(uploadFile.getAbsolutePath());
        driver.findElement(By.id("file-submit")).click();

        // Assert that the file was uploaded successfully
        Assert.assertTrue(driver.findElement(By.id("uploaded-files")).isDisplayed(), "File was not uploaded successfully");

    }

    /////**** Task 9 ***
///

import org.testng.annotations.Test;

*
     * Open Google Chrome
     * Navigate to [https://jqueryui.com/resources/demos/droppable/default.html]
     * Drag [Drag me to my target] and drop it on [Drop here]
     * Assert that the text has been changed to [Dropped!]
     * Close Google Chrome


    @Test
    public void dragAndDrop(){

        // Open Google Chrome

        //Navigate to [https://jqueryui.com/resources/demos/droppable/default.html]
        driver.get("https://jqueryui.com/resources/demos/droppable/default.html");

        //Drag [Drag me to my target] and drop it on [Drop here]


        new Actions(driver)
                .dragAndDrop(driver.findElement(By.id("draggable")), driver.findElement(By.id("droppable")))
                .perform();

        //Assert that the text has been changed to [Dropped!]
        Assert.assertEquals(driver.findElement(By.id("droppable")).getText(), "Dropped!");
    }
    }
*/
