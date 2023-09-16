import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.VacanciesPage;
import utils.DriverHandler;
import utils.PropsHandler;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    void testDistCheckbox() {
        List<WebDriver> drivers = DriverHandler.getDrivers();

        drivers.parallelStream().forEach(driver -> {
            VacanciesPage vacanciesPage = new VacanciesPage(driver);
            vacanciesPage.loadSite(PropsHandler.get("url_vacancies"));

            vacanciesPage.distCheckbox();
            List<String> distStr = vacanciesPage.getDist();

            for (String element : distStr) {
                assertTrue(element.contains("Можно удалённо"));
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

            vacanciesPage.specSelect(PropsHandler.get("search_spec_key_word"));
            List<String> specList = vacanciesPage.getSpec();

            for (String element : specList) {
                assertTrue(element.contains("автоматизации"));
            }

            driver.quit();
        });
    }

    @Test
    void testSpecFilterDiv() {
        List<WebDriver> drivers = DriverHandler.getDrivers();

        drivers.parallelStream().forEach(driver -> {
            VacanciesPage vacanciesPage = new VacanciesPage(driver);
            vacanciesPage.loadSite(PropsHandler.get("url_vacancies"));

            vacanciesPage.specSelectDiv();
            List<String> specList = vacanciesPage.getSpec();

            for (String element : specList) {
                assertTrue(element.contains("автоматизации"));
            }

            driver.quit();
        });
    }

    @Test
    void testSpecFilterReset() {
        List<WebDriver> drivers = DriverHandler.getDrivers();

        String rusStr = "автоматизации";

        drivers.parallelStream().forEach(driver -> {
            VacanciesPage vacanciesPage = new VacanciesPage(driver);
            vacanciesPage.loadSite(PropsHandler.get("url_vacancies"));

            vacanciesPage.specSelectDiv();
            List<String> specList = vacanciesPage.getSpec();

            for (String element : specList) {
                assertTrue(element.contains(rusStr));
            }

            vacanciesPage.specSelectReset();
            specList = vacanciesPage.getSpec();

            assertTrue(vacanciesPage.partlyContains(specList, rusStr));

            driver.quit();
        });
    }

    @Test
    void testFilterReset() {
        List<WebDriver> drivers = DriverHandler.getDrivers();

        drivers.parallelStream().forEach(driver -> {
            VacanciesPage vacanciesPage = new VacanciesPage(driver);
            vacanciesPage.loadSite(PropsHandler.get("url_vacancies"));

            vacanciesPage.distCheckbox();
            vacanciesPage.searchSalary(PropsHandler.get("salary_filter"));
            List<String> distStr = vacanciesPage.getDist();
            List<Double> salaryValue = vacanciesPage.getSalaryValue();

            for (String element : distStr) {
                assertTrue(element.contains("Можно удалённо"));
            }
            for (Double element : salaryValue) {
                assertTrue(element >= Double.parseDouble(PropsHandler.get("salary_filter")));
            }

            vacanciesPage.selectReset();
            distStr = vacanciesPage.getDist();
            salaryValue = vacanciesPage.getSalaryValue();

            assertTrue(vacanciesPage.partlyContains(distStr, "Можно удалённо"));
            assertTrue(vacanciesPage.partlyContainsDouble(salaryValue, PropsHandler.get("salary_filter"))
            || !(vacanciesPage.getJobNames().size()==salaryValue.size()));

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
