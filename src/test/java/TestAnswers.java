import ru.yandex.praktikum.MainPageScooterService;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static ru.yandex.praktikum.MainPageScooterService.URL_YANDEX;

@RunWith(Parameterized.class)
public class TestAnswers {
    private WebDriver driver;
    private final int number;

    public TestAnswers(int number) {
        this.number = number;
    }
    // Подстовляем все 8 вопросов и ответов (через индексы массивов)
    @Parameterized.Parameters
    public static Object[][] getNumber () {
        return new Object[][] {
                {0},{1},{2},{3},
                {4},{5},{6},{7}
        };
    }

    @Before
    public void testSetup() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(URL_YANDEX);
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);

    }

    @Test
    public void testAccordionButton() {
        MainPageScooterService mainPageScooterService = new MainPageScooterService(driver);

        mainPageScooterService.scrollAndClickToAccordionButton(number);

        assertEquals("Text not found or doesn't match",mainPageScooterService.ANSWERS[number], mainPageScooterService.clickToAccordionButton(number));
    }

    @After
    public void teardown(){
        driver.close();
        driver.quit();
    }
}


