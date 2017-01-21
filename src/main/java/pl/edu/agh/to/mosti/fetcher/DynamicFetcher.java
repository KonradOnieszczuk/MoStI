package pl.edu.agh.to.mosti.fetcher;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DynamicFetcher implements Fetcher {

    private long timeOutInSeconds;

    public DynamicFetcher( long timeOutInSeconds ){
        this.timeOutInSeconds = timeOutInSeconds;
    }

    public FetchResult fetch(FetchRequest fetchRequest) throws FetchException {

        // Using selenium and phantomjs
        // http://docs.seleniumhq.org/docs/04_webdriver_advanced.jsp#
        // http://stackoverflow.com/questions/38676719/selenium-using-java-the-path-to-the-driver-executable-must-be-set-by-the-webdr
        // https://github.com/mozilla/geckodriver/releases
        // http://phantomjs.org/download.html

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability( PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "external/phantomjs-2.1.1-windows/bin/phantomjs.exe");

        try {

            WebDriver driver = new PhantomJSDriver(caps);
            driver.get(fetchRequest.getURL());
            WebElement element = (new WebDriverWait(driver, timeOutInSeconds))
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(fetchRequest.getSelector())));

            FetchResult result = new FetchResult();
            result.setText(element.getText());
            return result;

        } catch (TimeoutException e) {
            throw new FetchException("DynamicFetcher TimeoutException occurred: " +  e.getMessage());
        } catch (Exception e) {
            throw new FetchException("DynamicFetcher Exception occurred: " +  e.getMessage());
        }
    }
}

