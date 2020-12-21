package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Home extends PageObject {
    // Atributos / Elementos da Página
    @FindBy(id = "search")
    private WebElement busca;

    @FindBy(css = "button.button-search")
    private WebElement lupa;


    // Construtor Obrigatório
    public Home(WebDriver driver) {   //construtor
        super(driver);
    }

    public void buscarProdutoComEnter(String produto){
        busca.clear();
        busca.sendKeys(produto + Keys.ENTER);  // Escrever o termo e dar enter
    }

    public void buscarProdutoComLupa(String produto){  // não retorna nada = void
        busca.sendKeys(produto);
        lupa.click();
    }


}
