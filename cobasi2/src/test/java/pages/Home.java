package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Home extends PageObject {
    //Elementos da página
    @FindBy(css = "input.fulltext-search-box.ui-autocomplete-input.neemu-search-field")
    private WebElement busca;

    @FindBy(css = "input.btn-buscar")
    private WebElement lupa;

    //construtor
    public Home(WebDriver driver) { super(driver);}

    public void buscarProdutoComLupa(String produto){
        busca.clear();
        busca.sendKeys(produto);
        lupa.click();
    }

}
