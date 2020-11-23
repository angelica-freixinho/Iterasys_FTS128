package site;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class lojaAmericanas {

    //
    String url;
    WebDriver driver;
    String pastaPrint = "evidencias/" + new SimpleDateFormat("yyyy-MM-dd HH-mm").format(Calendar.getInstance().getTime()) + "/";

    public void tirarPrint(String nomePrint) throws IOException {
        File foto = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); //grava na memória RAM
        FileUtils.copyFile(foto,new File(pastaPrint + nomePrint + ".png")); // grava no arquivo em disco
    }

    @Before
    public void inicar(){
        url = "https://www.americanas.com.br";
        System.setProperty("webdriver.chrome.driver","drivers/chrome/87/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();

    }

    @Test
    public void selecionarItem() throws InterruptedException, IOException {
        driver.get(url);
        tirarPrint("Americanas Passo 1 - Acessou a Home");
        driver.findElement(By.id("h_search-input")).sendKeys("marca texto pastel");
        driver.findElement(By.cssSelector("button#h_search-btn.src-btn")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("lgpd-accept")).click(); // aceita cookie
        tirarPrint("Americanas Passo 2 - Exibe os produtos relacionados a marca texto pastel");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("img.src__Image-xr9q25-0.jLfQuZ")).click();
        tirarPrint ("Americanas Passo 3 - Exibe o titulo, valor e  parcelamento do produto escolhido");
        Thread.sleep(3000);

        // Validar o nome do produto:
        String resultadoEsperado = "Marca Texto Pastel 6 Cores - NewPen";
        String resultadoAtual = driver.findElement(By.cssSelector("span.src__Text-sc-154pg0p-0.src__Title-sc-14j0fsd-0.gjpFEE")).getText();
        assertEquals(resultadoEsperado,resultadoAtual);

        // Validar o preço
        assertEquals("R$ 21,80", driver.findElement(By.cssSelector("div.src__BestPrice-lo2vta-5.bFIChl.priceSales")).getText());

        // validar o valor das parcelas
        //assertEquals("R$ 21,80",driver.findElement(By.cssSelector("p.src__Text-sc-8ap1bp-0.UpOQz")).getText());
        assertTrue(driver.findElement(By.cssSelector("p.src__Text-sc-8ap1bp-0.UpOQz")).getText().contains("2x sem juros"));

        // validar s segunda opção de parcelamento
        assertTrue(driver.findElement(By.cssSelector("div.brand-card__Text-sc-1rgcf0y-1.dOvHzA")).getText().contains("4x sem juros"));


    }


    @After
    public void finalizar(){
       driver.quit();
    }


}
