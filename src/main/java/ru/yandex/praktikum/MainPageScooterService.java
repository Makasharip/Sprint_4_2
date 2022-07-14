package ru.yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class MainPageScooterService {
    private WebDriver driver;
    public final static String URL_YANDEX = "https://qa-scooter.praktikum-services.ru/";

    public MainPageScooterService(WebDriver driver) {
        this.driver = driver;
    }

    // Массив с ожидаемыми ответами на вопросы из столбца "Вопросы о важном"
    public static final String[] ANSWERS = {
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."
    };

    // Массив локаторов для вопросов из столбца "Вопросы о важном"
    private final By[] LOCATORS_QUESTIONS = {
            By.xpath(".//div[@class='accordion']/div[1]"),
            By.xpath(".//div[@class='accordion']/div[2]"),
            By.xpath(".//div[@class='accordion']/div[3]"),
            By.xpath(".//div[@class='accordion']/div[4]"),
            By.xpath(".//div[@class='accordion']/div[5]"),
            By.xpath(".//div[@class='accordion']/div[6]"),
            By.xpath(".//div[@class='accordion']/div[7]"),
            By.xpath(".//div[@class='accordion']/div[8]")
    };

    // Массив локаторов для ответов из столбца "Вопросы о важном"
    private final By[] LOCATORS_ANSWERS = {
            By.xpath(".//div[@id='accordion__panel-0']/p"),
            By.xpath(".//div[@id='accordion__panel-1']/p"),
            By.xpath(".//div[@id='accordion__panel-2']/p"),
            By.xpath(".//div[@id='accordion__panel-3']/p"),
            By.xpath(".//div[@id='accordion__panel-4']/p"),
            By.xpath(".//div[@id='accordion__panel-5']/p"),
            By.xpath(".//div[@id='accordion__panel-6']/p"),
            By.xpath(".//div[@id='accordion__panel-7']/p")
    };

    private By cookie = By.className("App_CookieButton__3cvqF");
    //Верхняя кнопка "Заказать"
    private By upperOrderButton = By.className("Button_Button__ra12g");
    //Нижняя кнопка "Заказать"
    private By lowerOrderButton = By.xpath("(//button [text()='Заказать'])[2]");


    public void clickUpperOrderButton() {
        driver.findElement(upperOrderButton).click();
    }

    public void clickLowerOrderButton() {

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                driver.findElement(lowerOrderButton));
        driver.findElement(lowerOrderButton).click();
    }

    public void closeCookieButton() {
        driver.findElement(cookie).click();
    }

    // Скрол к указанному вопросу из столбца "Вопросы о важном"
    public void scrollToAccordionButtons(int locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(LOCATORS_QUESTIONS[locator]));
    }

    // Клик по раскрывающей кнопке из столбца "Вопросы о важном" и метод возвращающий текст ответа
    public String clickToAccordionButton(int locator) {
        driver.findElement(LOCATORS_QUESTIONS[locator]).click();
        return driver.findElement(LOCATORS_ANSWERS[locator]).getText();
    }

    // Объединение методов скрола и клика
    public void scrollAndClickToAccordionButton(int locator) {
        scrollToAccordionButtons(locator);
        clickToAccordionButton(locator);
    }

    // Скрол к нижней кнопке "Заказать" (большой)
    public void scrollToLowerButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
                driver.findElement(lowerOrderButton));
    }

    // Клик на верхнюю кнопку "Заказать"
    public void clickToOrderButtonUp() {
        driver.findElement(upperOrderButton).click();
    }

    // Клик на нижнюю кнопку "Заказать" (большую)
    public void clickToOrderButtonLow() {
        driver.findElement(lowerOrderButton).click();
    }
}
