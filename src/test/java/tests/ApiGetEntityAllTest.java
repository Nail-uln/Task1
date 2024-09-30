package tests;

import helpers.BaseRequests;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pojo.MyEntity;


import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Класс с тестами получения всех сущностей, получения сущностей по заданным параметрам
 */
public class ApiGetEntityAllTest {
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
    public void getEntityAllTest() {
        given()
                .spec(requestSpecification)
                .when()
                .get("/api/getAll")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("entity.size()", greaterThan(0));
    }

    @Test
    public void getEntityByTitleTest() {
        given()
                .spec(requestSpecification)
                .queryParam("title",myEntityPojo.getTitle())
                .when()
                .get("/api/getAll")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("entity.title", everyItem(equalTo(myEntityPojo.getTitle())));
    }

    @Test
    public void getEntityBy2ParamsTest() {
        given()
                .spec(requestSpecification)
                .queryParam("title",myEntityPojo.getTitle(),"verified", myEntityPojo.getVerified())
                .when()
                .get("/api/getAll")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("entity.title", everyItem(equalTo(myEntityPojo.getTitle())), "entity.verified", everyItem(equalTo(myEntityPojo.getVerified())));
    }

    @AfterEach
    public void tearDown() {
        BaseRequests.deleteEntityById(entityId);
    }
}
