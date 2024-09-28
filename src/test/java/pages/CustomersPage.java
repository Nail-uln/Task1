package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Класс страницы просмотра/удаления клиентов
 */
public class CustomersPage extends BasePage {

    /**
     * поле ввода поиска клиентов
     */
    @FindBy(xpath = "//input[@ng-model='searchCustomer']")
    private WebElement searchField;

    /**
     * Заголовок "First Name" (Имя) первой колонки таблицы клиентов
     */
    @FindBy(xpath = "//table//thead//td//a[contains(@ng-click,'fName')]")
    private WebElement headerFirstName;

    /**
     * span в заголовке "First Name" первой колоки таблицы, необходим для определния прямой/обратной сортировки
     */
    @FindBy(xpath = "//table//thead//td//a[contains(@ng-click,'fName')]//span[@ng-show=\"sortType == 'fName' && !sortReverse\"]")
    private WebElement spanFNameSortType;

    /**
     * список всех строк таблицы клиентов
     */
    @FindBy(xpath = "//table[@class=\"table table-bordered table-striped\"]//tbody//tr")
    private List<WebElement> listWebElementsRows;

    /**
     * список ячеек с именем клиентов
     */
    @FindBy(xpath = "//table//tbody//tr//td[1]")
    private List<WebElement> listWebElementsFirstName;

    /**
     * Конструктор класса
     *
     * @param webDriver web драйвер
     */
    public CustomersPage(final WebDriver webDriver) {
        super(webDriver);
    }

    public void inputSearchField(String postCode) {
        searchField.sendKeys(postCode);
    }

    public void clickHeaderFirstName() {
        headerFirstName.click();
    }

    public boolean isSpanFNameSortReverse() {
        return spanFNameSortType.getAttribute("class").contains("ng-hide");
    }

    public int getCountCustomers() {
        return listWebElementsRows.size();
    }

    public String getFirstName(int number) {
        return listWebElementsFirstName.get(number).getText();
    }

    public List<WebElement> getListWebElementsFirstName() {
        return listWebElementsFirstName;
    }

    /**
     * Метод получения значения запрашиваемой ячейки таблицы клиентов
     * @param row номер строки таблицы
     * @param column номер колонки таблицы
     * @return текстовое содержимое ячейки таблицы
     */
    public String getCellValue(int row, int column) {
        return driver.findElement(By.xpath(String.format("//table//tbody//tr[%d]//td[%d]", row, column))).getText();
    }

    /**
     * Метод удаления клиента
     * @param name номер строки таблицы
     */
    public void deleteCustomer(String name) {
        for (int i = 0; i <listWebElementsFirstName.size() ; i++) {
            if (listWebElementsFirstName.get(i).getText().equals(name)) {
                WebElement buttonDelete = driver.findElement(By.xpath("//table//tbody//tr[" + (i + 1) + "]//td[5]//button[@ng-click='deleteCust(cust)']"));
                buttonDelete.click();
            }
        }
    }

    /**
     * Метод удаления клиента
     * @param name номер строки таблицы
     */
    public void getRowCustomerByName(String name) {
        for (int i = 0; i <listWebElementsFirstName.size() ; i++) {
            if (listWebElementsFirstName.get(i).getText().equals(name)) {
                WebElement buttonDelete = driver.findElement(By.xpath("//table//tbody//tr[" + (i + 1) + "]//td[5]//button[@ng-click='deleteCust(cust)']"));
                buttonDelete.click();
            }
        }
    }


}
