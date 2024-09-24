package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Базовый класс страниц сайта
 */
public class BasePage {

    /**
     * Web драйвер
     */
    protected final WebDriver driver;

    /**
     * Конструктор класса
     *
     * @param webDriver web драйвер
     */
    public BasePage(final WebDriver webDriver) {
            PageFactory.initElements(webDriver, this);
            this.driver = webDriver;
    }
}
