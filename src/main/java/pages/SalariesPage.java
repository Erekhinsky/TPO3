package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SalariesPage extends Page {

    @FindBy(xpath = "//input[@value=\"Lead\"]")
    WebElement salariesRadioLead;

    public SalariesPage(WebDriver driver) {
        super(driver);
    }

    public void tryLookSalariesLead() {
        salariesRadioLead.click();
    }

    public void lookSalariesLead() {
        String url = driver.getCurrentUrl();
        salariesRadioLead.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(url)));
    }

}
