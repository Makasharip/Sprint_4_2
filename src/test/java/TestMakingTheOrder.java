import ru.yandex.praktikum.OrderInformationPage;
import ru.yandex.praktikum.MainPageScooterService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertTrue;
import static ru.yandex.praktikum.MainPageScooterService.URL_YANDEX;

@RunWith(Parameterized.class)
public class TestMakingTheOrder {
    private WebDriver driver;
    private final String FNAME;
    private final String SNAME;
    private final String ADDR;
    private final String METRO;
    private final String NUMBER;
    private final String DATE;
    private final String TIME;
    private final String COMM;



    public TestMakingTheOrder(String fname, String sname, String addr, String metro, String number, String date, String time, String comm) {
        this.FNAME = fname;
        SNAME = sname;
        ADDR = addr;
        METRO = metro;
        NUMBER = number;
        DATE = date;
        TIME = time;
        COMM = comm;
    }

    @Parameterized.Parameters
    public static Object[][] getNumber () {
        return new Object[][] {
                {"Макашарип", "Муртазалиев", "ул. Вашего д.25 кв.5", "Сокольники", "88005553535","16.07.2022", "сутки", "Позвонить перед приездом"},

        };
    }
    @Before
    public void setPropAndStartBrowser() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(URL_YANDEX);
        MainPageScooterService mainPageScooterService = new MainPageScooterService(driver);
        mainPageScooterService.closeCookieButton();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);

    }
    @Test
    public void testOrderWithLowerButton() {

        MainPageScooterService mainPageScooterService = new MainPageScooterService(driver);
        mainPageScooterService.clickToOrderButtonLow();

        OrderInformationPage orderInformationPage = new OrderInformationPage(driver);
        orderInformationPage.orderPageFirstInput(FNAME,SNAME,ADDR,METRO,NUMBER);
        orderInformationPage.clickOnBlackCheckBox();
        orderInformationPage.orderPageSecondInput(DATE,TIME,COMM);
        orderInformationPage.clickYesButton();

        assertTrue("Window Order Completed - not found!",orderInformationPage.getNotificationOfOrderCreation());
    }
    @Test
    public void testOrderWithUpperButton() {

        MainPageScooterService mainPageScooterService = new MainPageScooterService(driver);
        mainPageScooterService.clickToOrderButtonUp();

        OrderInformationPage orderInformationPage = new OrderInformationPage(driver);
        orderInformationPage.orderPageFirstInput(FNAME,SNAME,ADDR,METRO,NUMBER);
        orderInformationPage.orderPageSecondInput(DATE,TIME,COMM);
        orderInformationPage.clickYesButton();

        assertTrue("Window Order Completed - not found!",orderInformationPage.getNotificationOfOrderCreation());
    }

    @After
    public void teardown(){

        driver.quit();

    }
}

