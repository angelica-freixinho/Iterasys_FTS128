package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Produto extends PageObject {

    @FindBy(css = "div.product__brand")
    private WebElement marcaProduto;

    @FindBy(css = "div.js-price-product")
    private WebElement precoNormalProduto;

    @FindBy(css = "span.price__assinatura-price")
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
