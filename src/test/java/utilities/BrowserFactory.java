package utilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class BrowserFactory {
    /*@param browser name
    @return browser object, otherwise throw exception to prevent test running

*/
    public static WebDriver getDriver(String browser){

        if(browser.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();

        }else if (browser.equals("firefox")){
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        }else{

            throw new IllegalArgumentException("Wrong browser name");


        }


    }



}
