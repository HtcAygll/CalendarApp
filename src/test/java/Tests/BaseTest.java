package Tests;

import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.URL;


public class BaseTest {

    //public AppiumDriver driver;

    public AndroidDriver androidDriver;

    public WebDriverWait wait;

    @Before
    public void beforeTest() throws Exception {
        final DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("deviceName", "Pixel_2_10");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("platformVersion", "10.0");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appActivity", "com.google.android.apps.nexuslauncher.NexusLauncherActivity");
        capabilities.setCapability("appPackage", "com.google.android.apps.nexuslauncher");
        capabilities.setCapability("autoAcceptAlerts", true);

        URL url = new URL("http://localhost:4723/wd/hub");
        androidDriver = new AndroidDriver(url, capabilities);
    }

    @After
    public void afterTest() {

        takeScreenshot("ScreenShot");

        if (androidDriver != null) {
            androidDriver.quit();
            androidDriver = null;
        }
    }

    public void takeScreenshot(String screenShotName) {

        File file  = ((TakesScreenshot)androidDriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(screenShotName + ".jpg"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
