package tests;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.CustomersPage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс с тестом удаления клиентов
 */
public class DeleteCustomerTest extends BaseTest {

    private CustomersPage customersPage;
    private List<String> listNames;
    double averageLengthOfName;
    private double minDeviation;

    @BeforeEach
    public void setUp() {
        customersPage = managerPage.clickCustomersButton();
        listNames = customersPage.getListWebElementsFirstName().stream()
                .map(webElement -> webElement.getText())
                .collect(Collectors.toList());
        averageLengthOfName = listNames.stream()
                .mapToInt(String::length)
                .average()
                .orElse(0.0);
        minDeviation = listNames.stream()
                .map(String::length)
                .map(lengthName -> (Math.round(Math.abs(lengthName - averageLengthOfName) * 100.0) / 100.0))
                .min(Double::compare).get();
    }

    @Test
    public void deleteCustomerTest() {
        List<String> deletedNamesList = new ArrayList<>();
        listNames.stream()
                .filter(name -> Math.round(Math.abs(name.length() - averageLengthOfName) * 100.0) / 100.0 == minDeviation)
                .forEach(name -> {
                            customersPage.deleteCustomer(name);
                            deletedNamesList.add(name);
                        }
                );

        SoftAssertions softAssertions = new SoftAssertions();
        deletedNamesList
                .forEach(deletedName -> {
                    for (int number = 0; number < customersPage.getCountCustomers(); number++) {
                        softAssertions.assertThat(customersPage.getFirstName(number)).isNotEqualTo(deletedName);
                    }
                });
        softAssertions.assertAll();
    }

    @Test
    public void deleteCustomerTest2() {
        List<String> deletedNamesList = new ArrayList<>();
        listNames.stream()
                .filter(name -> Math.round(Math.abs(name.length() - averageLengthOfName) * 100.0) / 100.0 == minDeviation)
                .forEach(name -> {
                            customersPage.deleteCustomer(name);
                            deletedNamesList.add(name);
                        }
                );


    }

}
