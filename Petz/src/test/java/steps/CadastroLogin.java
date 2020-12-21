package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class CadastroLogin {
    /*
    String url;
    WebDriver driver;
    String pastaPrint = "evidencias/" + new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(Calendar.getInstance().getTime()) + "/";

    public void tirarPrint(String nomePrint) throws IOException {
        File foto = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); //grava na memória RAM
        FileUtils.copyFile(foto,new File(pastaPrint + nomePrint + ".png")); // grava no arquivo em disco
    }

    @Before
    public void iniciar2() {
        url = "https://www.petz.com.br";
        System.setProperty("webdriver.chrome.driver", "drivers/chrome/87/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20000, TimeUnit.MILLISECONDS);
        System.out.println("Passo 1 - Preparou o setup");
    }

    @After
    public void finalizar() {
        //driver.quit();
        System.out.println("Passo Final - Fechou o browser");
    }

    @Dado("^que acesso a pagina da Petz$")
    public void que_acesso_o_site_da_Petz() throws IOException {
        driver.get(url);
        System.out.println("Passo 2 - Acessou o site da Petz");
    }

    @Quando("^passo o mouse na aba Entre e seleciono Criar \"([^\"]*)\"$")
    public void passo_o_mouse_na_aba_Entre_e_seleciono_Criar(String termo1) throws InterruptedException, IOException {
        driver.findElement(By.cssSelector("button.greetings")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Criar conta')]")).click();
        System.out.println("Passo 3 - No site da Petz presiono criar" + termo1);
    }

    @Entao("^exibe a pagina de cadastro relacionado a \"([^\"]*)\"$")
    public void exibe_a_pagina_de_cadastro_relacionado_a(String termo2) throws InterruptedException, IOException {
        assertEquals("Cadastre-se na Petz e garanta muitos benefícios | Petz", driver.getTitle());
        System.out.println("Passo 4 - Acessou a página de cadastro com dados da Minha " + termo2);
    }

    @Quando("^Prencho os campos com \"([^\"]*)\" e \"([^\"]*)\" e \"([^\"]*)\" e \"([^\"]*)\" e \"([^\"]*)\" e \"([^\"]*)\" e \"([^\"]*)\" e \"([^\"]*)\" e \"([^\"]*)\" e pressiono Salvar$")
    public void prencho_os_campos_com_e_e_e_e_e_e_e_e_e_pressiono_Salvar(String NomeCompleto, String Email, String DDD, String Celular, String Sexo, String Data_nasc, String CPF, String Senha, String Confsenha) throws Throwable {
        driver.findElement(By.cssSelector("input#Nome")).sendKeys(NomeCompleto);
        driver.findElement(By.cssSelector("input#Email")).sendKeys(Email);
        driver.findElement(By.cssSelector("input.form-control.ddd:nth-child(1)")).sendKeys(DDD);
        driver.findElement(By.cssSelector("input.form-control.celular")).sendKeys(Celular);
        driver.findElement(By.cssSelector("select#Sexo.form-control")).sendKeys(Sexo);
        driver.findElement(By.cssSelector("input#dataNascimento.form-control.aniversario")).sendKeys(Data_nasc);
        driver.findElement(By.id("CPF-CNPJ")).sendKeys(CPF);
        driver.findElement(By.cssSelector("input#Senha.form-control")).sendKeys(Senha);
        driver.findElement(By.cssSelector("input#confirmasenha.form-control")).sendKeys(Confsenha);
        System.out.println("Passo 5 - Mostra a pagina de cadastro com as informacoes preenchidas");
        tirarPrint("Passo 1-5 - Mostra a pagina de cadastro com as informacoes preenchidas");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input#criarContaButton.btn.btn-success.btn-form.btn-redondo-verde")).click();
    }

    @Entao("^exibe a tela de Aviso com o texto \"([^\"]*)\"$")
    public void exibe_a_tela_de_Aviso_com_o_texto(String termo3) throws InterruptedException, IOException {
        System.out.println("Passo 6 - Exibe a tela de aviso: " + termo3);
        assertEquals(termo3, driver.findElement(By.xpath("//p[contains(text(),'Dados salvos com sucesso')]")).getText());
        tirarPrint("Passo 6 - Exibe a tela Dados salvos com sucesso");
    }

    @Entao("^pressiono Entendi para concluir$")
    public void pressiono_Entendi_para_concluir() throws IOException, InterruptedException {
        driver.findElement(By.xpath("//a[contains(text(),'Entendi')]")).click();
        System.out.println("Passo 7 - Usuario Cadastrado!!");
    }


    @Quando("^passo o mouse na aba Entre e seleciono \"([^\"]*)\"$")
    public void passo_o_mouse_na_aba_Entre_e_seleciono(String palavra) {
        driver.findElement(By.cssSelector("button.greetings")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Entrar')]")).click();
        System.out.println("Passo 8 - Login no site ao clicar em " + palavra);
    }

    @Entao("^exibe a pagina de login com \"([^\"]*)\"$")
    public void exibe_a_pagina_de_login_com(String frase) {
        assertEquals(frase, driver.findElement(By.xpath("//body/div[8]/div[2]/div[1]/form[1]/h2[1]")).getText());
        System.out.println("Passo 9 - Exibe a pagina de  Login com  " + frase);
    }

    @Quando("^preencho o email \"([^\"]*)\" e digito a senha \"([^\"]*)\" e clico em Entrar$")
    public void preencho_o_email_e_digito_a_senha_e_clico_em_Entrar(String Email, String Senha) throws IOException, InterruptedException {
        //driver.findElement(By.xpath("//body/div[8]/div[2]/div[1]/form[1]/div[1]/input[1]")).sendKeys(Email);
        driver.findElement(By.name("login")).sendKeys(Email);
        driver.findElement(By.name("senha")).sendKeys(Senha);
        //driver.findElement(By.xpath("/html[1]/body[1]/div[8]/div[2]/div[1]/form[1]/div[2]/input[1]")).sendKeys(Senha);
        System.out.println("Passo 10 - Exibe a pagina de Login preenchida com dados do e-mail: " + Email);
        assertEquals("Email ou CPF", driver.findElement(By.xpath("//label[@id='label-login-form']")).getText());
        tirarPrint("Passo 7-10 - Exibe a pagina de  Login preenchida com dados do usuario");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input.btn.modal-petz-btn.btn-redondo-verde")).click();
    }

    @Entao("^o site realiza o login e exibe o icone do usuario \"([^\"]*)\"$")
    public void o_site_realiza_o_login_e_exibe_o_icone_do_usuario(String Nome) throws IOException {
        assertEquals(Nome, driver.findElement(By.xpath("//span[contains(text(),\""+Nome+"\")]")).getText());
        System.out.println("Passo 11 - Exibe a pagina com nome de usuario logado: " + Nome);
        //assertEquals(Nome,driver.findElement(By.cssSelector("span.username")).getText());
        tirarPrint("Passo 11 - Exibe o usuario logado");
    }


    @Entao("^o site exibe a mensagem \"([^\"]*)\"$")
    public void o_site_exibe_a_mensagem(String frase2) throws IOException {
        assertEquals(frase2, driver.findElement(By.cssSelector("div.dados-incorretos.escondido")).getText());
        System.out.println("Passo 12 - Exibe a mensagem de erro: " + frase2);
        tirarPrint("Passo 12 - Exibe a mensagem de dados incorretos");

    }
*/
}



