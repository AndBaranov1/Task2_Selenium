package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by 1 on 09.06.2018.
 */
public class Blanck2Page {
    @FindBy(xpath = "//span [contains(text(),'Оформление')]")
    public WebElement title3;

    @FindBy(name = "insured0_surname")
    public WebElement insured0_surname;

    @FindBy(name = "insured0_name")
    public WebElement insured0_name;

    @FindBy(name = "insured0_birthDate")
    public WebElement insured0_birthDate;

    @FindBy(name = "surname")
    public WebElement surname;

    @FindBy(name = "name")
    public WebElement name;

    @FindBy(name = "middlename")
    public WebElement middlename;

    @FindBy(name = "birthDate")
    public WebElement birthDate;

    @FindBy(name = "female")
    public WebElement female;

    @FindBy(name = "passport_series")
    public WebElement passport_series;

    @FindBy(name = "passport_number")
    public WebElement passport_number;

    @FindBy(name = "issueDate")
    public WebElement issueDate;

    @FindBy(name = "issuePlace")
    public WebElement issuePlace;
    @FindBy(xpath = "//span[contains(text(),'Продолжить')]")
    public WebElement contButton;

    @FindBy(xpath = "//div[@ng-show='tryNext && myForm.$invalid']")
    public WebElement errorMessage;

    public Blanck2Page(WebDriver driver) {
        PageFactory.initElements(driver, this);
        Wait<WebDriver> wait = new WebDriverWait(driver, 10, 1000);
        wait.until(ExpectedConditions.visibilityOf(title3));
        wait.until(ExpectedConditions.visibilityOf(contButton));
    }

    public void fillField(String fieldName, String value) {
        switch (fieldName) {
            case "Застрахованные: Фамилия":
                fillField(insured0_surname, value);
                break;
            case "Застрахованные: Имя":
                fillField(insured0_name, value);
                break;
            case "Застрахованные: Дата рождения":
                fillField(insured0_birthDate, value);
                break;
            case "Фамилия":
                fillField(surname, value);
                break;
            case "Имя":
                fillField(name, value);
                break;
            case "Отчество":
                fillField(middlename, value);
                break;
            case "Дата рождения":
                fillField(birthDate, value);
                break;
            case "Пол":
                fillField(female, value);
                break;
            case "Серия паспорта":
                fillField(passport_series, value);
                break;
            case "Номер паспорта":
                fillField(passport_number, value);
                break;
            case "Дата выдачи":
                fillField(issueDate, value);
                break;
            case "Место выдачи":
                fillField(issuePlace, value);
                break;
            default:
                throw new AssertionError("Поле '" + fieldName + "' не объявлено на странице");
        }

    }

    public void fillField(WebElement element, String value) {
        //element.clear();
        element.sendKeys(value);
    }
    public String getFillField(String fieldName) {
        switch (fieldName) {
            case "Застрахованные: Фамилия":
                return insured0_surname.getAttribute("value");
            case "Застрахованные: Имя":
                return insured0_name.getAttribute("value");
            case "Застрахованные: Дата рождения":
                return insured0_birthDate.getAttribute("value");
            case "Фамилия":
                return surname.getAttribute("value");
            case "Имя":
                return name.getAttribute("value");
            case "Отчество":
                return middlename.getAttribute("value");
            case "Дата рождения":
                return birthDate.getAttribute("value");
            case "Пол":
                return female.getAttribute("value");
            case "Серия паспорта":
                return passport_series.getAttribute("value");
            case "Номер паспорта":
                return passport_number.getAttribute("value");
            case "Дата выдачи":
                return issueDate.getAttribute("value");
            case "Место выдачи":
                return issuePlace.getAttribute("value");
        }
        throw new AssertionError("Поле не объявлено на странице");
    }

    public void checkFieldData(String field, String value) {
        Assert.assertEquals(value, getFillField(field));

    }
}
