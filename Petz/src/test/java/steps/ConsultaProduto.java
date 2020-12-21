package steps;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.Evidences; // precisa importar porque está em outro pacote

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class ConsultaProduto {
    Evidences evidences; // referência a classe Evidences que nós criamos
    String id; // id compartilhado com todos os blocos de steps
    String url;
    WebDriver driver;
    static String pastaPrint = "evidencias/" + new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(Calendar.getInstance().getTime()) + "/";


    @Before
    public void iniciar(){
        url = "https://www.petz.com.br";
        System.setProperty("webdriver.chrome.driver", "drivers/chrome/87/chromedriver.exe");
        ChromeOptions co = new ChromeOptions();
        co.addArguments("--disable-notifications");
        driver = new ChromeDriver(co);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20000, TimeUnit.MILLISECONDS);
        System.out.println("Passo 0 - Preparou o setup");
        evidences = new Evidences();
    }

    @After
    public void finalizar(){
        driver.quit();
        System.out.println("Passo 6 - Fechou o browser");
    }

    @Dado("^que acesso o site da Petz \"([^\"]*)\"$")
    public void queAcessoOSiteDaPetz(String id) throws IOException {
        driver.get(url);
        this.id = id;
        evidences.takesScreenshot(driver, id, pastaPrint, "consultarProduto - Passo 1 - Acessou o site da Petz");
        System.out.println("Passo 1 - Acessou o site da Petz");
    }

    @Quando("^procuro por \"([^\"]*)\" e pressiono Enter$")
    public void procuro_por_e_pressiono_Enter(String termo) throws IOException {
        driver.findElement(By.id("search")).sendKeys(termo);
        //todo: tirar print
        evidences.takesScreenshot(driver, id, pastaPrint, "consultarProduto - Passo 2 - Procurou por " + termo);
        driver.findElement(By.id("search")).sendKeys(Keys.ENTER);
        System.out.println("Passo 2 - Procurou por " + termo);
    }


    @Entao("^exibe a lista de produtos relacionados a \"([^\"]*)\"$")
    public void exibe_a_lista_de_produtos_relacionados_a(String termo) throws IOException {
        assertEquals(termo + " - Produtos pet em promoção | Petz", driver.getTitle());
        assertEquals("RESULTADOS PARA \"" + termo.toUpperCase() + "\"",
                driver.findElement(By.cssSelector("h1.h2Categoria.nomeCategoria")).getText());
        evidences.takesScreenshot(driver, id, pastaPrint, "consultarProduto - Passo 3 - Exibiu a lista de produtos relacionados com " + termo);
        System.out.println("Passo 3 - Exibiu a lista de produtos relacionados com " + termo);

    }

    @Quando("^seleciono o primeiro produto da lista$")
    public void seleciono_o_primeiro_produto_da_lista() throws IOException {
       // driver.findElement(By.cssSelector("h3.nome_produto:nth-child(1)")).click();
        //assertEquals("Ração Royal Canin Maxi - Cães Adultos - 15kg",
        driver.findElement(By.xpath("//h3[contains(.,'Ração Royal Canin Maxi - Cães Adultos - 15kg')]")).click();
       // Quando ja click pelo "nome"  não precisa fazer assert

        //driver.findElement(By.cssSelector("li.liProduct:nth-child(1) > a:nth-child(2)")).click();
        //driver.findElement(By.cssSelector("img.product-img.img-responsive")).click();
        evidences.takesScreenshot(driver, id, pastaPrint, "consultarProduto - Passo 4 - Selecionou o primeiro produdo da lista");
        System.out.println("Passo 4 - Selecionou o primeiro produdo da lista");
    }


    @Entao("^verifico a marca como \"([^\"]*)\" com preco normal de \"([^\"]*)\" e \"([^\"]*)\" para assinantes$")
    public void verifico_a_marca_como_com_preco_normal_de_e_para_assinantes(String marca, String precoNormal, String precoAssinante) throws IOException {
        assertEquals(marca, driver.findElement(By.cssSelector("span.blue")).getText());
        assertEquals(precoNormal, driver.findElement(By.cssSelector("div.price-current")).getText());
        assertEquals(precoAssinante, driver.findElement(By.cssSelector("span.price-subscriber")).getText());
        evidences.takesScreenshot(driver, id, pastaPrint, "consultarProduto - Passo 5 - Verificou a marca como " + marca + ", o preco normal como " + precoNormal +
                " para assinante como " + precoAssinante);
        System.out.println("Passo 5 - Verificou a marca como " + marca + ", o preco normal como " + precoNormal +
                " para assinante como " + precoAssinante);

    }

    @Quando("^procuro por \"([^\"]*)\" e clico na Lupa$")
    public void procuro_por_e_clico_na_Lupa(String termo) throws IOException {
        driver.findElement(By.id("search")).sendKeys(termo);
        //todo: tirar print
        driver.findElement(By.cssSelector("button.button-search")).click();
        evidences.takesScreenshot(driver, id, pastaPrint, "consultarProduto - Passo 5b - Procurou por " + termo);
        System.out.println("Passo 5b - Procurou por " + termo);
    }

    @Entao("^exibe uma lista de produtos aproximados e a mensagem de que nao encontrou \"([^\"]*)\"$")
    public void exibe_uma_lista_de_produtos_aproximados_e_a_mensagem_de_que_nao_encontrou(String termo) throws IOException {
     assertEquals("Os resultados desta busca são aproximados, pois não encontramos resultados para \""
             + termo + "\"",driver.findElement(By.cssSelector("span.descricao-lucene:nth-child(2)")).getText());
        evidences.takesScreenshot(driver, id, pastaPrint, "consultarProduto - Passo 5c - Acessou o site da Petz");
    }

    @Entao("^exibe uma caixa de alerta dizendo que precisa preencher pelo menos tres letras no termo$")
    public void exibeUmaCaixaDeAlertaDizendoQuePrecisaPreencherPeloMenosTresLetrasNoTermo() throws IOException {
        assertEquals("Digite pelo menos 3 caracteres", driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        evidences.takesScreenshot(driver, id, pastaPrint, "consultarProduto - Passo 5d - Acessou o site da Petz");
    }


    @Entao("^verifico a \"([^\"]*)\" com o \"([^\"]*)\" e \"([^\"]*)\"$")
    public void verificoAComOE(String marca, String precoNormal, String precoAssinante) throws IOException {
        // Validar a marca
        assertEquals(marca, driver.findElement(By.cssSelector("span.blue")).getText());

        // Validar o preço normal
        assertEquals(precoNormal, driver.findElement(By.cssSelector("div.price-current")).getText());

        // Validar o preço de assinante
        assertEquals(precoAssinante, driver.findElement(By.cssSelector("span.price-subscriber")).getText());
        evidences.takesScreenshot(driver, id, pastaPrint, "consultarProduto - Passo 5e - Verificou a marca como " + marca + ", o preco normal como " + precoNormal +
                " e o preco de assinante como " + precoAssinante);

        System.out.println("Passo 5e - Verificou a marca como " + marca + ", o preco normal como " + precoNormal +
                " e o preco de assinante como " + precoAssinante);

    }

    @Quando("^seleciono o \"([^\"]*)\" da lista$")
    public void selecionoODaLista(String produtoDescricao) throws IOException {
        driver.findElement(By.xpath("//h3[contains(.,'" + produtoDescricao + "')]")).click();
        evidences.takesScreenshot(driver, id, pastaPrint, "consultarProduto - Passo 5f - Selecionou o primeiro produdo da lista");
        System.out.println("Passo 5f - Selecionou o primeiro produdo da lista");
    }

   // @Dado("^que acesso o site da Petz \"([^\"]*)\"$")
   // public void queAcessoOSiteDaPetz(String arg0) {

   // }
}
