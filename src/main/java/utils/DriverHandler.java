package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class DriverHandler {

    public static void prepareSystemProps() {
        System.setProperty(PropsHandler.get("chrome_sys_prop_name"), PropsHandler.get("chrome_sys_prop_path"));
        System.setProperty(PropsHandler.get("gecko_sys_prop_name"), PropsHandler.get("gecko_sys_prop_path"));
    }

    private static ChromeDriver getChromeDriver() {

        if (!System.getProperties().containsKey(PropsHandler.get("chrome_sys_prop_name"))) {
            throw new RuntimeException("Chrome driver system property not set!");
        }
        return new ChromeDriver();
    }

    private static FirefoxDriver getFirefoxDriver() {
        if (!System.getProperties().containsKey(PropsHandler.get("gecko_sys_prop_name"))) {
            throw new RuntimeException("Gecko driver system property not set!");
        }
        return new FirefoxDriver();
    }

    public static List<WebDriver> getDrivers() {
        List<WebDriver> drivers = new ArrayList<>();
        drivers.add(getChromeDriver());
        //drivers.add(getFirefoxDriver());
        drivers.parallelStream()
                .forEach(driver -> driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1)));
        return drivers;
    }

    public static void waitUntilPageLoads(WebDriver driver, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }


}
