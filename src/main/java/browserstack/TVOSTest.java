package browserstack;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import java.util.HashMap;

public class TVOSTest {

    public static String USERNAME = "<YOUR_USERNAME>";
    public static String ACCESS_KEY = "<YOUR_ACCESS_KEY>";

    public static void main(String[] args) throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
        browserstackOptions.put("projectName" , "TVOS-PROJECT");
        browserstackOptions.put("buildName" , "TVOS-BUILD");
        browserstackOptions.put("sessionName" , "TVOS-SESSION");
        browserstackOptions.put("networkLogs" , true);
        capabilities.setCapability("bstack:options" , browserstackOptions);
        capabilities.setCapability("platformName" , "tvOS");
        capabilities.setCapability("platformVersion" , "16.3");
        capabilities.setCapability("deviceName" , "Apple TV 4K");
        capabilities.setCapability("app" , "tvOSApp");
        capabilities.setCapability("automationName" , "XCUITest");

        IOSDriver driver = new IOSDriver(new URL("https://" + USERNAME + ":" + ACCESS_KEY +
                "@hub-cloud.browserstack.com/wd/hub"), capabilities);
        JavascriptExecutor jse = (JavascriptExecutor)driver;

        try {
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            System.out.println(driver.getPageSource());
            driver.getScreenshotAs(OutputType.FILE);
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Test Passed!\"}}");

        }catch (Exception e)
        {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Test Failed\"}}");

        }finally {
            driver.quit();

        }
    }
}
