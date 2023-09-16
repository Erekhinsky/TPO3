package utils;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.time.Duration;
import java.util.Date;
import java.util.StringTokenizer;

public class CookieController {

    public static void writeCookiesToFile(WebDriver webDriver) {
        File file = new File("browser.dat");
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(60));
            wait.until(ExpectedConditions.urlToBe("https://career.habr.com/salaries?qualification=Intern&spec_aliases%5B%5D=backend"));
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Cookie cookie : webDriver.manage().getCookies()) {
                writer.write((cookie.getName() + ";" + cookie.getValue() + ";" +
                        cookie.getDomain() + ";" + cookie.getPath() + ";" + cookie.getExpiry() +
                        ";" + cookie.isSecure()));
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка при записи куки - "+ e.getLocalizedMessage());
        }
    }

    public static Cookie readCookiesFromFile() {
        Cookie cookie = new Cookie("","");
        try {
            File file = new File("browser.dat");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer str = new StringTokenizer(line, ";");
                while (str.hasMoreTokens()) {
                    String name = str.nextToken();
                    String value = str.nextToken();
                    String domain = str.nextToken();
                    String path = str.nextToken();
                    Date expiry = null;
                    String date= str.nextToken();
                    if (!(date).equals("null")) {
                        expiry = new Date(System.currentTimeMillis()*2);
                    }
                    boolean isSecure = Boolean.parseBoolean(str.nextToken());
                    cookie = new Cookie(name, value, domain, path, expiry, isSecure);
                }
            }
        } catch (Exception ex) {
            System.out.println("Ошибка при чтении куки - "+ ex.getLocalizedMessage());
        }
        return cookie;
    }
}
