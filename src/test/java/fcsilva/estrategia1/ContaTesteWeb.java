package fcsilva.estrategia1;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) // ordem de execução dos testes
public class ContaTesteWeb {

    private WebDriver driver;

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
    public void caseTest_1_inserirConta(){
        driver.findElement(By.linkText("Contas")).click();
        driver.findElement(By.linkText("Adicionar")).click();
        driver.findElement(By.id("nome")).sendKeys("Conta estratégia #1");
        driver.findElement(By.tagName("button")).click();
        String text = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
        Assert.assertEquals("Conta adicionada com sucesso!", text);

    }

    @Test
    public void caseTest_2_consultarConta(){
        driver.findElement(By.linkText("Contas")).click();
        driver.findElement(By.linkText("Listar")).click();
        driver.findElement(By.xpath("//tr[1]/td[2]/a/span[@class='glyphicon glyphicon-edit']")).click();
        String nomeConta = driver.findElement(By.id("nome")).getAttribute("value");
        Assert.assertEquals("Conta estratégia #1", nomeConta);

    }


    @Test
    public void caseTest_3_editarConta(){
        driver.findElement(By.linkText("Contas")).click();
        driver.findElement(By.linkText("Listar")).click();
        driver.findElement(By.xpath("//tr[1]/td[2]/a/span[@class='glyphicon glyphicon-edit']")).click();
        driver.findElement(By.id("nome")).sendKeys("Editada");
        driver.findElement(By.tagName("button")).click();
        String nomeConta = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
        Assert.assertEquals("Conta alterada com sucesso!", nomeConta);
    }

    @Test
    public void caseTest_4_excluirConta(){
        driver.findElement(By.linkText("Contas")).click();
        driver.findElement(By.linkText("Listar")).click();
        driver.findElement(By.xpath("//tr[1]/td[2]/a/span[@class='glyphicon glyphicon-remove-circle']")).click();
        String text = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
        Assert.assertEquals("Conta removida com sucesso!", text);

    }



}
