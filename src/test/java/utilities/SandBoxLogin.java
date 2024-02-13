package utilities;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import stepDefinition.HistoryByStorePage;

public class SandBoxLogin extends HistoryByStorePage {

    private static WebDriver driver;

    public SandBoxLogin(WebDriver driver){
        this.driver = driver;
    }

    public static void login(String emailPropertyKey, String passwordPropertyKey){
        driver.get(loginURL);

        WebElement Email = driver.findElement(By.xpath(email));
        Email.sendKeys(PropertyReader.getProperties(emailPropertyKey));

        WebElement Password = driver.findElement(By.xpath(password));
        Password.sendKeys(PropertyReader.getProperties(passwordPropertyKey));

        WebElement loginButton = driver.findElement(By.xpath(loginBtn));
        loginButton.click();

        PageLoadWait.waitForPageToLoad(driver);

        WebElement EmailToPhonePopUp = PageLoadWait
                .waitForElementPresence(driver, By.xpath(emailToPhnPopUp));
        if (EmailToPhonePopUp != null){
            EmailToPhonePopUp.click();
        }
    }

}
