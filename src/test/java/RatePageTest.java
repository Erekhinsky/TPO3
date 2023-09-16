import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.RatePage;
import utils.DriverHandler;
import utils.PropsHandler;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RatePageTest {

    @BeforeAll
    public static void prepareDrivers() {
        DriverHandler.prepareSystemProps();
    }

    @Test
    void testSalaryFilter() {
        List<WebDriver> drivers = DriverHandler.getDrivers();

        drivers.parallelStream().forEach(driver -> {
            RatePage ratePage = new RatePage(driver);
            ratePage.loadSite(PropsHandler.get("url_rate"));

            ratePage.selectRateEmp(PropsHandler.get("rate1000"));
            List<String> numEmp = ratePage.getTrueNumEmp();

            for (int i = 0; i < numEmp.size(); i = i + 2) {
                assertTrue(PropsHandler.get("rate1000").contains(numEmp.get(i)) &&
                        PropsHandler.get("rate1000").contains(numEmp.get(i+1)));
            }

            driver.quit();
        });
    }
}
