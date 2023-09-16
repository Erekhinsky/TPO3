package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropsHandler;

import java.time.Duration;
import java.util.List;

public class FavoritesPage extends Page {

    @FindBy(xpath = "//div[@class=\"vacancy-card__actions\"]/button")
    List<WebElement> vacanciesCardToFav;

    @FindBy(xpath = "//div[@class=\"vacancy-card__actions\"]/button[contains(text(),\"Удалить\")]")
    List<WebElement> vacanciesCardToFavPressed;

    @FindBy(xpath = "//div[@class=\"header__top_main_menu\"]/a[contains(text(),\"Вакансии\")]")
    WebElement vacanciesGoTo;

    public FavoritesPage(WebDriver driver) {
        super(driver);
    }

    public void deleteFavourites() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.urlToBe(PropsHandler.get("url_favorites")));
        String url = driver.getCurrentUrl();
        if (vacanciesCardToFav != null) {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"vacancy-card__actions\"]")));
            for (WebElement element: vacanciesCardToFav) {
                element.click();
            }
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                    "//div[@class=\"vacancy-card__actions\"]/button[contains(text(),\"Удалить\")]")));
        }
        vacanciesGoTo.click();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(url)));
    }
}
