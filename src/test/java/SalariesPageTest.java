import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.SalariesPage;
import pages.VacanciesPage;
import utils.DriverHandler;
import utils.PropsHandler;

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

            salariesPage.logIn(PropsHandler.get("login"), PropsHandler.get("password"));
            salariesPage.lookSalariesLead();
            assertEquals(driver.getCurrentUrl(), PropsHandler.get("url_salaries_lead"));

            driver.quit();
        });
    }
}

