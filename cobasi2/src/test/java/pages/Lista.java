package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Lista extends PageObject {
    //Resultados para
    //@FindBy(xpath = "//strong[contains(text(),'\" + produto + \"')]")
    @FindBy(css = "ul.neemu-breadcrumb-container")
    private WebElement resultadosParaProduto;

    public Lista(WebDriver driver) {
        super(driver);
    }

    // Ler o que está na linha de resultado
    public String lerResultadoParaProduto(){

        return resultadosParaProduto.getText();
    }

    //Ler o titulo da aba ativa
    public String lerTituloAba(){

        return driver.getTitle();
    }

    //Clicar no produto escolhido
    public void clicarNoProdutoNaLista(String produtoDescricao){
        WebElement nomeDoProduto = driver.findElement(By.xpath("//a[contains(text(), '" + produtoDescricao + "')]"));
        nomeDoProduto.click();
    }

}
