package fcsilva.estrategia3;

import com.github.javafaker.Faker;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING) // ordem de execução dos testes
public class ContaTesteWeb {

    // criando a massa de testes durante o próprio teste

    private WebDriver driver;
    private Faker faker = new Faker();

    @Before
    public void login(){

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://seubarriga.wcaquino.me/");

        driver.findElement(By.id("email")).sendKeys("fr@fr");
        driver.findElement(By.id("senha")).sendKeys("654321");
        driver.findElement(By.tagName("button")).click();
    }

    @After
    public void fecharBrowser(){
        driver.quit();

    }

    @Test
    public void inserirConta() throws SQLException, ClassNotFoundException {
        String conta = faker.gameOfThrones().character() + " " + faker.gameOfThrones().dragon();
        driver.findElement(By.linkText("Contas")).click();
        driver.findElement(By.linkText("Adicionar")).click();
        driver.findElement(By.id("nome")).sendKeys(conta);
        driver.findElement(By.tagName("button")).click();
        String text = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
        Assert.assertEquals("Conta adicionada com sucesso!", text);
        new MassaDAOImpl().inserirMassa(GeradorMassas.CHAVE_CONTA_SB, conta);


    }

    @Test
    public void consultarConta() throws SQLException, ClassNotFoundException {
        String conta = new MassaDAOImpl().obterMassa(GeradorMassas.CHAVE_CONTA_SB);
        driver.findElement(By.linkText("Contas")).click();
        driver.findElement(By.linkText("Listar")).click();
        driver.findElement(By.xpath("//td[contains(text(), '"+conta+"')]/..//a")).click();
        String nomeConta = driver.findElement(By.id("nome")).getAttribute("value");
        Assert.assertEquals(conta, nomeConta);
        new MassaDAOImpl().inserirMassa(GeradorMassas.CHAVE_CONTA_SB, conta);

    }


    @Test
    public void editarConta() throws SQLException, ClassNotFoundException {
        String conta = new MassaDAOImpl().obterMassa(GeradorMassas.CHAVE_CONTA_SB);
        driver.findElement(By.linkText("Contas")).click();
        driver.findElement(By.linkText("Listar")).click();
        driver.findElement(By.xpath("//td[contains(text(), '"+conta+"')]/..//a")).click();
        driver.findElement(By.id("nome")).sendKeys(" Editada");
        driver.findElement(By.tagName("button")).click();
        String nomeConta = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
        Assert.assertEquals("Conta alterada com sucesso!", nomeConta);
    }

    @Test
    public void excluirConta() throws SQLException, ClassNotFoundException {
        String conta = new MassaDAOImpl().obterMassa(GeradorMassas.CHAVE_CONTA_SB);
        driver.findElement(By.linkText("Contas")).click();
        driver.findElement(By.linkText("Listar")).click();
        driver.findElement(By.xpath("//tr[1]/td[2]/a/span[@class='glyphicon glyphicon-remove-circle']")).click();
        String text = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
        Assert.assertEquals("Conta removida com sucesso!", text);

    }




}
