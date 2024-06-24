import io.qameta.allure.Epic;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import static org.testng.Assert.assertTrue;


@Epic("Проверка формы онлайн оплаты.")
public class MtsByTest {
        public WebDriver driver;
        public WebDriverWait wait;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        System.setProperty("webdriver.chrome.args", "--disable-logging");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(chromeOptions);
        wait = new WebDriverWait(driver, (15));
        driver.get("https://mts.by");
        driver.findElement(By.id("cookie-agree")).click();
    }

    @Test
    public void testCheckBlockName() {
        WebElement blockName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"col-12 col-xl-8\"] h2")));

        Assert.assertEquals(blockName.getText(), "Онлайн пополнение\nбез комиссии");
    }

    @Test
    public void testCheckForPaymentSystemLogos() {
        List<WebElement> logoElements = driver.findElements(By.cssSelector("[class=\"pay__partners\"] img"));

        assertTrue(logoElements.size() > 0, "No logos found in the payment partners block.");
        for (WebElement logo : logoElements) {
            Assert.assertTrue(logo.isDisplayed(), "Logo " + logo.getAttribute("alt") + " not found.");
        }
    }

    @Test
    public void testCheckLink() {
        WebElement checkLink = driver.findElement(By.cssSelector(".pay__partners~a"));
        checkLink.click();

        wait.until(ExpectedConditions.urlContains("www.mts.by/help/poryadok"));
        assertTrue(driver.getCurrentUrl().contains("www.mts.by/help/poryadok"));
    }

    @Test
    public void testCheckTheButtonOperation() {
        driver.get("https://mts.by");
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.select__now")));
        Assert.assertEquals(dropdown.getText(), "Услуги связи");

        WebElement phoneNumberInput = driver.findElement(By.id("connection-phone"));
        phoneNumberInput.sendKeys("297777777");
        WebElement amountInput = driver.findElement(By.id("connection-sum"));
        amountInput.sendKeys("10");
        WebElement continueButton = driver.findElement(By.cssSelector("#pay-connection button"));
        continueButton.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.bepaid-app iframe")));
        driver.switchTo().frame(iframe);

        String result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.pay-description__text"))).getText();
        Assert.assertEquals(result, "Оплата: Услуги связи Номер:375297777777");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
