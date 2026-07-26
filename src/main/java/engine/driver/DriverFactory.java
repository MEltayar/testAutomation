package engine.driver;

import engine.config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

// Builds a configured browser. Which browser is decided by config.properties.
public class DriverFactory {

    public static WebDriver createDriver() {
        String browser = Config.browser().trim().toLowerCase();
        return switch (browser) {
            case "chrome"  -> new ChromeDriver(chromeOptions());
            case "firefox" -> new FirefoxDriver(firefoxOptions());
            default -> throw new IllegalArgumentException("Unsupported browser in config: " + browser);
        };
    }

    private static ChromeOptions chromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--incognito");
        options.addArguments("--remote-allow-origins=*");
        // options.addArguments("--headless=new");
        options.setExperimentalOption("detach", true);
        return options;
    }

    private static FirefoxOptions firefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--width=1920");
        options.addArguments("--height=1080");
        options.addArguments("-private");
        // options.addArguments("-headless");
        return options;
    }
}
