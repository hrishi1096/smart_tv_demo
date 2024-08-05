package browserstack;
import io.appium.java_client.android.AndroidDriver;
import java.net.URL;
import java.util.HashMap;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AmazonFireTVStick {
    public static void main(String[] args) throws Exception {
        String USERNAME = "<YOUR_USERNAME>";
        String ACCESS_KEY = "<YOUR_ACCESS_KEY>";
        HashMap<String, Object> browserstackOptions = new HashMap<>();
        browserstackOptions.put("debug", "true");
        browserstackOptions.put("idleTimeout", "300");


        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("bstack:options", browserstackOptions);
        caps.setCapability("build", "Java Appium Sample");
        caps.setCapability("deviceName", "Amazon Fire TV Stick 4K");
        caps.setCapability("osVersion", "7.1");
        caps.setCapability("app", "AndroidApp");

        AndroidDriver driver = new AndroidDriver(new URL("https://" + USERNAME + ":" + ACCESS_KEY +
                "@hub-cloud.browserstack.com/wd/hub"), caps);
        JavascriptExecutor jse = (JavascriptExecutor)driver;


        try {
    Thread.sleep(5000);
            System.out.println(driver.getPageSource());

    driver.pressKey(new KeyEvent(AndroidKey.DPAD_DOWN));
            Thread.sleep(5000);
// DPAD_DOWN
    driver.pressKey(new KeyEvent(AndroidKey.DPAD_RIGHT)); // DPAD_RIGHT
            Thread.sleep(5000);
            driver.pressKey(new KeyEvent(AndroidKey.DPAD_LEFT)); // DPAD_RIGHT
            Thread.sleep(5000);
            driver.pressKey(new KeyEvent(AndroidKey.DPAD_RIGHT)); // DPAD_RIGHT
            Thread.sleep(5000);

            System.out.println("After :"+driver.getPageSource());

            String videoName = driver.findElement(By.xpath("//*[@focused='true']"))
            .getAttribute("content-desc");

    driver.pressKey(new KeyEvent(AndroidKey.DPAD_CENTER)); // DPAD_CENTER
    Thread.sleep(5000);
    driver.pressKey(new KeyEvent(AndroidKey.MEDIA_PLAY_PAUSE)); // MEDIA_PLAY_PAUSE
    Thread.sleep(3000);
    driver.pressKey(new KeyEvent(AndroidKey.MEDIA_PLAY_PAUSE)); // MEDIA_PLAY_PAUSE
    Thread.sleep(3000);
    driver.pressKey(new KeyEvent(AndroidKey.MEDIA_REWIND)); // MEDIA_REWIND
    Thread.sleep(3000);
    driver.pressKey(new KeyEvent(AndroidKey.BACK)); // BACK


    jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Test Passed!\"}}");

}
catch (Exception e)
{
    jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Test Failed\"}}");
    throw e;
}
finally {

    driver.quit();

    }

    }
}

