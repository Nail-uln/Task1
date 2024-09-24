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
        try {
            PageFactory.initElements(webDriver, this);
            this.driver = webDriver;
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
    }
}
