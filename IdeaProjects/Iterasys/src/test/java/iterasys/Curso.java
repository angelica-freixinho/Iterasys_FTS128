// 1 - pacote
package iterasys;

// 2 - bibliotecas

import org.apache.commons.io.FileUtils;
import org.junit.*;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// 3 - classe
@RunWith(Parameterized.class) // Esta classe é parametrizada = lê uma massa de teste
public class Curso {
    // 3.1 - Atributos = Características
    static String url; // guardará o endereço do site alvo
    WebDriver driver;
    String pastaPrint = "evidencias/" + new SimpleDateFormat("yyyy-MM-dd HH-mm").format(Calendar.getInstance().getTime()) + "/";

    // 3.2 métodos e funções
    // métodos ou funções de apoio (util / commons)

    public void tirarPrint(String nomePrint) throws IOException {
        File foto = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); //grava na memória RAM
        FileUtils.copyFile(foto,new File(pastaPrint + nomePrint + ".png")); // grava no arquivo em disco
    }

    // função para ler uma massa de teste

    // 1 - Atributos / Campos da massa de teste

    private String id;
    private String curso;
    private String valor;
    private String subtotal;
    private String parcelamento;
    private String browser;
    // 2 - Construtor (de-para entre os campos na massa e os atributos)
    public Curso(String id, String curso, String valor, String subtotal, String parcelamento, String browser) {
        this.id = id;
        this.curso = curso;
        this.valor = valor;
        this.subtotal = subtotal;
        this.parcelamento = parcelamento;
        this.browser = browser;
    }

    // 3 - Collection Intermediária entre o Constructor e a Collection que vai fazer a leitura
    // Ela serve para apontar a pasta e o nome do arquivo a ser lido
    @Parameterized.Parameters
    public static Collection<String[]> LerArquivo() throws IOException {  //Tabela/coleção de textos
        return LerCSV( "db/FTS128 Massa Iterasys.csv");

    }

    // 4 - Collection que lê um arquivo no formato CSV
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
        url = "https://www.iterasys.com.br";

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
                driver = new ChromeDriver(); // Instanciar o Selenium como um controlador do chrome
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

        //Instanciar o objeto Selenium WebDriver Como navegador Chrome

        driver.manage().timeouts().implicitlyWait( 30000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize(); // maximiza a janela do navegador
    }

    @Test
    public void consultarCurso() throws InterruptedException, IOException {
        driver.get(url);
        tirarPrint("Passo 1 - Acessou a Home");
        driver.findElement(By.cssSelector("a.cc-btn.cc-dismiss")).click();
        driver.findElement(By.id("searchtext")).sendKeys(curso + Keys.ENTER);
        Thread.sleep(10000);
        tirarPrint("Passo 2 - Exibe os cursos relacionado a TestLink");
        Thread.sleep(15000);
        driver.findElement(By.cssSelector("span.comprar")).click();

        // Validar nome do curso
        String resultadoEsperado = curso;
        String resultadoAtual = driver.findElement(By.cssSelector("span.item-title")).getText();
        assertEquals(resultadoEsperado,resultadoAtual);

        tirarPrint("Passo 3 - Exibe o titulo, valor e parcelamento do curso");

        // Validar o preço
        assertEquals(valor,driver.findElement(By.cssSelector("span.new-price")).getText());

        // Validar o preço da direita
        assertEquals(subtotal, driver.findElement(By.cssSelector("div.subtotal")).getText());
        // Validar o valor das parcelas
        assertTrue( driver.findElement(By.cssSelector("div.ou-parcele")).getText().contains(parcelamento));

    }



    //@Test
    public void consultarMantis() throws IOException {
        driver.get(url);
        //Thread.sleep(3000);
        tirarPrint("Passo 4 - Acessou a Home");
        driver.findElement(By.id("searchtext")).sendKeys("Mantis");
        driver.findElement(By.id("btn_form_search")).click();
        tirarPrint("Passo 5 - Exibe os cursos relacionados a Mantis");
        driver.findElement(By.cssSelector("i.fa.fa.fa-chevron-right")).click();
        tirarPrint ("Passo 6 - Exibe o titulo, valor e  parcelamento do curso");

        // Validar o nome do curso
        String resultadoEsperado = "Mantis";
        String resultadoAtual = driver.findElement(By.cssSelector("span.item-title")).getText();
        assertEquals(resultadoEsperado,resultadoAtual);

        // validar o preço
        assertEquals("R$ 49,99",driver.findElement(By.cssSelector("span.new-price")).getText());

        // Validar o preço da direita
        assertEquals("SUBTOTAL R$ 49,99",driver.findElement(By.cssSelector("div.subtotal")).getText());
        // Validar o valor das parcelas
        assertTrue(driver.findElement(By.cssSelector("div.ou-parcele")).getText().contains("ou em 12 x de R$ 5,02"));


    }

    @Test
    public void fazerLogin() throws InterruptedException, IOException {
        driver.get(url);
        // Pagina de login
        driver.findElement(By.cssSelector("li.active.login_header")).click(); // clica no icone do Login
        Thread.sleep(3000);
        driver.findElement(By.id("email")).sendKeys("angelicafn@gmail.com"); // preenche/cola o e-mail
        Thread.sleep(3000);
        driver.findElement(By.id("senha")).sendKeys("Quali@274208"); // Preenche / cola a senha
        tirarPrint("Passo 7 - Acessou tela de login preenchendo email e senha");
        Thread.sleep(3000);
        driver.findElement(By.id("btn_login")).click(); // Clica no Botao enter
        tirarPrint("Passo 8 - Acessou o site");

        // Validar se a imagem esta presente ou não em uma seção.
        boolean imagePresent = driver.findElement(By.cssSelector("img.img-circle")).isDisplayed();
        assertTrue(imagePresent);


    }


        @After
    public void finalizar(){
       // driver.quit(); // Destruir o objeto Selenium webDriver

    }

}

