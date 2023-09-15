package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage extends Page{

    @FindBy(xpath = "//input[@class=\"l-page-title__input\"]")
    WebElement careerFormInput;

    @FindBy(xpath = "//button[contains(@class, \"l-page-title__form-submit\")]")
    WebElement careerFormSubmitButton;

    @FindBy(xpath = "//div[@data-tab=\"dev\"]//span[contains(text(), \"Больше вакансий\")]/../..")
    WebElement moreVacanciesButton;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void searchCareer(String input) {
        String url = driver.getCurrentUrl();
        careerFormInput.clear();
        careerFormInput.sendKeys(input);
        careerFormSubmitButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(url)));
    }

    public void moreVacancies() {
        String url = driver.getCurrentUrl();
        moreVacanciesButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(url)));
    }


}
