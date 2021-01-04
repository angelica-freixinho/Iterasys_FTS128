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

public class Calc {

    private AndroidDriver<MobileElement> driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        //Emulador Local
        /*
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("automationName", "UIAutomator2");
        //desiredCapabilities.setCapability("deviceName", "emulator5554");
        desiredCapabilities.setCapability("appPackage", "com.google.android.calculator");
        desiredCapabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
        desiredCapabilities.setCapability("platformVersion", "10.0");
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");
        */

        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("automationName", "UIAutomator2");
        desiredCapabilities.setCapability("deviceName", "Samsung_Galaxy_S9_free");
        desiredCapabilities.setCapability("appPackage", "com.google.android.calculator");
        desiredCapabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
        desiredCapabilities.setCapability("platformVersion", "10.0");
        desiredCapabilities.setCapability("testobject_api_key", "1C7D1C4FF2B7443B9CB5891A98714990");
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);

        URL remoteUrl = new URL("https://us1.appium.testobject.com/wd/hub");


        driver = new AndroidDriver<MobileElement>(remoteUrl, desiredCapabilities);
    }

    @Test
    public void somar2Numeros() throws InterruptedException {
        // prepara ou Configura
        MobileElement btn2 = (MobileElement) driver.findElementById("com.google.android.calculator:id/digit_2");
        btn2.click();
        MobileElement btnSomar = (MobileElement) driver.findElementByAccessibilityId("plus");
        btnSomar.click();
        MobileElement btn3 = (MobileElement) driver.findElementById("com.google.android.calculator:id/digit_3");
        btn3.click();
        MobileElement btnIgual = (MobileElement) driver.findElementByAccessibilityId("equals");
        btnIgual.click();
        Thread.sleep(5000);
        MobileElement visor = (MobileElement) driver.findElementById("com.google.android.calculator:id/result_final");
        //Valida
        assertEquals("5", visor.getText());
    }

    @Test
    public void subtrair2Numeros() throws InterruptedException {
        MobileElement btn1 = (MobileElement) driver.findElementById("com.google.android.calculator:id/digit_1");
        btn1.click();
        MobileElement btn2 = (MobileElement) driver.findElementById("com.google.android.calculator:id/digit_2");
        btn2.click();
        MobileElement btnSubtrair = (MobileElement) driver.findElementByAccessibilityId("minus");
        btnSubtrair.click();
        MobileElement btn6 = (MobileElement) driver.findElementById("com.google.android.calculator:id/digit_6");
        btn6.click();
        MobileElement btnIgual = (MobileElement) driver.findElementByAccessibilityId("equals");
        btnIgual.click();
        Thread.sleep(3000);
        MobileElement visor = (MobileElement) driver.findElementById("com.google.android.calculator:id/result_final");
        // Valida
        assertEquals("6", visor.getText());
    }

    @Test
    public void multiplicar2Numeros() throws InterruptedException {
        MobileElement btn3 = (MobileElement) driver.findElementById("com.google.android.calculator:id/digit_3");
        btn3.click();
        //MobileElement btnVezes = (MobileElement) driver.findElementByAccessibilityId("×");
        MobileElement btnVezes = (MobileElement) driver.findElementById("com.google.android.calculator:id/op_mul");
        btnVezes.click();
        Thread.sleep(3000);
        MobileElement btn5 = (MobileElement) driver.findElementById("com.google.android.calculator:id/digit_5");
        btn5.click();
        MobileElement btnIgual = (MobileElement) driver.findElementByAccessibilityId("equals");
        btnIgual.click();
        Thread.sleep(3000);
        MobileElement visor = (MobileElement) driver.findElementById("com.google.android.calculator:id/result_final");
        assertEquals("15", visor.getText());
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}

