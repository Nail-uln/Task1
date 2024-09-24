package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Класс главной страницы менеджера банка
 */
public class ManagerPage extends BasePage {

    /**
     * Кнопка "Add customer" для добавления клиентов
     */
    @FindBy(xpath = "//button[@ng-class='btnClass1']")
    private WebElement addCustomerButton;

    /**
     * Кнопка "Customers"  для перехода на страницу со списком клиентов
     */
    @FindBy(xpath = "//button[@ng-class='btnClass3']")
    private WebElement customersButton;

    /**
     * Конструктор класса
     *
     * @param webDriver web драйвер
     */
    public ManagerPage(final WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * Нажатие на кнопку добавления клиента
     *
     * @return Страница добавления клиента
     */
    public AddCustPage clickAddCustomerButton() {
        addCustomerButton.click();
        return new AddCustPage(driver);
    }

    /**
     * Нажатие на кнопку просмотра клиентов
     *
     * @return Страница со списком клиентов
     */
    public CustomersPage clickCustomersButton() {
        customersButton.click();
        return new CustomersPage(driver);
    }
}
