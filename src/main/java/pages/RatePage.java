package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class RatePage extends Page {

    @FindBy(xpath = "//button/span[contains(text(),\"1000 - 5000\")]")
    WebElement rateFilterButton1000;

    @FindBy(xpath = "//button/span[contains(text(),\"100 - 1000\")]")
    WebElement rateFilterButton100;

    @FindBy(xpath = "//button/span[contains(text(),\"10 - 100\")]")
    WebElement rateFilterButton10;

    @FindBy(xpath = "//span[@class=\"rating-card__company-title\"]")
    List<WebElement> companiesByEmp;

    @FindBy(xpath = "//div[@class=\"employees\"]")
    WebElement allEmp;

    public RatePage(WebDriver driver) {
        super(driver);
    }

    public void selectRateEmp(String select) {
        String url = driver.getCurrentUrl();
        if (select.equals("1000 - 5000")) {
            rateFilterButton1000.click();
        } else if (select.equals("100 - 1000")) {
            rateFilterButton100.click();
        } else if (select.equals("10 - 100")) {
            rateFilterButton10.click();
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(url)));
    }

    public List<String> getTrueNumEmp() {
        List<String> numEmp = new ArrayList<>();
        for (WebElement element : companiesByEmp) {
            element.click();
            ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            List<String> strs = List.of(allEmp.getText().split(" "));
            for (String str : strs) {
                if (str.matches("\\d+")) {
                    numEmp.add(str);
                }
            }
            driver.close();
            driver.switchTo().window(tabs.get(0));
        }
        return numEmp;
    }

}
