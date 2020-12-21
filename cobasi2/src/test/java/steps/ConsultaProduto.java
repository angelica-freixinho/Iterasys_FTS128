package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.hamcrest.CoreMatchers;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.Home;
import pages.Lista;
import pages.Produto;
import utils.Evidences;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class ConsultaProduto {
    // páginas
    Home home;
    Lista lista;
    Produto produto;

    // classes de Apoio
    Evidences evidences;

    //Atributos
    String id;
    String url;
    WebDriver driver;
    static String pastaPrint = "evidencias/" + new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(Calendar.getInstance().getTime()) + "/";


    @Before
    public void iniciar() {
        url = "https://www.cobasi.com.br";
        System.setProperty("webdriver.chrome.driver", "drivers/chrome/87/chromedriver.exe");
        ChromeOptions co = new ChromeOptions();
        co.addArguments("--disable-notifications");
        driver = new ChromeDriver(co);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20000, TimeUnit.MILLISECONDS);
        System.out.println("Passo 0 - Preparou o setup");
        evidences = new Evidences();
        home = new Home(driver);
        lista = new Lista(driver);
        produto = new Produto(driver);

    }

    @After
    public void finalizar(){
        driver.quit();
        System.out.println("Passo 6 - Fechou o Browser");
    }

    @Dado("^que acesso o site da Cobasi \"([^\"]*)\"$")
    public void queAcessoOSiteDaCobasi(String id) throws InterruptedException, IOException {
        this.id = id;
        driver.get(url);
        System.out.println("Passo 1 - Acesso a página da Cobasi");
        evidences.takesScreenshot(driver, id, pastaPrint, "Passo 1 - Acessou o site da Cobasi");
        Thread.sleep(2000);
    }

    @Quando("^procuro por \"([^\"]*)\" e clico na lupa$")
    public void procuro_por_e_clico_na_lupa(String produto) throws InterruptedException, IOException {
       home.buscarProdutoComLupa(produto);
        evidences.takesScreenshot(driver, id, pastaPrint, "Passo 2 - Procuro por produto e clico na lupa");
        System.out.println("Passo 2 - Procuro por produto e clico na lupa");
        Thread.sleep(3000);
    }

    @Entao("^exibe a lista de produtos relacionados a \"([^\"]*)\"$")
    public void exibe_a_lista_de_produtos_relacionados_a(String produto) throws InterruptedException, IOException {
        // Título da Aba
        assertEquals(produto + " - Cobasi", lista.lerTituloAba());
        //resultado para produto
        assertEquals("COBASI\n/\nVOCÊ BUSCOU POR \"" + produto.toUpperCase() + "\"", lista.lerResultadoParaProduto());
        System.out.println("Passo 3 - Exibe a lista de itens relacionados a produto");
        evidences.takesScreenshot(driver, id, pastaPrint, "Passo 3 - Exibe a lista de itens relacionados a produto");
        Thread.sleep(2000);
    }

    @Quando("^seleciono o \"([^\"]*)\" da lista$")
    public void seleciono_o_da_lista(String produtoDescricao) throws InterruptedException {
       // Clicar no Produto
        lista.clicarNoProdutoNaLista(produtoDescricao);
        Thread.sleep(10000);
    }

    @Entao("^verifico a \"([^\"]*)\" com o \"([^\"]*)\" e o \"([^\"]*)\"$")
    public void verifico_a_com_o_e_o(String marca, String precoNormal, String precoAssinante) throws InterruptedException, IOException {
        //validar Marca
        Thread.sleep(2000);
        System.out.println("Passo 4-5 - Exibe o produto selecionado da lista");
        evidences.takesScreenshot(driver, id, pastaPrint, "Passo 4-5 - Exibe o produto selecionado da lista");
        assertEquals(marca, produto.lerMarca());

        //validar preco Normal
        //assertEquals(precoNormal, produto.lerPrecoNormal());
        //assertTrue(precoNormal.contains(produto.lerPrecoNormal()));
        assertThat(produto.lerPrecoNormal(), CoreMatchers.containsString(precoNormal));

        //validar preco Assinante
        //assertEquals(precoAssinante, produto.lerPrecoAssinante());
        //assertTrue(precoAssinante.contains(produto.lerPrecoAssinante()));
        assertThat(produto.lerPrecoAssinante(), CoreMatchers.containsString(precoAssinante));
        Thread.sleep(2000);
    }

}


