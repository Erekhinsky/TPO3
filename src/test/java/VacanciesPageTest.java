import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.VacanciesPage;
import utils.DriverHandler;
import utils.PropsHandler;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class VacanciesPageTest {

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

            vacanciesPage.searchJob(PropsHandler.get("search_key_word"));
            List<String> jobNames = vacanciesPage.getJobNames();

            for (String element : jobNames) {
                assertTrue(element.contains(PropsHandler.get("search_key_word")));
            }

            driver.quit();
        });
    }

    @Test
    void testSalaryFilter() {
        List<WebDriver> drivers = DriverHandler.getDrivers();

        drivers.parallelStream().forEach(driver -> {
            VacanciesPage vacanciesPage = new VacanciesPage(driver);
            vacanciesPage.loadSite(PropsHandler.get("url_vacancies"));

            vacanciesPage.searchSalary(PropsHandler.get("salary_filter"));
            List<Double> salaryValue = vacanciesPage.getSalaryValue();

            for (Double element : salaryValue) {
                assertTrue(element >= Double.parseDouble(PropsHandler.get("salary_filter")));
            }

            driver.quit();
        });
    }

    @Test
    void testQualFilter() {
        List<WebDriver> drivers = DriverHandler.getDrivers();

        drivers.parallelStream().forEach(driver -> {
            VacanciesPage vacanciesPage = new VacanciesPage(driver);
            vacanciesPage.loadSite(PropsHandler.get("url_vacancies"));

            vacanciesPage.qualSelect(PropsHandler.get("qualMid"));
            List<String> qualList = vacanciesPage.getQual();

            for (String element : qualList) {
                assertTrue(element.contains(PropsHandler.get("qualMid")));
            }

            driver.quit();
        });
    }

    @Test
    void testSpecFilter() {
        List<WebDriver> drivers = DriverHandler.getDrivers();

        drivers.parallelStream().forEach(driver -> {
            VacanciesPage vacanciesPage = new VacanciesPage(driver);
            vacanciesPage.loadSite(PropsHandler.get("url_vacancies"));

            vacanciesPage.specSelectOpen(PropsHandler.get("search_spec_key_word_eng"));
            List<String> specList = vacanciesPage.getSpec();

            for (String element : specList) {
                System.out.println(element + " " + PropsHandler.get("search_spec_key_word_rus") + "\n");
                assertTrue(element.contains(PropsHandler.get("search_spec_key_word_rus")));
            }

            driver.quit();
        });
    }

    @Test
    void testSalarySort() {
        List<WebDriver> drivers = DriverHandler.getDrivers();

        drivers.parallelStream().forEach(driver -> {
            VacanciesPage vacanciesPage = new VacanciesPage(driver);
            vacanciesPage.loadSite(PropsHandler.get("url_vacancies"));

            vacanciesPage.salarySortDesc();
            List<Double> salaryArray = vacanciesPage.getSalaryValue();

            assertTrue(vacanciesPage.isSorted(salaryArray));

            driver.quit();
        });
    }

}
