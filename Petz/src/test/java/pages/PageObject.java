package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageObject {
    protected WebDriver driver; // Selenium interno

    public PageObject(WebDriver driver){  //construtor: repete o nome da classe mas não é uma classe, é uma estrutura
        this.driver = driver; // Selenium da classe de dentro está recebendo o Selenium de fora.
        PageFactory.initElements(driver, this);
    }

}
