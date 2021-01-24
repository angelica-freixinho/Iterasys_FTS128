package mobile;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.junit.Assert.assertEquals;

public class Appicarros {

    private AndroidDriver<MobileElement> driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        //Emulador Local
        /*
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("automationName", "UIAutomator2");
        desiredCapabilities.setCapability("autoGrantPermissions", true);
        desiredCapabilities.setCapability("appPackage", "br.com.icarros.androidapp");
        desiredCapabilities.setCapability("appActivity", "br.com.icarros.androidapp.ui.home.MainActivity");
        desiredCapabilities.setCapability("platformVersion", "10.0");
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");
        */
        // Executar na nuvem via TestObject
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("automationName", "UIAutomator2");
        desiredCapabilities.setCapability("deviceName", "Samsung_Galaxy_S9_free");
        //desiredCapabilities.setCapability("autoGrantPermissions", true);
        desiredCapabilities.setCapability("appPackage", "br.com.icarros.androidapp");
        desiredCapabilities.setCapability("appActivity", "br.com.icarros.androidapp.ui.home.MainActivity");
        desiredCapabilities.setCapability("platformVersion", "10.0");
        desiredCapabilities.setCapability("testobject_api_key", "BE93647E39434652B52F70236E30B92E");
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);

        URL remoteUrl = new URL("https://us1.appium.testobject.com/wd/hub");

        driver = new AndroidDriver<MobileElement>(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException {
       //MobileElement coockie = (MobileElement) driver.findElementById("android:id/button1");
       //coockie.click();
        MobileElement pesquisa = (MobileElement) driver.findElementById("br.com.icarros.androidapp:id/searchText");
        pesquisa.click();
        Thread.sleep(3000);
        MobileElement textoPesquisa = (MobileElement) driver.findElementById("br.com.icarros.androidapp:id/fullSearchText");
        textoPesquisa.click();
        textoPesquisa.sendKeys("spin  2016");
        MobileElement busca = (MobileElement) driver.findElementById("br.com.icarros.androidapp:id/searchButton");
        busca.click();
        Thread.sleep(3000);
        MobileElement selecionaItem = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.view.View");
        selecionaItem.click();
        Thread.sleep(5000);
        //MobileElement cookie = (MobileElement) driver.findElementById("android:id/button1");
        //cookie.click();

        //MobileElement produto = (MobileElement) driver.findElementById("br.com.icarros.androidapp:id/makeAndModelText");
        //produto.click();
        //MobileElement btn = (MobileElement) driver.findElementById("android:id/button1");
        //btn.click();
        MobileElement nomeCarro = (MobileElement) driver.findElementById("br.com.icarros.androidapp:id/dealNameText");
        assertEquals("Chevrolet Spin", nomeCarro.getText());
        Thread.sleep(3000);
        MobileElement precoCarro = (MobileElement) driver.findElementById("br.com.icarros.androidapp:id/dealPriceText");
        assertEquals("R$ 32,720", precoCarro.getText());
        Thread.sleep(5000);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}