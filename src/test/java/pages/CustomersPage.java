package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CustomersPage extends BasePage {

    @FindBy(xpath = "//input[@ng-model='searchCustomer']")
    private WebElement searchField;

    @FindBy(xpath = "//table//tbody//td[1]")
    private WebElement cellFirstName;

    @FindBy(xpath = "//table//tbody//td[2]")
    private WebElement cellLastName;

    @FindBy(xpath = "//table//tbody//td[3]")
    private WebElement cellPostCode;

    @FindBy(xpath = "//table//thead//td//a[contains(@ng-click,'fName')]")
    private WebElement headerFirstName;

    @FindBy(xpath = "//table//thead//td//a[contains(@ng-click,'fName')]//span[@ng-show=\"sortType == 'fName' && !sortReverse\"]")
    private WebElement spanFNameSortNotReverse;

    @FindBy(xpath = "//table[@class=\"table table-bordered table-striped\"]//tbody//tr")
    private List<WebElement> listWebElementsRows;

    @FindBy(xpath = "//table//tbody//tr//td[1]")
    private List<WebElement> listWebElementsFirstName;





    public CustomersPage(final   WebDriver webDriver) {
        super(webDriver);
    }


    public void inputSearchField(String postCode) {
        searchField.sendKeys(postCode);
    }

    public String getTextCellFirstName() {
        return cellFirstName.getText();
    }

    public String getTextCellLastName() {
        return cellLastName.getText();
    }

    public String getTextCellPostCode() {
        return cellPostCode.getText();
    }

    public void clickHeaderFirstName() {
        headerFirstName.click();
    }

    public WebElement getSpanFNameSortNotReverse() {
        return spanFNameSortNotReverse;
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


    public String getCellValue(int row, int column){
        return driver.findElement(By.xpath("//table//tbody//tr["+row+"]//td["+column+"]")).getText();
    }

    public void deleteCustomer(int row){
        WebElement buttonDelete = driver.findElement(By.xpath("//table//tbody//tr["+row+"]//td[5]//button[@ng-click='deleteCust(cust)']"));
        buttonDelete.click();
    }

}
