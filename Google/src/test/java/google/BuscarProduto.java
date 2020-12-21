package google;

import com.sun.javafx.image.BytePixelAccessor;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)// lê uma massa de teste

public class BuscarProduto {
    // 3.1 - Atributos = Características
    static String url; // guardará o endereço do site alvo
    WebDriver driver;
    String pastaPrint = "evidencias/" + new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(Calendar.getInstance().getTime()) + "/";

    // 3.2 métodos e funções
    // métodos ou funções de apoio (util / commons)

    public void tirarPrint(String nomePrint) throws IOException {
        File foto = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); //grava na memória RAM
        FileUtils.copyFile(foto, new File(pastaPrint + nomePrint + ".png")); // grava no arquivo em disco
    }

    //Função para ler a massa de teste
    // Campos da massa de teste

    private String id;
    private String buscaPalavra;
    private String browser;
    private String titulo;

    // Construtor
    public BuscarProduto(String id, String buscaPalavra, String browser, String titulo){
        this.id = id;
        this.buscaPalavra = buscaPalavra;
        this.browser = browser;
        this.titulo = titulo;
    }

    //Collection intermediaria entre construtor e a collection que vai fazer a leitura
    @Parameters
    public static Collection<String[]> LerArquivo() throws IOException {
        return LerCSV("db/Massa Pesquisa Google.csv");

    }

    //Collection que le um arquivo no formato CSV
    public static Collection<String[]> LerCSV(String nomeCSV) throws IOException {
        // Lê o arquivo no disco e disponibiliza na memória RAM
        BufferedReader arquivo = new BufferedReader(new FileReader(nomeCSV));
        String linha;  // cria uma variável linha
        List<String[]> dados = new ArrayList<>(); // Cria uma lista (tabela) para receber o resultado

        while ((linha = arquivo.readLine()) != null) {
            String[] campos = linha.split(";");
            dados.add(campos);
        }
        arquivo.close();
        return dados;
    }


    @BeforeClass
    public static void antesDeTudo(){
        //Declarando o endereço do site alvo
        url = "https://www.google.com";

        // Informando o local
        System.setProperty("webdriver.chrome.driver","drivers/chrome/87/chromedriver.exe");
        System.setProperty("webdriver.edge.driver","drivers/edge/msedgedriver.exe");
        System.setProperty("webdriver.gecko.driver","drivers/firefox/geckodriver.exe");
        System.setProperty("webdriver.ie.driver","drivers/ie/IEDriverServer.exe");

    }


    @Before
    public void inicializar(){
        switch (browser){
            case "Chrome":
                driver = new ChromeDriver();
                break;
            case "Edge":
                driver = new EdgeDriver();
                break;
            case "Firefox":
                driver = new FirefoxDriver();
                break;
            case "IE":
                driver = new InternetExplorerDriver();
                break;
        }


        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize(); // maximiza a janela do navegador

        }


    @Test
    public void consultarSite() throws InterruptedException, IOException {
        driver.get(url);
        Thread.sleep(3000);
        tirarPrint("Passo 1 - Acessou o Google");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input.gLFyf.gsfi")).sendKeys(buscaPalavra + Keys.ENTER); //Pesquisa pelo item
        Thread.sleep(3000);
        tirarPrint("Passo 2 - Exibe as pesquisas realizada ");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//cite[contains(.,buscaPalavra)]")).click(); // clica para entrar na pagina escodlhida por massa de teste
        Thread.sleep(3000);
        tirarPrint("Passo 3 - Exibe as pagina selecionada da pesquisa");
        Thread.sleep(8000);
        //validar a pagina encontrada na pesquisa do google
        assertTrue(driver.getTitle().contains(titulo));

    }

    @After
    public void finalizar(){
        // driver.quit(); // Destruir o objeto Selenium webDriver
        }

}
