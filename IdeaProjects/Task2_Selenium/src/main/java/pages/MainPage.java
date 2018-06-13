package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by 1 on 08.06.2018.
 */
public class MainPage {
    WebDriver driver;
    @FindBy(xpath = "//ul[@class='lg-menu__list']")
    WebElement mainMenu;

    @FindBy(xpath = "(//DIV[@class='lg-menu__sub'])[6]")
    WebElement subMenu;

    public MainPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver= driver;
    }

    public void selectMainMenu(String menuItem){
        mainMenu.findElement(By.xpath(".//span[contains(text(),'"+menuItem+"')]")).click();
    }

    public void selectSubMenu(String menuItem) {
        subMenu.findElement(By.xpath("//li//a[contains(text(),'"+menuItem+"')]")).click();
    }
    /**
     * Добавлен дополнительный выбор, так как есть баг: При переходе на форму "Путешествия и покупки" ошибка 404.
     * Если еще раз кликуть на "Страхование", "Путешествия и покупки" то переходит на форму "Сбербанк страхование
     */

    @FindBy(xpath = "//ul[@class='lg-menu__list']")
    WebElement mainMenu1;

    @FindBy(xpath = "(//DIV[@class='lg-menu__sub'])[6]")
    WebElement subMenu1;
    public void select1MainMenu(String menuItem) {
        mainMenu1.findElement(By.xpath(".//span[contains(text(),'" + menuItem + "')]")).click();
    }
    public void select1SubMenu(String menuItem) {
        subMenu1.findElement(By.xpath("//li//a[contains(text(),'"+menuItem+"')]")).click();
    }

}
