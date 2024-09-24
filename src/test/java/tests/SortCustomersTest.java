package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.CustomersPage;

/**
 * Класс с тестом сортировки клиентов
 */
public class SortCustomersTest extends BaseTest {

    private CustomersPage customersPage;

    @BeforeEach
    public void setUp() {
        customersPage = managerPage.clickCustomersButton();
    }

    @Test
    public void sortCustomersTest() {
        customersPage.clickHeaderFirstName();
        if (customersPage.getSpanFNameSortNotReverse().getAttribute("class").contains("ng-hide")) {
            customersPage.clickHeaderFirstName();
        }
        String previousFirstName = "";
        String currentFirstName = "";
        for (int number = 0; number < customersPage.getCountCustomers(); number++) {
            currentFirstName = customersPage.getFirstName(number);
            Assertions.assertTrue(currentFirstName.compareToIgnoreCase(previousFirstName) > 0);
            previousFirstName = currentFirstName;
        }
    }
}
