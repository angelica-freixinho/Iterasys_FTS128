package stepsPO;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.Home;
import pages.Lista;
import pages.Produto;
import utils.Evidences;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class ConsultaProdutoPO {
    // Páginas
    Home home;
    Lista lista;
    Produto produto;

    // Classes de Apoio / Utils
    Evidences evidences; // referência a classe Evidences que nós criamos

    // Atributos e Objetos
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
        home = new Home(driver);
        lista = new Lista(driver);
        produto = new Produto(driver);

    }

    @After
    public void finalizar(){
        driver.quit();
        System.out.println("Passo 6 - Fechou o browser");
    }


    @Dado("^que acesso o site da Petz \"([^\"]*)\" PO$")
    public void queAcessoOSiteDaPetzPO(String id){
        driver.get(url);
    }

    @Quando("^procuro por \"([^\"]*)\" e pressiono Enter PO$")
    public void procuroPorEPressionoEnterPO(String produto){
        home.buscarProdutoComEnter(produto);

    }

    @Entao("^exibe a lista de produtos relacionados a \"([^\"]*)\" PO$")
    public void exibeAListaDeProdutosRelacionadosAPO(String produto){
        // Titulo da Aba
        assertEquals(produto + " - Produtos pet em promoção | Petz", lista.lerTituloAba());

        //resultado para Produto
        assertEquals("RESULTADOS PARA \"" + produto.toUpperCase() + "\"", lista.lerResultadoParaProduto());

        //mensagem de Erro
        //assertEquals("Os resultados desta busca são aproximados, pois não encontramos resultados para \""
         //       + produto + "\"", lista.lerMensagemDeErro());


    }

    @Quando("^seleciono o produto \"([^\"]*)\" da lista PO$")
    public void selecionoOProdutoDaListaPO(String produto) throws InterruptedException {
        // Clicar no Produto
        lista.clicarNoProdutoNaLista(produto);
        Thread.sleep(60000);
    }


    @Entao("^verifico a marca como \"([^\"]*)\" com preco normal de \"([^\"]*)\" e \"([^\"]*)\" para assinantes PO$")
    public void verificoAMarcaComoComPrecoNormalDeEParaAssinantesPO(String marca, String precoNormal, String precoAssinante) throws InterruptedException {
        Thread.sleep(10000);
        // Validar Marca
        assertEquals(marca, produto.lerMarca());

        // Validar Preco Normal
        assertEquals(precoNormal, produto.lerPrecoNormal());

        // validar Preco Assinante
        assertEquals(precoAssinante, produto.lerPrecoAssinante());

    }
}
