package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class VacanciesPage extends Page {


    @FindBy(xpath = "//span[@class=\"text-input text-input--withIcon\"]/input[@placeholder=\"Поиск\"]")
    WebElement jobFormInput;

    @FindBy(xpath = "//div[@class=\"vacancy-card__title\"]/a")
    WebElement jobsName;

    @FindBy(xpath = "//div[@data-tab=\"dev\"]//span[contains(text(), \"Больше вакансий\")]/../..")
    WebElement moreVacanciesButton;

    public VacanciesPage(WebDriver driver) {
        super(driver);
    }

    public void searchJob(String input) {
        String url = driver.getCurrentUrl();
        jobFormInput.clear();
        jobFormInput.sendKeys(input);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(url)));
    }






}
