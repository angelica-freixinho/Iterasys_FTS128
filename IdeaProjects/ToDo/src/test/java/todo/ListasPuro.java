// 1 - Pacote = Conjunto de Classes
package todo;

// 2 - Bibliotecas = Métodos e Funções Prontos

import com.sun.prism.shader.AlphaOne_Color_AlphaTest_Loader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

// 3 - Classe
public class ListasPuro {
    // 3.1 - Atributos = Características
    String url; // guardará o endereço do site alvo
    WebDriver driver; // objeto do Selenium WebDriver


    // 3.2 - Métodos ou Funcionalidades = O que ele sabe fazer

    @Before
    public void inicializar(){
        //Declarando o endereço do site alvo
        url = "https://todo.microsoft.com";

        // Informando o local
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        //Instanciar o objeto Selenium WebDriver Como navegador Chrome
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait( 3000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize(); // maximiza a janela do navegador

    }

    @Test
    public void criarListaComTresItens() throws InterruptedException {
        driver.get(url);
        // Pagina de login
        driver.findElement(By.id("mectrl_headerPicture")).click(); // clica no icone do Sign In / Log In
        Thread.sleep(3000);
        driver.findElement(By.id("i0116")).sendKeys("angelicafn@gmail.com"); // preenche/cola o e-mail
        driver.findElement(By.id("idSIButton9")).click(); // clica no botao Proximo
        driver.findElement(By.id("i0118")).sendKeys("Quali@274208"); // Preenche / cola a senha
        Thread.sleep(3000);
        driver.findElement(By.id("idSIButton9")).click(); // Clica no Botao enter
        driver.findElement(By.id("idSIButton9")).click(); // clica no botao Sim
        Thread.sleep(40000);

        // Pagina de Tasks - Tarefas
        driver.findElement(By.id("baseAddInput-addList")).click(); // clica no elemento "Nova Lista"
        Thread.sleep(3000);
        driver.findElement(By.id("baseAddInput-addList")).clear(); // apaga o texto no elemento
        //driver.findElement(By.id("baseAddInput-addList")).sendKeys("Musicas"); //cola a palavra "Musica"
        driver.findElement(By.id("baseAddInput-addList")).sendKeys(Keys.chord("Musicas")); // soletra a palavra "Musica"
        Thread.sleep(3000);
        // Todo: Implamentar o print da tela
        driver.findElement(By.id("baseAddInput-addList")).sendKeys(Keys.ENTER); // pressiona a tecla Enter
        Thread.sleep(3000);
        // Adiciona 3 musicas a lista
        driver.findElement(By.id("baseAddInput-addTask")).sendKeys("O quereres" + Keys.ENTER);
        driver.findElement(By.id("baseAddInput-addTask")).sendKeys("Terra" + Keys.ENTER);
        driver.findElement(By.id("baseAddInput-addTask")).sendKeys("Me Gusta" + Keys.ENTER);
        Thread.sleep(3000);

    }

    @Test
     public void alterarLista() throws InterruptedException {
        driver.get(url);
       // Pagina de login
        driver.findElement(By.id("mectrl_headerPicture")).click();// clica no icone do Sign In / Log In
        Thread.sleep(3000);
        driver.findElement(By.id("i0116")).sendKeys("angelicafn@gmail.com"); // preenche/cola o e-mail
        driver.findElement(By.id("idSIButton9")).click(); // clica no botao Proximo
        driver.findElement(By.id("i0118")).sendKeys("Quali@274208"); // Preenche / cola a senha
        Thread.sleep(3000);
        driver.findElement(By.id("idSIButton9")).click(); // Clica no Botao enter
        driver.findElement(By.id("idSIButton9")).click(); // clica no botao Sim
        Thread.sleep(40000);

        // Pagina de Tasks - Tarefas
        driver.findElement(By.xpath("//div[contains(@aria-label,\"Musicas\")]")).click(); // seleciona a lista no painel da esquerda
        //driver.findElement(By.cssSelector("i.ms-Icon--More")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("i.icon.fontIcon.ms-Icon.ms-Icon--More.iconSize-24")).click();//clica na reticencias
        Thread.sleep(3000);
         // escolhe a primeira opção, de Renomear:
        driver.findElement(By.cssSelector("ul.ms-ContextualMenu-list.is-open.list-62 li.ms-ContextualMenu-item.item-65:nth-child(1)")).click();
        Thread.sleep(3000);
        //driver.findElement(By.cssSelector("input.chromeless.editing.tasksToolbar-input")).clear(); // Limpar o nome antigo
        //Thread.sleep(22000);
        // Altera para o novo nome
        driver.findElement(By.cssSelector("input.chromeless.editing.tasksToolbar-input")).sendKeys("Minhas Musicas" + Keys.ENTER);
        Thread.sleep(3000);

     }

    @Test
    public void excluirLista() throws InterruptedException {
        driver.get(url);
       // Pagina de login
        driver.findElement(By.id("mectrl_headerPicture")).click(); // clica no icone do Sign In / Log In
        Thread.sleep(3000);
        driver.findElement(By.id("i0116")).sendKeys("angelicafn@gmail.com"); // preenche/cola o e-mail
        driver.findElement(By.id("idSIButton9")).click(); // clica no botao Proximo
        driver.findElement(By.id("i0118")).sendKeys("Quali@274208"); // Preenche / cola a senha
        Thread.sleep(3000);
        driver.findElement(By.id("idSIButton9")).click(); // Clica no Botao enter
        driver.findElement(By.id("idSIButton9")).click(); // clica no botao Sim
        Thread.sleep(40000);

        // Pagina de Tasks - Tarefas
        driver.findElement(By.xpath("//div[contains(@aria-label,\"Minhas\")]")).click(); // seleciona a lista no painel da esquerda
        //driver.findElement(By.cssSelector("li.listItem-container active")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("i.icon.fontIcon.ms-Icon.ms-Icon--More.iconSize-24")).click();//clica na reticencias
        Thread.sleep(3000);
        // escolhe a quinta opção, de Excluir lista:
        driver.findElement(By.cssSelector("i.icon.fontIcon.ms-Icon.ms-Icon--Delete.iconSize-24")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("button.button.red")).click(); //confirma a exclusão
        Thread.sleep(3000);
    }


        @After
    public void finalizar(){
    //    driver.quit();   // destruir o objeto Selenium webDriver
    }


}
