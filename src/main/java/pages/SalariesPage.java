package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SalariesPage extends Page {

    @FindBy(xpath = "//input[@value=\"Lead\"]")
    WebElement salariesRadioLead;

    @FindBy(xpath = "//div[@class=\"login-menu\"]/div/div/button")
    WebElement logButton;

    @FindBy(xpath = "//div[@class=\"login-menu__card\"]/button[contains(text(),\"Хабр\")]")
    WebElement logButtonHabr;

    @FindBy(xpath = "//button[@name=\"go\"]")
    WebElement logInButtonHabr;

    @FindBy(xpath = "//a[@title=\"Войти с помощью Вконтакте\"]")
    WebElement logButtonVK;

    @FindBy(xpath = "//input")
    WebElement logInputLogin;

    @FindBy(xpath = "//button/span/span[contains(text(),\"Продолжить\")]")
    WebElement logButtonVKContinueLogin;

    @FindBy(xpath = "//button/span/span/span/../../..")
    WebElement logButtonVKContinuePassword;

    @FindBy(xpath = "//input[@name=\"password\"]")
    WebElement logInputPassword;

    public SalariesPage(WebDriver driver) {
        super(driver);
    }

    public void logIn(String login, String password) {
        //W1sxMDAwODA3NzIwXSwiJDJhJDEwJFA3YWh6MkFTMzFSRS9NQ2J5c3RiTWUiLCIxNjk0ODQ1NDU0LjQxNzk5NDUiXQ%3D%3D--e7de31c34b5721f1e74a77de901172a994de3bf9
        //1694764404651017798

        String url = driver.getCurrentUrl();
        logButton.click();
        logButtonHabr.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(url)));
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]")));
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.recaptcha-checkbox-checkmark"))).click();
        logInButtonHabr.click();
//        logButtonVK.click();
//        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
//        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(url)));
//        logInputLogin.sendKeys(login);
//        logButtonVKContinueLogin.click();
//        logInputPassword.sendKeys(password);
//        logButtonVKContinuePassword.click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(url)));
    }

    public void tryLookSalariesLead() {
        String url = driver.getCurrentUrl();
        salariesRadioLead.click();
    }

    public void lookSalariesLead() {
        String url = driver.getCurrentUrl();
        salariesRadioLead.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(url)));
    }

}
