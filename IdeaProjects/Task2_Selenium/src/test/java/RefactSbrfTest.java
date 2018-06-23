import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.MainPage;
import pages.Blanck1Page;
import pages.Blanck2Page;
import pages.TravelsPage;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by 1 on 09.06.2018.
 */
public class RefactSbrfTest extends BaseTest {


    @Test
    public void newSbrfTest() {
        driver.get(baseUrl);
        MainPage mainPage = new MainPage(driver);
        mainPage.selectMainMenu("Страхование");
        mainPage.selectSubMenu("Путешествия и покупки");
        /**
         * Добавлен дополнительный выбор, так как есть баг: При переходе на форму "Путешествия и покупки" ошибка 404.
         * Если еще раз кликуть на "Страхование", "Путешествия и покупки" то переходит на форму "Сбербанк страхование
         */
        mainPage.select1MainMenu("Страхование");
        mainPage.select1SubMenu("Путешествия и покупки");

        // Проверить наличие на странице заголовка – Страхование путешественников
        TravelsPage travelsPage = new TravelsPage(driver);
        assertEquals("Страхование путешественников", travelsPage.title.getText());
        // Нажать на – Оформить Онлайн
        new TravelsPage(driver).issueButtonClick.click();

        //Переход на вкладку
        String Tab1 = driver.getWindowHandle();
        ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
        if (!availableWindows.isEmpty()) {
            driver.switchTo().window(availableWindows.get(1));
        }
        // На вкладке – Выбор полиса  выбрать сумму страховой защиты – Минимальная
        Blanck1Page page1 = new Blanck1Page(driver);
        page1.selectSum("Минимальная");

        // Нажать Оформить
        page1.issueButton.click();
        // На вкладке Оформление заполнить поля:
        Blanck2Page page2 = new Blanck2Page(driver);
        String actualTitle = page2.title3.getText();
        String expectedTitle = "Оформление";
        assertTrue(String.format("Заголовок равен [%s]. Ожидалось - [%s]",
                actualTitle, expectedTitle), actualTitle.contains(expectedTitle));

        // Фамилию и Имя, Дату рождения застрахованных
        page2.fillField("Застрахованные: Фамилия", "Ivanov");
        page2.fillField("Застрахованные: Имя", "Ivan");
        page2.fillField("Застрахованные: Дата рождения", "05.05.1990");

        // Данные страхователя: Фамилия, Имя, Отчество, Дата рождения, Пол
        page2.fillField("Фамилия", "Петрова");
        page2.fillField("Имя", "Татьяна");
        page2.fillField("Отчество", "Борисовна");
        page2.birthDate.click();
        page2.fillField("Дата рождения", "15.09.1980");
        page2.female.click();

        // Паспортные данные
        page2.fillField("Серия паспорта", "5010");
        page2.fillField("Номер паспорта", "215316");
        page2.fillField("Дата выдачи", "10.06.2011");
        page2.fillField("Место выдачи", "ОУФМС РОССИИ УВД ПО ЦЕНТРАЛЬНОМУ АДМИНИСТРАТИВНОМУ ОКРУГУ ГОРОД МОСКВА");

        // Контактные данные не заполняем

        //Проверить, что все поля заполнены правильно
        page2.checkFieldData("Застрахованные: Фамилия","Ivanov");
        page2.checkFieldData("Застрахованные: Имя", "Ivan");
        page2.checkFieldData("Застрахованные: Дата рождения","05.05.1990");

        page2.checkFieldData("Фамилия","Петрова");
        page2.checkFieldData("Имя","Татьяна");
        page2.checkFieldData("Отчество", "Борисовна");
        page2.checkFieldData("Дата рождения","15.09.1980");

        page2.checkFieldData("Серия паспорта","5010");
        page2.checkFieldData("Номер паспорта","215316");
        page2.checkFieldData("Дата выдачи", "10.06.2011");
        page2.checkFieldData("Место выдачи","ОУФМС РОССИИ УВД ПО ЦЕНТРАЛЬНОМУ АДМИНИСТРАТИВНОМУ ОКРУГУ ГОРОД МОСКВА");

        //Нажать кнопку Продолжить
        page2.contButton.click();

        // Проверить, что появилось сообщение - Заполнены не все обязательные поля
//        WebElement webElement = driver.findElement(By.xpath("//div[@ng-show='tryNext && myForm.$invalid']"));
//        assertEquals("Заполнены не все обязательные поля", webElement.getText());
        Blanck2Page blanck2Page = new Blanck2Page(driver);
        assertEquals("Заполнены не все обязательные поля", blanck2Page.errorMessage.getText());
    }
}
