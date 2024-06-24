package Task_L16;

import io.qameta.allure.Epic;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;


@Epic("Форма оплаты. Проверка названий плейсхолдеров.")
public class PayFormTest {
        public WebDriver driver;
        public WebDriverWait wait;

    @BeforeMethod
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

    @DataProvider(name = "fieldPlaceholdersForCommunicationServices")
    public Object[][] fieldPlaceholdersForCommunicationServices() {
        return new Object[][] {
                {"connection-phone", "Номер телефона"},
                {"connection-sum", "Сумма"},
                {"connection-email", "E-mail для отправки чека"}
        };
    }

    @Test(dataProvider = "fieldPlaceholdersForCommunicationServices")
    public void testCheckFieldPlaceholdersForCommunicationServices(String fieldId, String expectedPlaceholder) {
        WebElement select = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.select__header")));
        select.click();
        WebElement selectList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()=\"Услуги связи\"]")));
        selectList.click();

        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(fieldId)));
        String placeholderText = field.getAttribute("placeholder");
        Assert.assertEquals(placeholderText, expectedPlaceholder);
    }

    @DataProvider(name = "fieldPlaceholdersForHomeInternet")
    public Object[][] fieldPlaceholdersForHomeInternet() {
        return new Object[][] {
                {"internet-phone", "Номер абонента"},
                {"internet-sum", "Сумма"},
                {"internet-email", "E-mail для отправки чека"}
        };
    }

    @Test(dataProvider = "fieldPlaceholdersForHomeInternet")
    public void testCheckFieldPlaceholdersForHomeInternet(String fieldId, String expectedPlaceholder) {
        WebElement select = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.select__header")));
        select.click();
        WebElement selectList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()=\"Домашний интернет\"]")));
        selectList.click();
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(fieldId)));
        String placeholderText = field.getAttribute("placeholder");

        Assert.assertEquals(placeholderText, expectedPlaceholder);
    }

    @DataProvider(name = "fieldPlaceholdersForInstallment")
    public Object[][] fieldPlaceholdersForInstallment() {
        return new Object[][] {
                {"score-instalment", "Номер счета на 44"},
                {"instalment-sum", "Сумма"},
                {"instalment-email", "E-mail для отправки чека"}
        };
    }

    @Test(dataProvider = "fieldPlaceholdersForInstallment")
    public void testCheckFieldPlaceholdersForInstallment(String fieldId, String expectedPlaceholder) {
        WebElement select = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.select__header")));
        select.click();
        WebElement selectList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()=\"Рассрочка\"]")));
        selectList.click();
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(fieldId)));
        String placeholderText = field.getAttribute("placeholder");

        Assert.assertEquals(placeholderText, expectedPlaceholder);
    }

    @DataProvider(name = "fieldPlaceholdersForArrears")
    public Object[][] fieldPlaceholdersForArrears() {
        return new Object[][] {
                {"score-arrears", "Номер счета на 2073"},
                {"arrears-sum", "Сумма"},
                {"arrears-email", "E-mail для отправки чека"}
        };
    }

    @Test(dataProvider = "fieldPlaceholdersForArrears")
    public void testCheckFieldPlaceholdersForArrears(String fieldId, String expectedPlaceholder) {
        WebElement select = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.select__header")));
        select.click();
        WebElement selectList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()=\"Задолженность\"]")));
        selectList.click();
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(fieldId)));
        String placeholderText = field.getAttribute("placeholder");

        Assert.assertEquals(placeholderText, expectedPlaceholder);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
