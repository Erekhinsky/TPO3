import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.FavoritesPage;
import pages.VacanciesPage;
import utils.CookieController;
import utils.DriverHandler;
import utils.PropsHandler;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FavoritesPageTest {

    @BeforeAll
    public static void prepareDrivers() {
        DriverHandler.prepareSystemProps();
    }

    @Test
    void testAddToFavorites() {
        List<WebDriver> drivers = DriverHandler.getDrivers();

        drivers.parallelStream().forEach(driver -> {
            VacanciesPage vacanciesPage = new VacanciesPage(driver);
            //FavoritesPage favoritesPage = new FavoritesPage(driver);
            vacanciesPage.loadSite(PropsHandler.get("url_vacancies"));

//            File file = new File("browser.dat");
//            if (file.exists()) {
//                CookieController.writeCookiesToFile(driver);
//            } else {
//                driver.manage().addCookie(CookieController.readCookiesFromFile());
//            }

            driver.manage().addCookie(CookieController.readCookiesFromFile());
            vacanciesPage.loadSite(PropsHandler.get("url_favorites"));
            vacanciesPage.deleteFavourites();
            //CookieController.writeCookiesToFile(driver);

            List<String> jobs1 = vacanciesPage.getJobNames();
            Collections.sort(jobs1);

            vacanciesPage.addFavourites();
            List<String> jobs2  = vacanciesPage.getJobNames();
            Collections.sort(jobs2);

            assertArrayEquals(jobs1.toArray(), jobs2.toArray());

            driver.quit();
        });
    }
}
