package stepDefinition;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjectModel.HistoryByStoreObjects;
import testRunner.ExtentManager;
import utilities.PageLoadWait;
import utilities.PropertyReader;
import utilities.SandBoxLogin;
import java.lang.reflect.Method;


public class HistoryByStorePage extends HistoryByStoreObjects {
    private WebDriver driver;
    private ExtentReports extent;
    private ExtentTest test;
    private Method method;

    @BeforeMethod
    public void chromeSetup(Method method){
        this.method =method;
        extent = ExtentManager.getExtentReports();
        String testMethod = method.getName();
        test =extent.createTest(testMethod);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(description = "SandBox Login Test")
    public void sandBoxLoginTest() throws InterruptedException {

        test.log(Status.INFO,"Test Started");
        driver.get(loginURL);

        WebElement Email = driver.findElement(By.xpath(email));
        Email.sendKeys(PropertyReader.getProperties("Email"));

        WebElement Password = driver.findElement(By.xpath(password));
        Password.sendKeys(PropertyReader.getProperties("Password"));
        test.log(Status.INFO,"Logged In to the Application");

        WebElement loginButton = driver.findElement(By.xpath(loginBtn));
        loginButton.click();

            PageLoadWait.waitForPageToLoad(driver);

        WebElement EmailToPhonePopUp = PageLoadWait.waitForElementPresence(driver, By.xpath(emailToPhnPopUp));
        if (EmailToPhonePopUp != null){
            EmailToPhonePopUp.click();
        }
        test.log(Status.INFO,"Popup is closed");
        test.log(Status.PASS,"Sand Box Login Test is successful");
    }

    @Test(description = "Price History GrandTotal Validation", priority = 1)
    public void priceHistoryGrandTotalValidation() throws InterruptedException {

        SandBoxLogin sandBoxLogin = new SandBoxLogin(driver);
        sandBoxLogin.login("Email","Password");
        test.log(Status.INFO,"Grand Total Validation Test Started");

        WebElement chargeBacks = PageLoadWait.waitForElementPresence(driver, By.xpath(ChargeBacks));
        chargeBacks.click();
        WebElement historyByStore = PageLoadWait.waitForElementToBeClickable(driver, By.xpath(HistoryByStore));
        historyByStore.click();

        test.log(Status.INFO,"Begin Data Validation");
        WebElement table = PageLoadWait.waitForElementToBeClickable(driver, By.xpath(ReversalTable));
        Thread.sleep(18000);

        for (int col = 2; col <= 8; col++) {
            double sum = 0;
            for (int row = 1; row <= 10; row++) {
                WebElement cell = table.findElement(By.xpath("./tbody/tr[" + row + "]/td[" + col + "]"));
                String cellText = cell.getText().replaceAll("[^\\d.]", "");
                double cellValue = Double.parseDouble(cellText);
                sum += cellValue;
            }

            WebElement grandTotalCell = table.findElement(By.xpath("./tbody/tr[12]/td[" + col + "]"));
            double grandTotal = Double.parseDouble(grandTotalCell.getText().replaceAll("[^\\d.]", ""));

            if (Math.abs(sum - grandTotal) < 0.001) {
                test.log(Status.PASS,"Sum is Matched for the column " + col);

            } else {
                test.log(Status.FAIL,"Sum validation failed for column " + col +
                        " Actual_Sum:" + sum + " Expected_Sum:"+ grandTotal);
            }
        }

    }

    @AfterMethod
    public void tearDown() { driver.quit(); extent.flush();}

}
