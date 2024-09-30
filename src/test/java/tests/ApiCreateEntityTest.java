package tests;

import helpers.BaseRequests;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pojo.MyEntity;
import pojo.MyEntityResponse;

import java.io.IOException;

import static io.restassured.RestAssured.given;

/**
 * Класс с тестом создания сущности
 */
public class ApiCreateEntityTest {

    /**
     * Спецификация запроса
     */
    private RequestSpecification requestSpecification;

    /**
     * Спецификация запроса
     */
    private RequestSpecification requestSpecificationForGet;

    /**
     * Сущность
     */
    private MyEntity myEntityPojo;

    /**
     * Id сущности
     */
    private String entityId;

    @BeforeEach
    public void setUp() throws IOException {
        requestSpecification = BaseRequests.initRequestSpecification(ContentType.TEXT);
        requestSpecificationForGet = BaseRequests.initRequestSpecification(ContentType.JSON);
        myEntityPojo = MyEntity.builder().build();
    }

    @Test
    public void createEntityTest() {
        entityId = given()
                .spec(requestSpecification)
                .body(myEntityPojo)
                .when()
                .post("/api/create")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .asString();

        MyEntityResponse myEntityResponse = given()
                .spec(requestSpecificationForGet)
                .when()
                .get("/api/get/%s".formatted(entityId))
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(MyEntityResponse.class, ObjectMapperType.GSON);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(myEntityResponse.getId()).isEqualTo(entityId);
        softAssertions.assertThat(myEntityResponse.getTitle()).isEqualTo(myEntityPojo.getTitle());
        softAssertions.assertThat(myEntityResponse.getVerified()).isEqualTo(myEntityPojo.getVerified());
        softAssertions.assertThat(myEntityResponse.getAddition().getAdditionalInfo()).isEqualTo(myEntityPojo.getAddition().getAdditionalInfo());
        softAssertions.assertThat(myEntityResponse.getAddition().getAdditionalNumber()).isEqualTo(myEntityPojo.getAddition().getAdditionalNumber());
        softAssertions.assertThat(myEntityResponse.getImportantNumbers()).isEqualTo(myEntityPojo.getImportantNumbers());
        softAssertions.assertAll();
    }

    @AfterEach
    public void tearDown() {
        BaseRequests.deleteEntityById(entityId);
    }
}
