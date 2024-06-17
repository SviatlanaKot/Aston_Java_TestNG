package Task_L16;

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


public class FormOfPaymentTest {
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
    public void testCheckTheButtonOperation() {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("span.select__now")));
        Assert.assertEquals(dropdown.getText(), "Услуги связи");

        WebElement phoneNumberInput = driver.findElement(By.id("connection-phone"));
        phoneNumberInput.sendKeys("297777777");
        WebElement amountInput = driver.findElement(By.id("connection-sum"));
        amountInput.sendKeys("10");
        WebElement continueButton = driver.findElement(By.cssSelector("#pay-connection button"));
        continueButton.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.bepaid-app iframe")));
        driver.switchTo().frame(iframe);

        String amountText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.pay-description__cost"))).getText();
        Assert.assertEquals(amountText, "10.00 BYN");

        String phoneNumberText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("span.pay-description__text"))).getText();
        Assert.assertEquals(phoneNumberText, "Оплата: Услуги связи Номер:375297777777");

        String amountButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.card-page__card > button"))).getText();
        Assert.assertEquals(amountButton, "Оплатить 10.00 BYN");

        String cardNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("#cc-number~label"))).getText();
        Assert.assertEquals(cardNumberField, "Номер карты");

        String validityField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[text()=\"Срок действия\"]"))).getText();
        Assert.assertEquals(validityField, "Срок действия");

        String cvcField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[text()=\"CVC\"]"))).getText();
        Assert.assertEquals(cvcField, "CVC");

        String holderName = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[text()=\"Имя держателя (как на карте)\"]"))).getText();
        Assert.assertEquals(holderName, "Имя держателя (как на карте)");

        WebElement mastercard = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.cards-brands img[src*='mastercard']")));
        Assert.assertTrue(mastercard.isDisplayed());

        WebElement visa = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.cards-brands img[src*='visa']")));
        Assert.assertTrue(visa.isDisplayed());

        WebElement belkart = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.cards-brands img[src*='belkart']")));
        Assert.assertTrue(belkart.isDisplayed());

        WebElement mir = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.cards-brands img[src*='mir']")));
        Assert.assertTrue(mir.isDisplayed());
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
