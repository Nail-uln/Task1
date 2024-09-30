package helpers;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import pojo.MyEntity;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class BaseRequests {

    /**
     * Составление спецификации запроса
     * @return спецификация
     */
    public static RequestSpecification initRequestSpecification(ContentType acceptContentType) throws IOException {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder
                .setContentType(ContentType.JSON)
                .setBaseUri(ConfigProperties.getProperty("api.url"))
                .setAccept(acceptContentType);
        return requestSpecBuilder.build();
    }

    /**
     * Создание сущности
     * @param requestSpecification спецификация запроса
     * @param myEntityPojo сущность
     * @return id созданной сущности
     */
    public static String createEntity(RequestSpecification requestSpecification, MyEntity myEntityPojo) {
        String entityId;
        entityId = given()
                .spec(requestSpecification)
                .body(myEntityPojo)
                .when()
                .post("/api/create")
                .then()
                .extract().asString();
        return entityId;
    }

    /**
     * Удаление сущности с заданным id
     * @param entityId id сущности подлежащей удалению
     */
    public static void deleteEntityById(String entityId) {
        given()
                .when()
                .delete("/api/delete/%s".formatted(entityId))
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
