package ru.yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class OrderInformationPage {
    private WebDriver driver;

    public OrderInformationPage(WebDriver driver) {
        this.driver = driver;
    }

    // Локаторы 1ой страницы заказа
    //Имя
    private By firstNameField = By.xpath("//input[@placeholder='* Имя']");
    //Фамилия
    private By secondNameField = By.xpath("//input[@placeholder='* Фамилия']");
    //Адрес
    private By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    //Станция метро
    private By metroStationField = By.xpath("//input[@placeholder='* Станция метро']");
    //Телефон
    private By phoneNumberField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка "Далее"
    private By nextButton = By.xpath("//button[text()='Далее']");

    //Локаторы 2ой страницы заказа
    //Когда привезти самокат
    private By dateDeliveryField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    //Срок аренды
    private By rentalDate = By.className("Dropdown-placeholder");

    //Выбор цвета самоката
    private final By colorBlack = By.id("black");
    private final By colorGrey = By.id("grey");

    //Комментарий для курьера
    private By comment = By.xpath("//input[@placeholder='Комментарий для курьера']");
    //Кнопка "Заказать"
    private By orderButton = By.xpath("(//button[text()='Заказать'])[2]");
    //Кнопка "Да" в окне подтверждения заказа
    private By yesButton = By.xpath("//button[text()='Да']");
    //Сообщение об успешном создании заказа
    public By notificationOfOrderCreation = By.xpath("//div[text()='Заказ оформлен']");

    // Методы для взаимодействия с локаторами
    // Вводим значение в поле Имя
    public void setFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    // Вводим значение в поле Фамилия
    public void setSecondName(String secondName) {
        driver.findElement(secondNameField).sendKeys(secondName);
    }

    // Вводим значение в поле Адрес
    public void setAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    // Вводим значение в поле Метро
    public void setMetroStation(String metroStation) {
        driver.findElement(metroStationField).sendKeys(metroStation + Keys.ARROW_DOWN + Keys.ENTER);
    }

    // Вводим значение в поле Телефон
    public void setPhoneNumber(String phoneNumber) {
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }

    // Кликаем по кнопке Далее
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    // Объединяем все методы для первой страницы ввода значений вместе с кликом по кнопке в 1 метод
    public void orderPageFirstInput(String firstName, String secondName, String address, String metroStation, String phoneNumber) {
        setFirstName(firstName);
        setSecondName(secondName);
        setAddress(address);
        setMetroStation(metroStation);
        setPhoneNumber(phoneNumber);
        clickNextButton();
    }

    // Вводим значение в поле Когда привезти самокат
    public void setDateDelivery(String date) {
        driver.findElement(dateDeliveryField).sendKeys(date + Keys.ENTER);
    }

    // Вводим значение в поле Срок аренды
    public void setRentalPeriod(String time) {
        driver.findElement(rentalDate).click();
        driver.findElement(By.xpath(".//div[@class='Dropdown-menu']/div[text()='" + time + "']")).click();
    }

    // Кликаем на чек-бокс цвета самоката (Черный)
    public void clickOnBlackCheckBox() {
        driver.findElement(colorBlack).click();
    }

    // Вводим значение в поле Комментарий
    public void setComment(String comm) {
        driver.findElement(comment).sendKeys(comm);
    }

    // Кликаем по кнопке заказать
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    // Объединяем все методы для второй страницы ввода значений вместе с тапом по кнопке в 1 метод
    public void orderPageSecondInput(String date, String time, String comm) {
        setDateDelivery(date);
        setRentalPeriod(time);
        setComment(comm);
        clickOrderButton();
    }

    // Кликаем по кнопке "да"
    public void clickYesButton() {
        driver.findElement(yesButton).click();
    }


    public boolean getNotificationOfOrderCreation() {
        return driver.findElement(notificationOfOrderCreation).getText().contains("Заказ оформлен");
    }
}
