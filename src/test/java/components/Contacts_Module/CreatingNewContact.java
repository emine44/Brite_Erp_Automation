package components.Contacts_Module;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BriteERPUtils;
import utilities.SeleniumUtils;

import java.util.concurrent.TimeUnit;

public class CreatingNewContact {
    WebDriver driver;
    String name="Emine Yildirim";


    @BeforeMethod
    public void setup() {
        //driver setup
        WebDriverManager.chromedriver().setup();
        //to initialize driver
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://34.220.250.213/web/login");
        BriteERPUtils.login(driver, "eventscrmmanager36@info.com", "eventscrmmanager");
        String expectedT="Contacts - Odoo";
        SeleniumUtils.waitPlease(3);
        driver.findElement(By.xpath("//span[contains(text(),'Contacts')]")).click();
        SeleniumUtils.waitPlease(5);
        String actualt=driver.getTitle();
        Assert.assertEquals(actualt,expectedT);
        SeleniumUtils.waitPlease(3);
        driver.findElement(By.xpath("//button[contains(text(),'Create')]")).click();
        SeleniumUtils.waitPlease(5);
       // driver.findElement(By.xpath("//input[@id='o_field_input_334']")).sendKeys(name);
        driver.findElement(By.xpath("//input[@name='name']")).sendKeys(name);
        SeleniumUtils.waitPlease(3);
    }


//User should able to create new contact
   @Test
   public void verifyCreateButton(){

   driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
   SeleniumUtils.waitPlease(3);
   String expectedT=name+" - Odoo";
   String actualT=driver.getTitle();
       SeleniumUtils.waitPlease(3);
   Assert.assertEquals(actualT,expectedT);

   }
//user should able to discard newly created contact
    @Test
    public void verifyDiscardButton() {

        driver.findElement(By.xpath("//button[contains(text(),'Discard')]")).click();
        SeleniumUtils.waitPlease(3);
        String expected="The record has been modified, your changes will be discarded. Do you want to proceed?";
        String actual= driver.findElement(By.className("modal-body")).getText();
        SeleniumUtils.waitPlease(3);
        Assert.assertEquals(expected,actual);




    }






    @AfterMethod
    public void teardown() {
        driver.close();
    }






}

