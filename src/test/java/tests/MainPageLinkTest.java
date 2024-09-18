package pageobjects;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
public class MainPageLinkTest {
    private WebDriver driver;
    private final String mainTestPageUrl = "https://qa-scooter.praktikum-services.ru";
    @Before
    public void begin() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get(mainTestPageUrl);
    }
    // Тест: Логотип Яндекс ведет на главную страницу Яндекс
    // Логика: берем из логотипа ссылку и сравниваем с тестовой
    @Test
    public void checkYandexLogoLinkIsCorrect() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.waitForLoad();
        String actualLink = objMainPage.getHeaderLogoYandexLink();
        String mainYandexUrl = "yandex.ru";
        Assert.assertTrue("Ссылка в логотипе Яндекса некорректна", actualLink.contains(mainYandexUrl));
    }
    //Тест: Логотип Самокат ведет на главную страницу ЯндексСамокат
    // Логика: берем из логотипа ссылку и сравниваем с тестовой
    @Test
    public void checkScooterLogoLinkIsCorrect() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.waitForLoad();
        String actualLink = objMainPage.getHeaderLogoScooterLink();
        Assert.assertTrue("Ссылка в логотипе Самокат некорректна", actualLink.contains(mainTestPageUrl));
    }
    // Тест: клик по логотипу Яндекс открывает главную страницу Яндекс в новой вкладке
    // Логика: берем из логотипа атрибут "target" если он "_blank", то ссылка открывается в новой вкладке
    @Test
    public void checkIsHeaderLogoYandexLinkOpenedInNewTab() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.waitForLoad();
        boolean isOpenedInNewTab = objMainPage.isHeaderLogoYandexLinkOpenedInNewTab();
        Assert.assertTrue("Ссылка логотипа Яндекс не открывается в новой вкладке", isOpenedInNewTab);
    }
    @After
    public void teardown() {
        driver.quit();
    }
}