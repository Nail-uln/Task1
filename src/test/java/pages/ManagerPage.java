package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManagerPage extends BasePage {

    @FindBy(xpath = "//button[@ng-class='btnClass1']")
    private WebElement addCustomerButton;

    @FindBy(xpath = "//button[@ng-class='btnClass3']")
    private WebElement customersButton;


    public ManagerPage(final WebDriver webDriver) {
        super(webDriver);
    }

    public AddCustPage clickAddCustomerButton(){
        addCustomerButton.click();
        return new AddCustPage(driver);
    }

    public CustomersPage clickCustomersButton(){
        customersButton.click();
        return new CustomersPage(driver);
    }
}
