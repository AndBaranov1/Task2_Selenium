import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

/**
 * Created by 1 on 04.06.2018.
 * Задание 1 - Написать автотест по заданному сценарию:
 Сценарий №1
 1.       Перейти на страницу http://www.sberbank.ru/ru/person
 2.       Нажать на – Застраховать себя и имущество
 3.       Выбрать – Страхование путешественников
 4.       Проверить наличие на странице заголовка – Страхование путешественников
 5.       Нажать на – Оформить Онлайн
 6.       На вкладке – Выбор полиса  выбрать сумму страховой защиты – Минимальная
 7.       Нажать Оформить
 8.       На вкладке Оформить заполнить поля:
 •       Фамилию и Имя, Дату рождения застрахованных
 •       Данные страхователя: Фамилия, Имя, Отчество, Дата рождения, Пол
 •       Паспортные данные
 •       Контактные данные не заполняем
 9.       Проверить, что все поля заполнены правильно
 10.   Нажать продолжить
 11.   Проверить, что появилось сообщение - Заполнены не все обязательные поля
 */
public class SbrfTest extends BaseTest{


    @Test
    @Ignore
    public void testSbrf () {
        driver.get(baseUrl);
        //Нажимаем вкладку "Страхование"
        driver.findElement(By.xpath("//li//span[contains(text(),'Страхование')]")).click();
        //Открываем путешествия и покупи
        driver.findElement(By.xpath("//li//a[contains(text(),'Путешествия и покупки')]")).click();
        WebElement title = driver.findElement(By.xpath("//div//h3[contains(text(), 'Страхование путешественников')]"));
        //Проверяем название заголовка "Страхование путешественников"
        Assert.assertEquals("Страхование путешественников", title.getText());
        //Нажимаем "Оформить онлайн"
        driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/table/tbody/tr/td/div/div/div/div/div/div[2]/div/div[2]/div/div[2]/div/div[2]/div/div/div/div/div/p/a")).click();

        //Переход на вкладку
        String Tab1 = driver.getWindowHandle();
        ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
        if (!availableWindows.isEmpty()) {
            driver.switchTo().window(availableWindows.get(1));
        }
        //Добавляем ожидание
        Wait<WebDriver> wait = new WebDriverWait(driver, 10, 1000);
        wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath("//*[@id=\"views\"]/form/section/section/section[2]/div[1]/div[1]/div"))));
        //Выбираем "Минимальная"
        driver.findElement(By.xpath("//*[@id=\"views\"]/form/section/section/section[2]/div[1]/div[1]/div")).click();
        //Нажимаем "Оформить"
        driver.findElement(By.xpath("//SPAN[@ng-click='save()'][text()='Оформить']")).click();
        //Заполняем поля на вкладке "Оформить"
        fillField(By.name("insured0_surname"), "Ivanov");
        fillField(By.name("insured0_name"), "Ivan");
        fillField(By.name("insured0_birthDate"), "05.05.1990");
        fillField(By.name("surname"), "Петрова");
        fillField(By.name("name"), "Татьяна");
        fillField(By.name("middlename"), "Борисовна");

        // fillField(By.name("birthDate"), "15.09.1980");
        driver.findElement(By.name("birthDate")).click();
        driver.findElement(By.name("birthDate")).sendKeys("15.09.1980");

        driver.findElement(By.xpath("//*[@id=\"views\"]/section/form/section/section[2]/div/fieldset[8]/span[2]/input")).click();
        fillField(By.name("passport_series"), "5010");
        fillField(By.name("passport_number"), "215316");
        fillField(By.name("issueDate"), "10.06.2011");
        fillField(By.name("issuePlace"), "ОУФМС РОССИИ УВД ПО ЦЕНТРАЛЬНОМУ АДМИНИСТРАТИВНОМУ ОКРУГУ ГОРОД МОСКВА");
        //Нажимаем продолжить
        driver.findElement(By.xpath("//*[@id=\"views\"]/section/form/section/section[5]/div[1]/span[2]")).click();
        // Проверяем, что появилось сообщение о том, что заполнены не все поля
        Assert.assertEquals("Заполнены не все обязательные поля", driver.findElement(By.xpath("//*[@id=\"views\"]/section/form/section/section[5]/div[2]/div[1]")).getText());

        //Проверяем, что все поля заполнены верно
        Assert.assertEquals("Ivanov", driver.findElement(By.name("insured0_surname")).getAttribute("value"));
        Assert.assertEquals("Ivan", driver.findElement(By.name("insured0_name")).getAttribute("value"));
        Assert.assertEquals("05.05.1990", driver.findElement(By.name("insured0_birthDate")).getAttribute("value"));
        Assert.assertEquals("Петрова", driver.findElement(By.name("surname")).getAttribute("value"));
        Assert.assertEquals("Татьяна", driver.findElement(By.name("name")).getAttribute("value"));
        Assert.assertEquals("Борисовна", driver.findElement(By.name("middlename")).getAttribute("value"));
        Assert.assertEquals("15.09.1980", driver.findElement(By.name("birthDate")).getAttribute("value"));
        Assert.assertEquals("5010", driver.findElement(By.name("passport_series")).getAttribute("value"));
        Assert.assertEquals("215316", driver.findElement(By.name("passport_number")).getAttribute("value"));
        Assert.assertEquals("10.06.2011", driver.findElement(By.name("issueDate")).getAttribute("value"));
        Assert.assertEquals("ОУФМС РОССИИ УВД ПО ЦЕНТРАЛЬНОМУ АДМИНИСТРАТИВНОМУ ОКРУГУ ГОРОД МОСКВА", driver.findElement(By.name("issuePlace")).getAttribute("value"));
    }

    //Метод для хаполнения полей
    private void fillField(By locator, String value){
        driver.findElement(locator).sendKeys(value);
    }

}