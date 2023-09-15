import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import utils.DriverHandler;
import utils.PropsHandler;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MainPageTest {

    @BeforeAll
    public static void prepareDrivers() {
        DriverHandler.prepareSystemProps();
    }

    @Test
    void testCareerFormInput() {
        List<WebDriver> drivers = DriverHandler.getDrivers();
        drivers.parallelStream().forEach(driver -> {
            MainPage mainPage = new MainPage(driver);
            mainPage.loadSite(PropsHandler.get("url"));

            String newUrl = PropsHandler.get("url_vacancies_keyword") + PropsHandler.get("search_key_word")
                    + PropsHandler.get("url_vacancies_keyword_tail");
            mainPage.searchCareer(PropsHandler.get("search_key_word"));

            assertEquals(driver.getCurrentUrl(), newUrl);

            driver.quit();
        });
    }

    @Test
    void testMoreVacanciesButton() {
        List<WebDriver> drivers = DriverHandler.getDrivers();
        drivers.parallelStream().forEach(driver -> {
            MainPage mainPage = new MainPage(driver);
            mainPage.loadSite(PropsHandler.get("url"));

            String newUrl = PropsHandler.get("url_vacancies_develop_filter");
            mainPage.moreVacancies();

            assertEquals(driver.getCurrentUrl(), newUrl);

            driver.quit();
        });
    }
}

