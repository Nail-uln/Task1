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
    @FindBy(xpath = "//button[@type='submit' and text()='Add Customer']")
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

    public void inputFirstNameField(String firstName) {
        firstNameField.sendKeys(firstName);
    }

    public void inputLastNameField(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    public void inputPostCodeField(String postCode) {
        postCodeField.sendKeys(postCode);
    }

    public void clickAddCustomerButton() {
        addCustomerButton.click();
    }

    public String getAlertMessage() {
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
