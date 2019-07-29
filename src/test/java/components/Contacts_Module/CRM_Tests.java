package components.Contacts_Module;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BriteERPUtils;
import utilities.SeleniumUtils;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CRM_Tests {

    String listOfOpportunities= "div[class='table-responsive']";
    String CRM_locator ="//span[contains(text(),'CRM')][1]";
    String ListLocator ="button[aria-label='list']";

    String First_Opportunity ="td[width='1'] ";
    String Action_locator ="//button[contains(text(),'Action')]";

    WebDriver driver;

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://34.220.250.213/web/login");
    }
    @Test   // First Acceptance Criteria:
    // Verify that user should able to see the list view

    public void CRM_List_View_Test_1() {

        BriteERPUtils.login(driver, "eventscrmmanager15@info.com", "eventscrmmanager");
        driver.findElement(By.xpath(CRM_locator)).click();
        SeleniumUtils.waitPlease(2);
        driver.findElement(By.cssSelector(ListLocator)).click();

        driver.findElement(By.cssSelector(First_Opportunity)).click();
        driver.findElement(By.cssSelector(listOfOpportunities)).isDisplayed();
        SeleniumUtils.waitPlease(3);
        String excpected= "Pipeline - Odoo";
        String actual= driver.getTitle();
        Assert.assertEquals(actual,excpected);
        System.out.println("Test passed,List of Opportunities is displayed");
        SeleniumUtils.waitPlease(2);

    }
    @Test   // Second Acceptance Criteria:
    // verify that user should able to delete the opportunity from doropDownList.

    public void Delete_Option_DropDown_Test2() throws Exception {


        BriteERPUtils.login(driver, "eventscrmmanager15@info.com", "eventscrmmanager");
        driver.findElement(By.xpath(CRM_locator)).click();
        SeleniumUtils.waitPlease(2);
        driver.findElement(By.cssSelector(ListLocator)).click();

        driver.findElement(By.cssSelector(First_Opportunity)).click();
        SeleniumUtils.waitPlease(2);


        driver.findElement(By.xpath(Action_locator)).click();
        BriteERPUtils.waitUntilLoaderScreenDisappear(driver);
        SeleniumUtils.waitPlease(2);

//

        WebElement delete=driver.findElement(By.xpath("//a[contains(text(),'Delete')]"));
        String expected=driver.findElement(By.xpath("(//strong[@class='o_kanban_record_title'] /span)[1]")).getText();
        Actions action = new Actions(driver);
        List<WebElement> options = driver.findElements(By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div[2]/div/div[2]/ul"));

//        Thread.sleep(3);
//        String temp = driver.findElement(By.className("o_kanban_record_title")).getText();
//        System.out.println(temp);

        for(WebElement option:options){
         // action.contextClick(delete).perform();
          action.doubleClick(delete).perform();
            SeleniumUtils.waitPlease(5);
        }

        driver.findElement(By.xpath("//span[text()='Ok']")).click();
        SeleniumUtils.waitPlease(5);
        BriteERPUtils.waitUntilLoaderScreenDisappear(driver);




        Actions action1 = new Actions(driver);
        List<WebElement> newOptions = driver.findElements(By.xpath("//td[@class='o_data_cell o_required_modifier']"));
        for(WebElement option1:newOptions){
            System.out.println(option1.getText());

        }

       // System.out.println(newOptions.contains(expected));
       // Assert.assertTrue(newOptions.contains(temp));
    }


    @AfterMethod
    public void teardown(){
        driver.quit();

    }
}
