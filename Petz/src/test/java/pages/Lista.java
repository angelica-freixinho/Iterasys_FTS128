package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Lista extends PageObject {

    // resultados para
    @FindBy(css = "h1.h2Categoria.nomeCategoria")
    private WebElement resultadosParaProduto;

    // Mensagem de erro
    @FindBy(css = "span.descricao-lucene:nth-child(2)")
    private WebElement mensagemDeErro;

    public Lista(WebDriver driver) {
        super(driver);
    }
    // Ler o que está na linha de resultado
    public String lerResultadoParaProduto(){
        return resultadosParaProduto.getText();
    }

    // Ler a mensagem de erro
    public String lerMensagemDeErro(){
        return mensagemDeErro.getText();
    }

    //Ler o titulo da aba ativa
    public String lerTituloAba(){
        return driver.getTitle();
    }

    //Clicar no produto escolhido
    public void clicarNoProdutoNaLista(String produtoDescricao){
        WebElement nomeDoProduto = driver.findElement(By.xpath("//h3[contains(.,'" + produtoDescricao + "')]"));
        nomeDoProduto.click();
    }

}
