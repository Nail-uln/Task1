package tests;

import helpers.ConfigProperties;
import helpers.Helpers;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.AddCustPage;
import pages.CustomersPage;

import java.util.List;

/**
 * Класс с тестом создания клиентов
 */
public class AddCustomerTest extends BaseTest {

    Helpers helpers = new Helpers();
    private AddCustPage addCustPage;
    private CustomersPage customersPage;
    private String postCode;
    private String firstName;
    private final String lastName = ConfigProperties.getProperty("data.lastName");
    private final String letters = ConfigProperties.getProperty("data.letters");

    @BeforeEach
    public void setUp() {
        addCustPage = managerPage.clickAddCustomerButton();
        postCode = helpers.generateRandomPostCode(10);
        List<String> listString = helpers.splitStringByLength(postCode, 2);
        firstName="";
        for (String currentStr : listString) {
            int currentNumber = Integer.parseInt(currentStr);
            int indexOfLetters = currentNumber % 26;
            firstName = "%s%s".formatted(firstName, letters.substring(indexOfLetters, indexOfLetters + 1));
        }
    }

    @Test
    public void addCustomerTest() {
        SoftAssertions softAssertions = new SoftAssertions();
        addCustPage.inputPostCodeField(postCode)
                .inputFirstNameField(firstName)
                .inputLastNameField(lastName)
                .clickAddCustomerButton();
        String alertMessage = ConfigProperties.getProperty("data.alertMessage");
        softAssertions.assertThat(addCustPage.getAlertMessageAndAccept()).startsWith(alertMessage);
        customersPage = addCustPage.clickCustomersButton();
        customersPage.inputSearchField(postCode);
        softAssertions.assertThat(customersPage.getCellValue(1,1)).isEqualTo(firstName);
        softAssertions.assertThat(customersPage.getCellValue(1,2)).isEqualTo(lastName);
        softAssertions.assertThat(customersPage.getCellValue(1,3)).isEqualTo(postCode);
        softAssertions.assertAll();
    }
}
