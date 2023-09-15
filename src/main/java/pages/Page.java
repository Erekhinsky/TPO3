package pages;

import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.DriverHandler;
import utils.PropsHandler;

@Data
public abstract class Page {

    protected WebDriver driver;

    public Page(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void loadSite(String resourceLocator) {
        PageFactory.initElements(driver, this);
        driver.get(resourceLocator);
        driver.manage().window().maximize();
        DriverHandler.waitUntilPageLoads(driver, 60);
    }

    public void loadSite() {
        PageFactory.initElements(driver, this);
        driver.get(PropsHandler.get("url"));
        driver.manage().window().maximize();
        DriverHandler.waitUntilPageLoads(driver, 60);
    }



}
