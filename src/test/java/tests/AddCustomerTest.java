package tests;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.AddCustPage;
import pages.CustomersPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс с тестом создания клиентов
 */
public class AddCustomerTest extends BaseTest {

    private AddCustPage addCustPage;
    private CustomersPage customersPage;
    private StringBuilder postCode;
    private StringBuilder firstName;
    private String lastName = "Piligrim";
    private final String letters = "abcdefghijklmnopqrstuvwxyz";

    @BeforeEach
    public void setUp() {
        addCustPage = managerPage.clickAddCustomerButton();
        postCode = new StringBuilder();
        while (postCode.length() < 10) {
            postCode.append((int) (Math.random() * 10));
        }
        String[] arrayStr = splitStringByLength(postCode.toString(), 2);
        firstName = new StringBuilder();
        for (String currentStr : arrayStr) {
            int currentNumber = Integer.parseInt(currentStr);
            int indexOfLetters = currentNumber % 26;
            firstName.append(letters.substring(indexOfLetters, indexOfLetters + 1));
        }
    }

    /**
     * Метод, разбивающий строку на подстроки заданной длины
     * @param str строка которую требуется разбить на подстроки
     * @param length длина каждой подстроки
     * @return массив подстрок
     */
    private String[] splitStringByLength(String str, int length) {
        List<String> parts = new ArrayList<>();
        for (int i = 0; i < str.length(); i += length) {
            parts.add(str.substring(i, Math.min(i + length, str.length())));
        }
        return parts.toArray(new String[0]);
    }

    @Test
    public void addCustomerTest() {
        SoftAssertions softAssertions = new SoftAssertions();
        addCustPage.inputPostCodeField(postCode.toString());
        addCustPage.inputFirstNameField(firstName.toString());
        addCustPage.inputLastNameField(lastName);
        addCustPage.clickAddCustomerButton();
        String alertMessage = "Customer added successfully with customer id";
        softAssertions.assertThat(addCustPage.getAlertMessageAndAccept().startsWith(alertMessage));
        customersPage = addCustPage.clickCustomersButton();
        customersPage.inputSearchField(postCode.toString());
        softAssertions.assertThat(firstName.toString().equals(customersPage.getCellValue(1,1)));
        softAssertions.assertThat(lastName.equals(customersPage.getCellValue(1,2)));
        softAssertions.assertThat(postCode.toString().equals(customersPage.getCellValue(1,3)));
        softAssertions.assertAll();
    }
}
