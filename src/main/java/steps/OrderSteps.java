package steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.OrderModel;


import static io.restassured.RestAssured.given;


public class OrderSteps {
    public static final String ORDER_CREATE_PATH = "/api/v1/orders";

    @Step("Создание заказа POST /api/v1/orders")
    public static Response createOrder(OrderModel order){
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(order)
                .when()
                .post(ORDER_CREATE_PATH)
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
