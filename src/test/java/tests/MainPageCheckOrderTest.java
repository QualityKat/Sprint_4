package pageobjects;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.Assert.*;
public class MainPageCheckOrderTest {
    private WebDriver driver;
    private final String mainTestPageUrl = "https://qa-scooter.praktikum-services.ru";
    private final String inputTrack = "999999";
    @Before
    public void begin() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get(mainTestPageUrl);
    }
    // Тест: ввод неверного номера заказа ведет на страницу "Такого заказа нет"
    // Логика: кликаем по кнопке статус заказа, вводим несуществующий номер заказа и ждем загрузки картинки
    @Test
    public void wrongTrackNotFound () {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.waitForLoad();
        objMainPage.clickOnHeaderOrderStatusButton();
        objMainPage.setInputTrack(inputTrack);
        objMainPage.clickOnGoButton();
        objMainPage.waitForLoadNotFoundPic();
        assertTrue(driver.findElement(objMainPage.trackNotFound).isDisplayed());
    }
    @After
    public void teardown() {
        driver.quit();
    }
}
