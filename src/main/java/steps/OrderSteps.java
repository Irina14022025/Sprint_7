package steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.OrderModel;


import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;


public class OrderSteps {
    public static final String ORDER_CREATE_PATH = "/api/v1/orders";
    public static final String ORDER_CANCEL_PATH = "/api/v1/orders/cancel?track=";

    @Step("Создание заказа POST /api/v1/orders")
    public static Response createOrder(OrderModel order){
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(order)
                .when()
                .post(ORDER_CREATE_PATH);
    }
    @Step("Проверка статуса и bogy для POST /api/v1/orders")
    public void checkStatusAndBodyResponseCreateOrder(Response response){
        response
                .then().assertThat()
                .statusCode(201)
                .body("track", notNullValue());
    }

    @Step("Отмена заказа PUT /api/v1/orders")
    public static Response cancelOrder(int trackId) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(Map.of("track", trackId))
                .when()
                .put(ORDER_CANCEL_PATH + trackId)
                .then().log().all()
                .extract().response();
    }

    @Step("Получение списка заказов GET /api/v1/orders")
    public static Response viewListOrder(){
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .get(ORDER_CREATE_PATH)
                .then().log().all()
                .extract().response();
    }


}
