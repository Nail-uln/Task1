package tests;

import helpers.BaseRequests;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pojo.MyEntity;
import pojo.MyEntityResponse;

import java.io.IOException;

import static io.restassured.RestAssured.given;

/**
 * Класс с тестом удаления сущности по заданному id
 */
public class ApiDeleteEntityTest {

    /**
     * Спецификация запроса
     */
    private RequestSpecification requestSpecification;

    /**
     * Id сущности
     */
    private String entityId;

    @BeforeEach
    public void setUp() throws IOException {
        requestSpecification = BaseRequests.initRequestSpecification(ContentType.TEXT);
        MyEntity myEntityPojo = MyEntity.builder().build();
        entityId = BaseRequests.createEntity(requestSpecification, myEntityPojo);
    }

    @Test
    public void deleteEntityTest() {
        given()
                .spec(requestSpecification)
                .when()
                .delete("/api/delete/%s".formatted(entityId))
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        given()
                .spec(requestSpecification)
                .when()
                .get("/api/get/%s".formatted(entityId))
                .then()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }
}
