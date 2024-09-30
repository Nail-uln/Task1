package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Класс страницы добавления клиентов
 */
public class AddCustPage extends BasePage {

    /**
     * поле "First Name" (Имя)
     */
    @FindBy(xpath = "//input[@ng-model='fName']")
    private WebElement firstNameField;

    /**
     * поле "Last Name" (Фамилия)
     */
    @FindBy(xpath = "//input[@ng-model='lName']")
    private WebElement lastNameField;

    /**
     * поле "Post Code" (Почтовый индекс)
     */
    @FindBy(xpath = "//input[@ng-model='postCd']")
    private WebElement postCodeField;

    /**
     * Кнопка добавления нового клиента
     */
    @FindBy(css = "button[type^='submit'")
    private WebElement addCustomerButton;

    /**
     * Кнопка "Customers" для перехода на страницу со списком клиентов
     */
    @FindBy(xpath = "//button[@ng-class='btnClass3']")
    private WebElement customersButton;

    /**
     * Конструктор класса
     *
     * @param webDriver web драйвер
     */
    public AddCustPage(final WebDriver webDriver) {
        super(webDriver);
    }

    public AddCustPage inputFirstNameField(String firstName) {
        firstNameField.sendKeys(firstName);
        return this;
    }

    public AddCustPage inputLastNameField(String lastName) {
        lastNameField.sendKeys(lastName);
        return this;
    }

    public AddCustPage inputPostCodeField(String postCode) {
        postCodeField.sendKeys(postCode);
        return this;
    }

    public void clickAddCustomerButton() {
        addCustomerButton.click();
    }

    /**
     * Метод получения текста всплывающего уведомления и последующее его закрытие
     * @return
     */
    public String getAlertMessageAndAccept() {
        Alert alert = driver.switchTo().alert();
        String message = alert.getText();
        alert.accept();
        return message;
    }

    public CustomersPage clickCustomersButton() {
        customersButton.click();
        return new CustomersPage(driver);
    }
}
