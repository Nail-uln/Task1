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
 * Класс с тестом получения сущности по заданному id
 */
public class ApiGetEntityByIdTest {
    /**
     * Спецификация запроса
     */
    private RequestSpecification requestSpecification;

    /**
     * Id сущности
     */
    private String entityId;

    /**
     * Сущность
     */
    private MyEntity myEntityPojo;

    @BeforeEach
    public void setUp() throws IOException {
        requestSpecification = BaseRequests.initRequestSpecification(ContentType.JSON);
        myEntityPojo = MyEntity.builder().build();
        entityId = BaseRequests.createEntity(requestSpecification, myEntityPojo);
    }

    @Test
    public void getEntityByIdTest() {
        MyEntityResponse myEntityResponse = given()
                .spec(requestSpecification)
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
