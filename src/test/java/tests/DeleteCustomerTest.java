package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.CustomersPage;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс с тестом удаления клиентов
 */
public class DeleteCustomerTest extends BaseTest {

    private CustomersPage customersPage;
    private List<String> listNames;
    private Integer lenghtNearestAvg;

    @BeforeEach
    public void setUp() {
        customersPage = managerPage.clickCustomersButton();
        listNames = customersPage.getListWebElementsFirstName().stream()
                .map(webElement -> webElement.getText())
                .collect(Collectors.toList());
        double averageLengthOfName = listNames.stream()
                .mapToInt(String::length)
                .average()
                .orElse(0.0);
        lenghtNearestAvg = listNames.stream()
                .map(String::length)
                .min((num1, num2) -> Math.abs(num1 - averageLengthOfName) < Math.abs(num2 - averageLengthOfName) ? -1 : 1)
                .orElse(null);
    }

    @Test
    public void deleteCustomerTest() {
        listNames.stream()
                .filter(name -> name.length() == lenghtNearestAvg)
                .forEach(name -> {
                    for (int row = 1; row <= customersPage.getCountCustomers(); row++) {
                        if (name.equals(customersPage.getCellValue(row, 1))) {
                            customersPage.deleteCustomer(row);
                        }
                    }
                });
        for (int row = 1; row <= customersPage.getCountCustomers(); row++) {
            Assertions.assertNotEquals(lenghtNearestAvg, customersPage.getCellValue(row, 1).length());
        }
    }
}
