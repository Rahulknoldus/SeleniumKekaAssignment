package kekaAssignmentstart;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class TestKeka {
    WebDriver driver;//global variable
    //keka url
    String baseUrl = "https://app.keka.com/Account/Login?ReturnUrl=%2F";


        @BeforeMethod
        public void beforeMethod() {

            System.out.println("Starting Test On Chrome Browser First");
            System.out.println("then starting test on firefox");

        }

        //test 1 browser = chrome
        @Test
        public void Chrome_browser_Keka_Test() throws InterruptedException {


            //here a file is call
            //link external input source
            ReadDataConfig excel = new ReadDataConfig("TestData/Untitled spreadsheet.xlsx");
            System.out.println("Launching Google Chrome browser");
            //set the chrome path
            System.setProperty("webdriver.chrome.driver", "src/test/chromedriver_linux64/chromedriver");

            driver = new ChromeDriver();
            driver.manage().window().maximize();

            //wait is use for control the selenium flow
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            //url=keka.com use
            driver.get(baseUrl);

            //send emailId from excel_sheet
            driver.findElement(By.id("email")).sendKeys(excel.getData(0, 0, 0));
            driver.findElement(By.cssSelector("div.login-container:nth-child(3) table.login-container-layout div.login-form-container div.credentials-container form:nth-child(5) div.login-form:nth-child(1) div.form-group div.login-button > button.btn.login-from-btn")).click();

            //reached keka.knoldus.com now
            driver.findElement(By.cssSelector("div.login-container:nth-child(3) table.login-container-layout div.login-form-container div.credentials-container div.form-group:nth-child(6) form.form-horizontal.inline-block.external-login-button-container > button.btn.btn-danger.btn-login.btn-google-login:nth-child(1)")).click();

            // driver.findElement(By.cssSelector("#identifierId")).sendKeys("kuch_bhi_add_krskta_ho");//also send or add keys into email field.
            driver.findElement(By.cssSelector("body.nyoS7c.UzCXuf.EIlDfe:nth-child(2) div.H2SoFe.LZgQXe.TFhTPc:nth-child(1) div.RAYh1e.LZgQXe.qmmlRd div.xkfVF div.Aa1VU div.JhUD8d.SQNfcc.vLGJgb div.zWl5kd div.DRS7Fe.bxPAYd.k6Zj8d div.pwWryf.bxPAYd:nth-child(2) div.Wxwduf.Us7fWe.JhUD8d div.zQJV3 div.dG5hZc div.qhFLie div.FliLIb.DL0QTb div.VfPpkd-dgl2Hf-ppHlrf-sM5MNb button.VfPpkd-LgbsSe.VfPpkd-LgbsSe-OWXEXe-k8QpJ.VfPpkd-LgbsSe-OWXEXe-dgl2Hf.nCP5yc.AjY5Oe.DuMIQc.qIypjc.TrZEUc.lw1w4b > span.VfPpkd-vQzf8d")).click();

            //send password from external sheet
            driver.findElement(By.name("password")).sendKeys(excel.getData(0, 0, 1));
            driver.findElement(By.xpath("//div[@id='passwordNext']/div/button/span")).click();

            //now we login to keka via google ID
            driver.findElement(By.xpath("//body/xhr-app-root[1]/div[1]/xhr-left-nav[1]/nav[1]/div[1]/ul[1]/li[2]/a[1]/span[2]")).click();
            driver.findElement(By.xpath("//a[contains(text(),'Attendance')]")).click();
            driver.findElement(By.xpath("(//a[@id='menu']/span)[3]")).click();

            //now to regularise attendance need pending approval or three dots.
            driver.findElement(By.xpath("//a[contains(text(),'Regularize')]")).click();
            driver.findElement(By.xpath("//button[contains(text(),'+Add Log')]")).click();

            //now add time duration(9.45-6.45)
            driver.findElement(By.xpath("/html/body/modal-container/div/div/attendance-adjustment-request/div[2]/form/div[2]/div/div/div/div/div/div[1]/div[2]/input")).sendKeys("9.45");
            driver.findElement(By.xpath("/html/body/modal-container/div/div/attendance-adjustment-request/div[2]/form/div[2]/div/div/div/div/div/div[1]/div[3]/input")).sendKeys("18.45");

            //send message from external source.
            driver.findElement(By.xpath("//body/modal-container[1]/div[1]/div[1]/attendance-adjustment-request[1]/div[2]/form[1]/div[3]/div[1]/textarea[1]")).sendKeys(excel.getData(0, 0, 4));

            //if you cancel the process(hit on cancel button)
            //driver.findElement(By.xpath("//button[contains(text(),'Cancel')]")).click();

            //for complete the request process
            driver.findElement(By.xpath("//button[contains(text(),'Request')]")).click();

            System.out.println("task completed");
            String actualString = driver.findElement(By.xpath("//body/xhr-app-root[1]/div[1]/employee-me[1]/div[1]/employee-attendance[1]/div[1]/div[1]/div[1]/div[1]/employee-attendance-logs[1]/div[1]/employee-attendance-list-view[1]/div[1]/div[2]/div[6]/div[1]/div[1]/div[1]/span[1]/span[1]")).getText();

            String expectedString = "Full day Weekly-off";

            assertTrue(actualString.contains(expectedString));
            //for quite the project
            driver.quit();

        }
         //----------------------------------------------------------------------------------------------------------------------------------------------------------------   ----------------------------------------------------------------------------------------------------------------------------------------



      //test 2 browser=Firefox
        @Test
        public  void Firefox_browser_Keka_Test() throws InterruptedException {
            ReadDataConfig excel = new ReadDataConfig("TestData/Untitled spreadsheet.xlsx");

            System.setProperty("webdriver.gecko.driver", "src/test/geckodriver-v0.30.0-linux64/geckodriver"); // Setting system properties of FirefoxDriver
            System.out.println("Launching Firefox browser");
            WebDriver driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            //url=keka.com use
            driver.get(baseUrl);


            //send emailId from excel_sheet
            driver.findElement(By.id("email")).sendKeys(excel.getData(0, 0, 0));
            driver.findElement(By.cssSelector("div.login-container:nth-child(3) table.login-container-layout div.login-form-container div.credentials-container form:nth-child(5) div.login-form:nth-child(1) div.form-group div.login-button > button.btn.login-from-btn")).click();

            //reached keka.knoldus.com now
            driver.findElement(By.cssSelector("div.login-container:nth-child(3) table.login-container-layout div.login-form-container div.credentials-container div.form-group:nth-child(6) form.form-horizontal.inline-block.external-login-button-container > button.btn.btn-danger.btn-login.btn-google-login:nth-child(1)")).click();

            // driver.findElement(By.cssSelector("#identifierId")).sendKeys("kuch_bhi_add_krskta_ho");//also send or add keys into email field.
            driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div[1]/div/div/button/span")).click();
            //send password from external sheet
           //driver.findElement(By.xpath("//input[@type='password']")).sendKeys(excel.getData(0,0,1));
            new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='password']"))).sendKeys(excel.getData(0,0,1));

            // driver.findElement(By.name("password")).sendKeys(excel.getData(0, 0, 1));
            driver.findElement(By.xpath("//div[@id='passwordNext']/div/button/span")).click();

            //now we login to keka via google ID
            driver.findElement(By.xpath("//body/xhr-app-root[1]/div[1]/xhr-left-nav[1]/nav[1]/div[1]/ul[1]/li[2]/a[1]/span[2]")).click();
            driver.findElement(By.xpath("//a[contains(text(),'Attendance')]")).click();
            driver.findElement(By.xpath("(//a[@id='menu']/span)[3]")).click();

            //now to regularise attendance need pending approval or three dots.
            driver.findElement(By.xpath("//a[contains(text(),'Regularize')]")).click();
            driver.findElement(By.xpath("//button[contains(text(),'+Add Log')]")).click();

            //now add time duration(9.45-6.45)
            driver.findElement(By.xpath("/html/body/modal-container/div/div/attendance-adjustment-request/div[2]/form/div[2]/div/div/div/div/div/div[1]/div[2]/input")).sendKeys("9.45");
            driver.findElement(By.xpath("/html/body/modal-container/div/div/attendance-adjustment-request/div[2]/form/div[2]/div/div/div/div/div/div[1]/div[3]/input")).sendKeys("18.45");

            //send message from external source.
            driver.findElement(By.xpath("//body/modal-container[1]/div[1]/div[1]/attendance-adjustment-request[1]/div[2]/form[1]/div[3]/div[1]/textarea[1]")).sendKeys(excel.getData(0, 0, 4));

            //if you cancel the process(hit on cancel button)
            //driver.findElement(By.xpath("//button[contains(text(),'Cancel')]")).click();

            //for complete the request process
            driver.findElement(By.xpath("//button[contains(text(),'Request')]")).click();
            System.out.println("task completed");

            //for quite the project
            //driver.quit();--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        }

        //test 3 browser=headless chrome
        @Test
        public  void HeadlessChrome() throws InterruptedException {

            //here a file is call
            //link external input source
            ReadDataConfig excel = new ReadDataConfig("TestData/Untitled spreadsheet.xlsx");


            //set the chrome path
            System.setProperty("webdriver.chrome.driver", "src/test/chromedriver_linux64/chromedriver");
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            driver = new ChromeDriver(chromeOptions);
            driver.manage().window().maximize();
            System.out.println("headless chromeTest run");

            //wait is use for control the selenium flow
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            //url=keka.com use
            driver.get(baseUrl);

            //send emailId from excel_sheet
            driver.findElement(By.id("email")).sendKeys(excel.getData(0, 0, 0));
            driver.findElement(By.cssSelector("div.login-container:nth-child(3) table.login-container-layout div.login-form-container div.credentials-container form:nth-child(5) div.login-form:nth-child(1) div.form-group div.login-button > button.btn.login-from-btn")).click();

            //reached keka.knoldus.com now
            driver.findElement(By.cssSelector("div.login-container:nth-child(3) table.login-container-layout div.login-form-container div.credentials-container div.form-group:nth-child(6) form.form-horizontal.inline-block.external-login-button-container > button.btn.btn-danger.btn-login.btn-google-login:nth-child(1)")).click();

            // driver.findElement(By.cssSelector("#identifierId")).sendKeys("kuch_bhi_add_krskta_ho");//also send or add keys into email field.
            driver.findElement(By.xpath("")).click();

            //send password from external sheet
            driver.findElement(By.name("password")).sendKeys(excel.getData(0, 0, 1));
            driver.findElement(By.xpath("//div[@id='passwordNext']/div/button/span")).click();

            //now we login to keka via google ID
            driver.findElement(By.xpath("//body/xhr-app-root[1]/div[1]/xhr-left-nav[1]/nav[1]/div[1]/ul[1]/li[2]/a[1]/span[2]")).click();
            driver.findElement(By.xpath("//a[contains(text(),'Attendance')]")).click();
            // driver.findElement(By.xpath("(//a[@id='menu']/span)[3]")).click();


            //now to regularise attendance need pending approval or three dots.
            driver.findElement(By.xpath("//a[contains(text(),'Regularize')]")).click();
            driver.findElement(By.xpath("//button[contains(text(),'+Add Log')]")).click();

            //now add time duration(9.45-6.45)
            driver.findElement(By.xpath("/html/body/modal-container/div/div/attendance-adjustment-request/div[2]/form/div[2]/div/div/div/div/div/div[1]/div[2]/input")).sendKeys("9.45");
            driver.findElement(By.xpath("/html/body/modal-container/div/div/attendance-adjustment-request/div[2]/form/div[2]/div/div/div/div/div/div[1]/div[3]/input")).sendKeys("18.45");

            //send message from external source.
            driver.findElement(By.xpath("//body/modal-container[1]/div[1]/div[1]/attendance-adjustment-request[1]/div[2]/form[1]/div[3]/div[1]/textarea[1]")).sendKeys(excel.getData(0, 0, 4));

            //if you cancel the process(hit on cancel button)
            //driver.findElement(By.xpath("//button[contains(text(),'Cancel')]")).click();

            //for complete the request process
            driver.findElement(By.xpath("//button[contains(text(),'Request')]")).click();
            System.out.println("task completed");

            //for quite the project
            //driver.quit();
        }

        @AfterMethod
        public void afterMethod() {
          //  driver.close();
            System.out.println("Finished Test On Chrome Browser");
            System.out.println("Finished Test On firefox Browser");
        }
    }

