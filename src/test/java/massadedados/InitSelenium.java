package massadedados;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class InitSelenium {
    public static void main(String[] args) {
        // se tiver no path (variáveis de ambiente não precisa dessa linha de código
        //System.setProperty("webdriver.chrome.driver", "/Users/francilenesilva/documents/drivers/chromedriver");
        WebDriver driver = new FirefoxDriver();

        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://seubarriga.wcaquino.me/");

        driver.findElement(By.id("email")).sendKeys("fr@fr");
        driver.findElement(By.id("senha")).sendKeys("654321");
        driver.findElement(By.tagName("button")).click();

        driver.quit();

    }
}

// username e password postgres: admin