package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.ManagerPage;

import java.time.Duration;

public class BaseTest {
    private static WebDriver driver;
    protected static ManagerPage managerPage;

    @BeforeAll
    public static void setUpClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager");
        managerPage = new ManagerPage(driver);
    }

    @AfterAll
    public static void tearDown(){
        driver.quit();
    }

}
