package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Produto extends PageObject {

    @FindBy(css = "span.blue")
    private WebElement marcaProduto;

    @FindBy(css = "div.price-current")
    private WebElement precoNormalProduto;

    @FindBy(css = "span.price-subscriber")
    private WebElement precoAssinanteProduto;


    public Produto(WebDriver driver) {
        super(driver);
    }

    public String lerMarca(){
        return marcaProduto.getText();
    }

    public String lerPrecoNormal(){
        return precoNormalProduto.getText();
    }

    public String lerPrecoAssinante(){
        return precoAssinanteProduto.getText();
    }

}
