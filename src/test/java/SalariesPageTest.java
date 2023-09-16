import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.SalariesPage;
import pages.VacanciesPage;
import utils.CookieController;
import utils.DriverHandler;
import utils.PropsHandler;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SalariesPageTest {

    @BeforeAll
    public static void prepareDrivers() {
        DriverHandler.prepareSystemProps();
    }

    @Test
    void testLookSalariesLead() {
        List<WebDriver> drivers = DriverHandler.getDrivers();

        drivers.parallelStream().forEach(driver -> {
            SalariesPage salariesPage = new SalariesPage(driver);
            salariesPage.loadSite(PropsHandler.get("url_salaries"));

            salariesPage.tryLookSalariesLead();
            assertEquals(driver.getCurrentUrl(), PropsHandler.get("url_salaries"));

            File file = new File("browser.dat");
            if (file.exists() && file.canRead()) {
                CookieController.writeCookiesToFile(driver);
            } else {
                driver.manage().addCookie(CookieController.readCookiesFromFile());
            }
            //driver.manage().addCookie(CookieController.readCookiesFromFile());
            //CookieController.writeCookiesToFile(driver);

            salariesPage.loadSite(PropsHandler.get("url_salaries"));
            salariesPage.lookSalariesLead();
            assertEquals(driver.getCurrentUrl(), PropsHandler.get("url_salaries_lead"));

            driver.quit();
        });
    }
}

