package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class ConsultaProduto {

    String url;
    WebDriver driver;

    @Before
    public void iniciar(){
        url = "https://www.petz.com.br";
        System.setProperty("webdriver.chrome.driver", "drivers/chrome/87/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20000, TimeUnit.MILLISECONDS);
        System.out.println("Passo 1 - Preparou o setup");
    }

    @After
    public void finalizar(){
        driver.quit();
        System.out.println("Passo 7 - Fechou o browser");
    }

    @Dado("^que acesso o site da Petz$")
    public void que_acesso_o_site_da_Petz() throws Throwable {
        driver.get(url);
        System.out.println("Passo 2 - Acessou o site da Petz");
    }

    @Quando("^procuro por \"([^\"]*)\" e pressiono Enter$")
    public void procuro_por_e_pressiono_Enter(String termo){
        driver.findElement(By.id("search")).sendKeys(termo);
        //todo: tirar print
        driver.findElement(By.id("search")).sendKeys(Keys.ENTER);
        System.out.println("Passo 3 - Procurou por " + termo);
    }


    @Entao("^exibe a lista de produtos relacionados a \"([^\"]*)\"$")
    public void exibe_a_lista_de_produtos_relacionados_a(String termo) throws Throwable {
        assertEquals(termo + " - Produtos pet em promo��o | Petz", driver.getTitle());
        assertEquals("RESULTADOS PARA \"" + termo.toUpperCase() + "\"",
                driver.findElement(By.cssSelector("h1.h2Categoria.nomeCategoria")).getText());
        System.out.println("Passo 4 - Exibiu a lista de produtos relacionados com " + termo);

    }

    @Quando("^seleciono o primeiro produto da lista$")
    public void seleciono_o_primeiro_produto_da_lista() throws Throwable {
       // driver.findElement(By.cssSelector("h3.nome_produto:nth-child(1)")).click();
        //assertEquals("Ra��o Royal Canin Maxi - C�es Adultos - 15kg",
             //   driver.findElement(By.xpath("//h3[contains(.,'Ra��o Royal Canin Maxi - C�es Adultos - 15kg')]")).getText());
       // Quando ja click pelo "nome"  n�o precisa fazer assert
        driver.findElement(By.xpath("//h3[contains(.,'Ra��o Royal Canin Maxi - C�es Adultos - 15kg')]")).click();
        //driver.findElement(By.cssSelector("li.liProduct:nth-child(1) > a:nth-child(2)")).click();
        //driver.findElement(By.cssSelector("img.product-img.img-responsive")).click();
        System.out.println("Passo 5 - Selecionou o primeiro produdo da lista");
    }


    @Entao("^verifico a marca como \"([^\"]*)\" com preco normal de \"([^\"]*)\" e \"([^\"]*)\" para assinantes$")
    public void verifico_a_marca_como_com_preco_normal_de_e_para_assinantes(String marca, String precoNormal, String precoAssinante) throws Throwable {
        assertEquals(marca, driver.findElement(By.cssSelector("span.blue")).getText());
        assertEquals(precoNormal, driver.findElement(By.cssSelector("div.price-current")).getText());
        assertEquals(precoAssinante, driver.findElement(By.cssSelector("span.price-subscriber")).getText());
        System.out.println("Passo 6 - Verificou a marca como " + marca + ", o preco normal como " + precoNormal +
                " para assinante como " + precoAssinante);

    }

    @Quando("^procuro por \"([^\"]*)\" e clico na Lupa$")
    public void procuro_por_e_clico_na_Lupa(String termo) throws Throwable {
        driver.findElement(By.id("search")).sendKeys(termo);
        //todo: tirar print
        driver.findElement(By.cssSelector("button.button-search")).click();
        System.out.println("Passo 3 - Procurou por " + termo);
    }

    @Entao("^exibe uma lista de produtos aproximados e a mensagem de que nao encontrou \"([^\"]*)\"$")
    public void exibe_uma_lista_de_produtos_aproximados_e_a_mensagem_de_que_nao_encontrou(String termo){
     assertEquals("Os resultados desta busca s�o aproximados, pois n�o encontramos resultados para \""
             + termo + "\"",driver.findElement(By.cssSelector("span.descricao-lucene:nth-child(2)")).getText());
    }



}
