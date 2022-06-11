package fcsilva.estrategia2;

import com.github.javafaker.Faker;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
    public void inserirConta(){
        driver.findElement(By.linkText("Contas")).click();
        driver.findElement(By.linkText("Adicionar")).click();
        driver.findElement(By.id("nome")).sendKeys(faker.harryPotter().character());
        driver.findElement(By.tagName("button")).click();
        String text = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
        Assert.assertEquals("Conta adicionada com sucesso!", text);

    }

    @Test
    public void consultarConta(){
        String conta = inserirContaTest();

        driver.findElement(By.linkText("Contas")).click();
        driver.findElement(By.linkText("Listar")).click();
        driver.findElement(By.xpath("//td[contains(text(), '"+conta+"')]/..//a")).click();
        String nomeConta = driver.findElement(By.id("nome")).getAttribute("value");
        Assert.assertEquals(conta, nomeConta);

    }


    @Test
    public void editarConta(){
        String conta = inserirContaTest();
        driver.findElement(By.linkText("Contas")).click();
        driver.findElement(By.linkText("Listar")).click();
        driver.findElement(By.xpath("//td[contains(text(), '"+conta+"')]/..//a")).click();
        driver.findElement(By.id("nome")).sendKeys(" Editada");
        driver.findElement(By.tagName("button")).click();
        String nomeConta = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
        Assert.assertEquals("Conta alterada com sucesso!", nomeConta);
    }

    @Test
    public void excluirConta(){
        String conta = inserirContaTest();
        driver.findElement(By.linkText("Contas")).click();
        driver.findElement(By.linkText("Listar")).click();
        driver.findElement(By.xpath("//tr[1]/td[2]/a/span[@class='glyphicon glyphicon-remove-circle']")).click();
        String text = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
        Assert.assertEquals("Conta removida com sucesso!", text);

    }

    private String inserirContaTest(){
        String registro = faker.harryPotter().character();

        driver.findElement(By.linkText("Contas")).click();
        driver.findElement(By.linkText("Adicionar")).click();
        driver.findElement(By.id("nome")).sendKeys(registro);
        driver.findElement(By.tagName("button")).click();

        return registro;

    }



}
