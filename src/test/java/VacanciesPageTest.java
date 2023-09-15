import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.MainPage;
import pages.VacanciesPage;
import utils.DriverHandler;
import utils.PropsHandler;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VacanciesPageTest {

    @FindBy(xpath = "//div[@class=\"vacancy-card__title\"]/a")
    List<WebElement> jobsName;

    @BeforeAll
    public static void prepareDrivers() {
        DriverHandler.prepareSystemProps();
    }

    @Test
    void testJobFormInput() {
        List<WebDriver> drivers = DriverHandler.getDrivers();

        drivers.parallelStream().forEach(driver -> {
            VacanciesPage vacanciesPage = new VacanciesPage(driver);
            vacanciesPage.loadSite(PropsHandler.get("url_vacancies"));

            String newUrl = PropsHandler.get("url_vacancies_keyword") + PropsHandler.get("search_key_word")
                    + PropsHandler.get("url_jobs_keyword_tail");
            vacanciesPage.searchJob(PropsHandler.get("search_key_word"));

            assertEquals(driver.getCurrentUrl(), newUrl);

            for (jobsName.listIterator())

            driver.quit();
        });
    }
}
