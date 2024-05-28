package stepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;


public class FlipkartProductFlow {

    private WebDriver driver;
    private int count;

    @FindBy(xpath = "//span[text() = 'Home & Furniture']")
    WebElement homeButton;

    @FindBy(xpath = "//a[contains(text(),'Home Decor')]")
    WebElement homeDecor;

    List<WebElement> allProducts;

    @BeforeClass
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    @Test
    public void productName() {

        Actions actions = new Actions(driver);

        driver.get("https://www.flipkart.com/");
        actions.moveToElement(homeButton).perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.visibilityOf(homeDecor));

        homeDecor.click();

        allProducts = driver.findElements(By.xpath("//div[@class = 'DOjaWF gdgoEp']" +
                "//div[contains(@class, '75n')]//a[string-length(text())>0]"));

        for (WebElement element : allProducts){
            System.out.println(element.getText());
            count++;
        }

        System.out.println("Total Product Count: " + count);

        driver.close();

    }

}
