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
 * Класс с тестом обновления сущности
 */
public class ApiPatchEntityTest {

    /**
     * Спецификация запроса
     */
    private RequestSpecification requestSpecification;

    /**
     * Спецификация запроса
     */
    private RequestSpecification requestSpecificationForGet;

    /**
     * Id сущности
     */
    private String entityId;

    /**
     * Сущность
     */
    private MyEntity myEntityPojo;

    /**
     * Сущность для обновления данных
     */
    private MyEntity myEntityPojoForUpdate;

    @BeforeEach
    public void setUp() throws IOException {
        requestSpecification = BaseRequests.initRequestSpecification(ContentType.TEXT);
        requestSpecificationForGet = BaseRequests.initRequestSpecification(ContentType.JSON);
        myEntityPojo = MyEntity.builder().build();
        entityId = BaseRequests.createEntity(requestSpecification, myEntityPojo);
        myEntityPojoForUpdate = MyEntity.builder().build();
    }

    @Test
    public void patchEntityTest() {
        given()
                .spec(requestSpecification)
                .body(myEntityPojoForUpdate)
                .when()
                .patch("/api/patch/%s".formatted(entityId))
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

        MyEntityResponse myEntityResponse = given()
                .spec(requestSpecificationForGet)
                .when()
                .get("/api/get/%s".formatted(entityId))
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(MyEntityResponse.class, ObjectMapperType.GSON);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(myEntityResponse.getId()).isEqualTo(entityId);
        softAssertions.assertThat(myEntityResponse.getTitle()).isEqualTo(myEntityPojoForUpdate.getTitle());
        softAssertions.assertThat(myEntityResponse.getVerified()).isEqualTo(myEntityPojoForUpdate.getVerified());
        softAssertions.assertThat(myEntityResponse.getAddition().getAdditionalInfo()).isEqualTo(myEntityPojoForUpdate.getAddition().getAdditionalInfo());
        softAssertions.assertThat(myEntityResponse.getAddition().getAdditionalNumber()).isEqualTo(myEntityPojoForUpdate.getAddition().getAdditionalNumber());
        softAssertions.assertThat(myEntityResponse.getImportantNumbers()).isEqualTo(myEntityPojoForUpdate.getImportantNumbers());
        softAssertions.assertAll();
    }

    @AfterEach
    public void tearDown() {
        BaseRequests.deleteEntityById(entityId);
    }
}
