package site;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Boticario {
    String url = "https://www.boticario.com.br";
    WebDriver driver;
    String pastaPrint = "evidencias/" + new SimpleDateFormat("yyyy-MM-dd HH-mm").format(Calendar.getInstance().getTime()) + "/";

    public void tirarPrint(String nomePrint) throws IOException {
        File foto = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); //grava na memória RAM
        FileUtils.copyFile(foto,new File(pastaPrint + nomePrint + ".png")); // grava no arquivo em disco
    }


    @Before
    public void iniciar(){
        ChromeOptions chOptions = new ChromeOptions(); // instanciar o objeto de configuração do chromeDriver
        chOptions.addArguments("--disable-notifications");

        System.setProperty("webdriver.chrome.driver","drivers/chrome/87/chromedriver.exe");
        driver = new ChromeDriver(chOptions); // <-- Instanciar o Selenium como um controlador do Chrome
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
    }


    @Test
    public void consultarItem() throws InterruptedException, IOException {
        driver.get(url);
        tirarPrint("Boticario Passo 1 - Acessou a Home");
        Thread.sleep(3000);
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        Thread.sleep(3000);
        //driver.findElement(By.id("onesignal-slidedown-cancel-button")).click();
        //driver.findElement(By.cssSelector("input#autocomplete-input.form-control.typeahead.tt-input")).sendKeys("Coffee Woman Seduction Desodorante Colonia 100ml");
        driver.findElement(By.cssSelector("input#autocomplete-input.form-control.typeahead.tt-input")).sendKeys(Keys.chord("Coffee Woman Seduction Desodorante Colonia 100ml")); // soletra
        driver.findElement(By.cssSelector("img.icon-search")).click();
        tirarPrint("Boticario Passo 2 - Exibe os perfumes relacionados a coffee");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("a.showcase-item-image:nth-child(1)")).click();
        //driver.findElement(By.cssSelector("span.showcase-item-brand")).click();
        tirarPrint ("Boticario Passo 3 - Exibe o titulo, valor e  parcelamento do perfume escolhido");
        Thread.sleep(3000);

        // Validar o nome do curso
        String resultadoEsperado = new String("Coffee Woman Seduction Desodorante Colônia 100ml".getBytes(), "UTF-8");
        String resultadoAtual = driver.findElement(By.cssSelector("h1.nproduct-title")).getText();
        assertEquals(resultadoEsperado,resultadoAtual);

        // validar o preço
        assertEquals("R$ 139,90",driver.findElement(By.cssSelector("div.nproduct-price-value")).getText());

        // Validar o valor das parcelas
        assertEquals("6x de R$ 23,32",driver.findElement(By.cssSelector("div.nproduct-price-installments")).getText());


    }

    @After
    public void finalizar(){
          driver.quit();
    }

}


