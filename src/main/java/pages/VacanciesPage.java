package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropsHandler;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class VacanciesPage extends Page {

    @FindBy(xpath = "//span[@class=\"text-input text-input--withIcon\"]/input[@placeholder=\"Поиск\"]")
    WebElement jobFormInput;

    @FindBy(xpath = "//div[@class=\"vacancy-card__title\"]/a")
    List<WebElement> jobsNames;

    @FindBy(xpath = "//div[@class=\"basic-salary\"]")
    List<WebElement> salaries;

    @FindBy(xpath = "//option[@value=\"salary_desc\"]")
    WebElement salariesSortDesc;

    @FindBy(xpath = "//select[@class=\"select__input\"]/option[@value=\"relevance\"]/..")
    WebElement salariesSort;

    @FindBy(xpath = "//input[@placeholder=\"От\"]")
    WebElement salariesFilterInput;

    @FindBy(xpath = "//select[@class=\"select__input\"]/option[@value=\"1\"]/..")
    WebElement qualSelect;

    @FindBy(xpath = "//select[@class=\"select__input\"]/option[@value=\"1\"]")
    WebElement internQualSelect;

    @FindBy(xpath = "//select[@class=\"select__input\"]/option[@value=\"3\"]")
    WebElement juniorQualSelect;

    @FindBy(xpath = "//select[@class=\"select__input\"]/option[@value=\"4\"]")
    WebElement middleQualSelect;

    @FindBy(xpath = "//select[@class=\"select__input\"]/option[@value=\"5\"]")
    WebElement seniorQualSelect;

    @FindBy(xpath = "//select[@class=\"select__input\"]/option[@value=\"6\"]")
    WebElement leadQualSelect;

    @FindBy(xpath = "//div[@class=\"vacancy-card__skills\"]/span/span/span/span")
    List<WebElement> qualAndSpec;

    @FindBy(xpath = "//div[@class=\"specs-picker__specs\"]")
    WebElement specFilterOpen;

    @FindBy(xpath = "//input[@placeholder=\"Поиск\"]/../../span[@class=\"text-input specs-search__input " +
            "text-input--appearance-search\"]/input")
    WebElement specFilterSearch;

    @FindBy(xpath = "//div[@data-dropdown-open=\"open\"]")
    WebElement specFilterAfterSearch;

    @FindBy(xpath = "//button[@class=\"button-comp button-comp--appearance-primary button-comp--size-l\"]")
    WebElement specFilterSubmitButton;

    @FindBy(xpath = "//button[@class=\"button-comp button-comp--appearance-secondary button-comp--size-l\"]")
    WebElement specFilterResetButton;

    @FindBy(xpath = "//div[@class=\"filters-controls filters-controls--appearance-search-panel\"]/button[@class=\"" +
            "button-comp button-comp--appearance-pseudo-link-muted button-comp--size-l\"]")
    WebElement filterResetButton;

    @FindBy(xpath = "//div[@class=\"specs-selector__group-title\"]/div[contains(text(),\"Quality Assurance\")]")
    WebElement specFilterQuality;

    @FindBy(xpath = "//label//div[contains(text(),\"Test Automation Engineer\")]")
    WebElement specFilterTestEngineer;

    @FindBy(xpath = "//label/span[contains(text(),\"Можно удалённо\")]/../..")
    WebElement specFilterDistCheckbox;

    @FindBy(xpath = "//div[@class=\"vacancy-card__meta\"]/span")
    List<WebElement> specFilterTypeWorking;

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

    public void specSelect(String input) {
        String url = driver.getCurrentUrl();
        specFilterOpen.click();
        specFilterSearch.sendKeys(input);
        specFilterAfterSearch.click();
        specFilterSubmitButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(url)));
    }

    public void distCheckbox() {
        String url = driver.getCurrentUrl();
        specFilterDistCheckbox.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(url)));
    }

    public void specSelectDiv() {
        String url = driver.getCurrentUrl();
        specFilterOpen.click();
        specFilterQuality.click();
        specFilterTestEngineer.click();
        specFilterSubmitButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(url)));
    }

    public void specSelectReset() {
        String url = driver.getCurrentUrl();
        specFilterOpen.click();
        specFilterResetButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(url)));
    }

    public void selectReset() {
        String url = driver.getCurrentUrl();
        filterResetButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(url)));
    }

    public void qualSelect(String select) {
        String url = driver.getCurrentUrl();
        qualSelect.click();
        if (select.equals(PropsHandler.get("qualIntern"))) {
            internQualSelect.click();
        } else if (select.equals(PropsHandler.get("qualJun"))) {
            juniorQualSelect.click();
        } else if (select.equals(PropsHandler.get("qualMid"))) {
            middleQualSelect.click();
        }  else if (select.equals(PropsHandler.get("qualSen"))) {
            seniorQualSelect.click();
        } else if (select.equals(PropsHandler.get("qualLead"))) {
            leadQualSelect.click();
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(url)));
    }

    public List<String> getJobNames() {
        List<String> strJobNames = new ArrayList<>();
        for (WebElement element : jobsNames) {
            strJobNames.add(element.getText().toLowerCase());
        }
        return strJobNames;
    }

    public List<String> getDist() {
        List<String> strDist = new ArrayList<>();
        for (WebElement element : specFilterTypeWorking) {
                strDist.add(element.getText());
        }
        return strDist;
    }

    public void searchSalary(String input) {
        String url = driver.getCurrentUrl();
        salariesFilterInput.clear();
        salariesFilterInput.sendKeys(input);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(url)));
    }

    public void salarySortDesc() {
        String url = driver.getCurrentUrl();
        salariesSort.click();
        salariesSortDesc.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(url)));
    }

    public List<String> getQual() {
        List<String> qual = new ArrayList<>();
        for (WebElement element : qualAndSpec) {
            String str = element.getText();
            if (!str.endsWith(",")) {
                qual.add(str.substring(0, str.length() - 1));
            }
        }
        return qual;
    }

    public List<String> getSpec() {
        List<String> spec = new ArrayList<>();
        for (WebElement element : qualAndSpec) {
            String str = element.getText();
            if (str.endsWith(",")) {
                spec.add(str);
            }
        }
        return spec;
    }

    public List<Double> getSalaryValue() {
        List<Double> numDoubles = new ArrayList<>();
        for (WebElement element : salaries) {
            List<String> strs = List.of(element.getText().split(" "));
            List<String> numbers = new ArrayList<>();
            boolean curNum = false;
            for (String str : strs) {
                if (str.matches("\\d+")) {
                    if (curNum) {
                        String prev = numbers.get(numbers.size() - 1);
                        numbers.remove(numbers.size() - 1);
                        numbers.add(prev + str);
                    } else {
                        curNum = true;
                        numbers.add(str);
                    }
                } else {
                    curNum = false;
                }
                switch (str) {
                    case "$" -> numDoubles.add(Double.parseDouble(numbers.get(numbers.size() - 1)) *
                            Double.parseDouble(PropsHandler.get("dollar_rate")));
                    case "€" -> numDoubles.add(Double.parseDouble(numbers.get(numbers.size() - 1)) *
                            Double.parseDouble(PropsHandler.get("euro_rate")));
                    case "₽" -> numDoubles.add(Double.parseDouble(numbers.get(numbers.size() - 1)));
                }
            }
        }
        return numDoubles;
    }

    public boolean isSorted(List<Double> a) {
        for (int i = 0; i < a.size() - 1; i++) {
            if (a.get(i) < a.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean partlyContains(List<String> a, String s) {
        for (String str : a) {
            if (!str.contains(s)) {
                return true;
            }
        }
        return false;
    }

    public boolean partlyContainsDouble(List<Double> a, String s) {
        for (Double db : a) {
            if (db < Double.parseDouble(s)) {
                return true;
            }
        }
        return false;
    }


}
