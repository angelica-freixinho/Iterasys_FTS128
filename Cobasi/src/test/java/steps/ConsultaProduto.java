package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ConsultaProduto {

    String url;
    WebDriver driver;
    String pastaPrint = "evidencias/" + new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(Calendar.getInstance().getTime()) + "/";

    public void tirarPrint(String nomePrint) throws IOException {
        File foto = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); //grava na memória RAM
        FileUtils.copyFile(foto, new File(pastaPrint + nomePrint + ".png")); // grava no arquivo em disco
    }

    @Before
    public void iniciar() {
        url = "https://www.cobasi.com.br";
        System.setProperty("webdriver.chrome.driver", "drivers/chrome/87/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20000, TimeUnit.MILLISECONDS);

    }

    @After
    public void finalizar(){
        driver.quit();
        System.out.println("Passo 6 - Fechou o Browser");
    }

    @Dado("^que acesso o site da Cobasi$")
    public void que_acesso_o_site_da_Cobasi() throws IOException, InterruptedException {
        driver.get(url);
        System.out.println("Passo 1 - Acessou o site da Cobasi");
        tirarPrint("Passo 1 - Acessou o site da Cobasi");
        Thread.sleep(2000);
    }

    @Quando("^procuro por \"([^\"]*)\" e clico na lupa$")
    public void procuro_por_e_clico_na_lupa(String produto) throws InterruptedException, IOException {
        driver.findElement(By.cssSelector("input.fulltext-search-box.ui-autocomplete-input.neemu-search-field")).sendKeys(produto);
        tirarPrint("Passo 2 - pesquiso e clico na lupa");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input.btn-buscar")).click();
        System.out.println("Passo 2 - pesquiso e clico na lupa");
        Thread.sleep(2000);
    }

    @Entao("^exibe a lista de produtos relacionados a \"([^\"]*)\"$")
    public void exibe_a_lista_de_produtos_relacionados_a(String produto) throws InterruptedException, IOException {
        driver.findElement(By.xpath("//strong[contains(text(),'" + produto + "')]")).getText();
        Thread.sleep(2000);
        tirarPrint("Passo 3 - Exibiu a lista de produtos relacionados com o produto");
        System.out.println("Passo 3 - Exibiu a lista de produtos relacionados com " + produto);
    }

    @Quando("^seleciono o \"([^\"]*)\" da lista$")
    public void seleciono_o_da_lista(String produtoDescricao) throws InterruptedException{
        driver.findElement(By.xpath("//a[contains(text(),'" + produtoDescricao + "')]")).click();
        Thread.sleep(2000);
        System.out.println("Passo 4 - Selecionou o produdo da lista");

    }

    @Entao("^verifico a \"([^\"]*)\" com o \"([^\"]*)\" e o \"([^\"]*)\"$")
    public void verifico_a_com_o_e_o(String marca, String precoNormal, String precoAssinante) throws InterruptedException, IOException {
        assertEquals(marca, driver.findElement(By.cssSelector("div.product__brand")).getText());
        Thread.sleep(2000);
        assertTrue(driver.findElement(By.cssSelector("div.js-price-product")).getText().contains(precoNormal));
        Thread.sleep(2000);
        assertTrue(driver.findElement(By.cssSelector("span.price__assinatura-price")).getText().contains(precoAssinante));
        tirarPrint("Passo 4-5 - Selecionou o produdo da lista");
        Thread.sleep(2000);
        //assertEquals(precoAssinante, driver.findElement(By.xpath("//span[contains(text(),'R$ 27,81')]")));
        System.out.println("Passo 5 - Exibe a página do produto");
    }

}


