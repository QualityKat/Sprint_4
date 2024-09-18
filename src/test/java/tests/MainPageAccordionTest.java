package pageobjects;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.hamcrest.Matchers.equalTo;


@RunWith(Parameterized.class)

public class MainPageAccordionTest {
    private WebDriver driver;
    private final int indexNumber;
    private final String expectedAccordionHeader;
    private final String expectedAccordionText;
    private final String mainTestPageUrl = "https://qa-scooter.praktikum-services.ru";

    public MainPageAccordionTest(int indexNumber, String expectedAccordionHeader, String expectedAccordionText) {
        this.indexNumber = indexNumber;
        this.expectedAccordionHeader = expectedAccordionHeader;
        this.expectedAccordionText = expectedAccordionText;
    }

    @Parameterized.Parameters
    public static Object[][] headersAndText() {
        return new Object[][]{
                {0, "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Я живу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Before
    public void begin() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get(mainTestPageUrl);
    }

    // Тест: заголовки и текс отображаются и соответствуют тестовым данным
    // Логика теста: идем по списку - кликаем на заголовок, ждем загрузки текста, сравниваем текст и заголовок с тестовыми данными
    @Test
    public void checkAccordionHeaderAndTextIsCorrect() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.waitForLoad();
        objMainPage.clickOnAcceptCookieButton();
        objMainPage.clickAccordionHeader(indexNumber);
        objMainPage.waitForLoadAccordionText(indexNumber);

        MatcherAssert.assertThat("Неверный текст заголовка",
                expectedAccordionHeader,
                equalTo(objMainPage.getAccordionHeaderText(indexNumber)));

        MatcherAssert.assertThat("Неверный текст внутри списка",
                expectedAccordionText,
                equalTo(objMainPage.getAccordionText(indexNumber)));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}