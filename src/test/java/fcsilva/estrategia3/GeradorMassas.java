package fcsilva.estrategia3;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class GeradorMassas {

    private WebDriver driver;
    private Faker faker = new Faker();
    public static final String CHAVE_CONTA_SB = "CONTAS_SB";

    public void gerarContaSrBarriga() throws SQLException, ClassNotFoundException {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://seubarriga.wcaquino.me/");

        driver.findElement(By.id("email")).sendKeys("fr@fr");
        driver.findElement(By.id("senha")).sendKeys("654321");
        driver.findElement(By.tagName("button")).click();

        String registro = faker.gameOfThrones().character() + " " + faker.gameOfThrones().dragon();
        driver.findElement(By.linkText("Contas")).click();
        driver.findElement(By.linkText("Adicionar")).click();
        driver.findElement(By.id("nome")).sendKeys(registro);
        driver.findElement(By.tagName("button")).click();
        driver.quit();

        new MassaDAOImpl().inserirMassa(CHAVE_CONTA_SB, registro);

    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        GeradorMassas gerador = new GeradorMassas();
        for(int i = 0; i < 5; i++){
            gerador.gerarContaSrBarriga();
        }

//        String massa = new MassaDAOImpl().obterMassa(CHAVE_CONTA_SB);
//        System.out.println(massa);
    }

}
