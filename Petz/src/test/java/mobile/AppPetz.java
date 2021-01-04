package mobile;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import static org.junit.Assert.assertEquals;


public class AppPetz {

    private AndroidDriver<MobileElement> driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        // Emulador Local
        /*
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("automationName", "UIAutomator2");
        desiredCapabilities.setCapability("autoGrantPermissions", true);
        desiredCapabilities.setCapability("appPackage", "br.com.petz");
        desiredCapabilities.setCapability("appActivity", "br.com.hanzo.petz.view.MainActivity");
        desiredCapabilities.setCapability("platformVersion", "10.0");
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");
        */
        // Executar na nuvem via TestObject
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("automationName", "UIAutomator2");
        desiredCapabilities.setCapability("deviceName", "Samsung_Galaxy_S9_free");
        desiredCapabilities.setCapability("autoGrantPermissions", true);
        desiredCapabilities.setCapability("appPackage", "br.com.petz");
        desiredCapabilities.setCapability("appActivity", "br.com.hanzo.petz.view.MainActivity");
        desiredCapabilities.setCapability("platformVersion", "10.0");
        desiredCapabilities.setCapability("testobject_api_key", "F9781B9060DE427EB5A8656B57339505");
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);

        URL remoteUrl = new URL("https://us1.appium.testobject.com/wd/hub");

        driver = new AndroidDriver<MobileElement>(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        MobileElement usuario = (MobileElement) driver.findElementById("br.com.petz:id/et_user_name");
        usuario.click();
        usuario.sendKeys("caiodc@maildrop.cc");
        MobileElement senha = (MobileElement) driver.findElementById("br.com.petz:id/et_password");
        senha.click();
        senha.sendKeys("calu@2021");
        Thread.sleep(3000);
        MobileElement btnEntrar = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.RelativeLayout[1]/android.widget.TextView");
        btnEntrar.click();
        Thread.sleep(6000);
        // logado no site da Petz
        MobileElement lupa = (MobileElement) driver.findElementByAccessibilityId("pesquisa");
        lupa.click();
        Thread.sleep(3000);
        MobileElement campoBusca = (MobileElement) driver.findElementById("br.com.petz:id/et_search");
        campoBusca.click();
        campoBusca.sendKeys("ração");
        campoBusca.click();
        MobileElement selecionaProduto = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.TextView");
        selecionaProduto.click();
        Thread.sleep(2000);
        MobileElement nomeProduto = (MobileElement) driver.findElementById("br.com.petz:id/tv_prod_name");
        assertEquals("Ração Royal Canin Maxi - Cães Adultos - 15kg", nomeProduto.getText());
        MobileElement precoProduto = (MobileElement) driver.findElementById("br.com.petz:id/tv_prod_main_price");
        assertEquals("232.69", precoProduto.getText());
        Thread.sleep(2000);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}


